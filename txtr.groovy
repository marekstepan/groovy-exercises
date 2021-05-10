//Application tested on Ubuntu 20.04.2 + Groovy 3.0.8 + JVM: 1.8.0_292

@Grapes([
    @Grab(group='org.slf4j', module='slf4j-api', version='1.7.0'),
    @Grab(group='ch.qos.logback', module='logback-classic', version='1.0.0')
])

//needed for cliBuilder
@Grab(group='commons-cli', module='commons-cli', version='1.4')

//slf4j for logging
import org.slf4j.*
import groovy.util.logging.Slf4j
import java.util.regex.*

@Slf4j
class Main {
    static main(args) {
        log.info('Application started.\n')
        CommandLineInterface cli = CommandLineInterface.INSTANCE
        cli.parse(args)
    }
}

//I decided to make some nice CLI first time in my life. Next time, I will try picoli
//and try to make it colorful + maybe some nice ASCII logo

@Slf4j
enum CommandLineInterface {
    INSTANCE

    CliBuilder cliBuilder

    CommandLineInterface() {
        cliBuilder = new CliBuilder(
            usage: '''groovy txtr -d pathToSourceDirectory -f originalText (-r newText)\
            (-l pathToListFile) (-regex)
            \nThis application will search for a text pattern recursively in all files of\
             a source directory and replace it with a new text.''',
            header: '\nAvailable options (use -h for help):\n',
            footer: 'Options -d and -f are mandatory.')

        cliBuilder.width = 80
        cliBuilder.with {
            h 'Print this help text and exit.'
            f(args: 1, argName: 'originalText',
                'Original txt or regex here. Required.')
            r(args: 1, argName: 'newText',
                'New text here. Optional. When not provided, the original text is simply deleted',
                optionalArg: true)
            d(args: 1, argName: 'pathToSource',
                'Absolute path to source directory. Required')
            l(args: 1, argName: 'pathToList',
                'Absolute path to file listing changed files. Optional.',
                optionalArg: true)
            regex(args: 1, argName: 'regex', 'String to be replaced (in -r) is treated as regex',
                optionalArg: true)
        }
    }

    void parse(args) {
        OptionAccessor options = cliBuilder.parse(args)

        if (options.h) {
            cliBuilder.usage()
            System.exit 0
        }

        if ((!options.h) && (!options.d) || (!options.f)) {
            log.error('Error while parsing command-line options.')
            println "For help use groovy txtr -h"
            System.exit 1
        }

        //I want to be able to search and replace strings containing characters
        //which are used to construct regular expressions. So I can decide.

        def regex = false

        if (options.regex) {
            regex = true
        }

        def dir = new File(options.d)
        if(dir.exists() == false) {
            log.error('Provided path to source directory is not valid.')
            System.exit 1
        }

        def originalText = options.f
        def newText = options.r
        // limiting to specific filetypes. to be discussed.
        def exts = [".txt", ".xml"]
        def backupFile
        def fileText
        def backupDir = new File(dir.path + "/backup")
        try {
            backupDir.mkdir()
        } catch(IOException exception) {
            log.error('Problem creating backup directory.')
          Sysetem.exit 1
        }

        //to create backup file only once
        Boolean firstOccurence = true

        //I am recording all lines of every file to memory. So it is not the best
        //architecture for working with large files.
        //But this way I am writing to new files only from those containing
        //searched pattern
        def lines = []

        //counter - number of line in a file
        def lineNumber = 0
        //counter - changed lines
        def changedLinesCount = 0
        //counter - changed files
        def changedFilesCount = 0
        //counter - total number of lines
        def totalLinesCount = 0
        //counter - total number of files
        def totalFilesCount = 0

        def startTime
        def finishTime
        def duration

        //now traverse through every file with selected extensions
        log.info('Processing of files started.')
        startTime = System.currentTimeMillis()
        dir.eachFileRecurse(
            {file ->
                for (ext in exts){
                    if (file.name.endsWith(ext)) {
                        totalFilesCount++
                        //now process every file line by line
                        file.eachLine {
                            line -> if((!regex && line.contains(originalText) || regex && line =~ originalText) && firstOccurence) {
                                //backing up file only when text replacement occurs
                                try {
                                    new File(file.path).withInputStream { input ->
                                        new File(backupDir.path + "/" + file.getName() + ".bak").withOutputStream {output ->
                                            output << input
                                        }
                                    }
                                } catch(Exception ex) {
                                    log.error('Problem with file backup.')
                                    System.exit 1
                                }
                                changedFilesCount++
                                log.info("File " + file.getName() + " backed up.")
                                if(options.l) {
                                    try {
                                        def listOfChangedFiles = new File(options.l)
                                        listOfChangedFiles.createNewFile()
                                        listOfChangedFiles << file << '\n'
                                    } catch(Exception ex) {
                                        log.error('Problem while creating list of changed files.')
                                    }
                                }
                                lines.add(line.replaceAll(originalText, newText))
                                log.info('Pattern found in file: ' + file.getName() + ', line: ' + (lineNumber + 1))
                                firstOccurence = false
                                lineNumber++
                                changedLinesCount++
                                totalLinesCount++
                                } else if((!regex && line.contains(originalText) || regex && line =~ originalText) && firstOccurence == false) {
                                //} else if(line =~ originalText && firstOccurence == false) {
                                    lines.add(line.replaceAll(originalText, newText))
                                    log.info('Pattern found in file: ' + file.getName() + ', line: ' + (lineNumber + 1))
                                    lineNumber++
                                    changedLinesCount++
                                    totalLinesCount++
                                } else {
                                    lines.add(line)
                                    lineNumber++
                                    totalLinesCount++
                                }
                            }

                            //rewrite the original file with processed data
                            try {
                                def writer = new File(file.path).newWriter()
                                lines.forEach {line ->
                                    writer.writeLine line
                                    }
                                    writer.flush()
                                    writer.close()
                            } catch(Exception ex) {
                                log.error('Problem when saving changes to file.')
                            }
                            lines.clear()
                            firstOccurence = true
                        }
                    }
                    lineNumber = 0
              }
          )

          log.info('Processing of files finished.')
          finishTime = System.currentTimeMillis()
          duration = (finishTime - startTime) / 1000

          println ''
          println totalFilesCount + ' file(s) with total ' + totalLinesCount + ' line(s) researched.'
          println changedFilesCount + ' file(s) with total ' + changedLinesCount + ' line(s) changed.'
          println 'Duration of processing: ' + duration + ' s'
      }
}
