class ProteinTranslation {
  /*
  Translate RNA sequences into proteins.
  Codon 	Protein
  AUG 	Methionine
  UUU, UUC 	Phenylalanine
  UUA, UUG 	Leucine
  UCU, UCC, UCA, UCG 	Serine
  UAU, UAC 	Tyrosine
  UGU, UGC 	Cysteine
  UGG 	Tryptophan
  UAA, UAG, UGA 	STOP
   */

  static String strand = 'UAA'

  static void main(String[] args) {
    println proteins(strand)
  }

  static String proteins(strand) {
    def codonMap = ['AUG' :	'Methionine',
                    'UUU' : 'Phenylalanine',
                    'UUC' :	'Phenylalanine',
                    'UUA' : 'Leucine',
                    'UUG' :	'Leucine',
                    'UCU' :	'Serine',
                    'UCC' : 'Serine',
                    'UCA' : 'Serine',
                    'UCG' :	'Serine',
                    'UAU' : 'Tyrosine',
                    'UAC' : 'Tyrosine',
                    'UGU' : 'Cysteine',
                    'UGC' :	'Cysteine',
                    'UGG' :	'Tryptophan',
                    'UAA' :	'STOP',
                    'UAG' :	'STOP',
                    'UGA' :	'STOP']
    codonMap.get(strand)
  }
}
