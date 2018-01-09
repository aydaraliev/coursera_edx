package StringsFirstAssignments;

public class Part2 {

	public String findSimpleGene(String s, String startCodon, String stopCodon) {
		
		s = s.toUpperCase();
		startCodon = startCodon.toUpperCase();
		stopCodon = stopCodon.toUpperCase();
		
		int gene_start = s.indexOf(startCodon);
		if (gene_start == -1) {
			System.out.println("Start codon is not found");
			return "";
		}
		int gene_stop = s.indexOf(stopCodon, gene_start + 3);
		if (gene_stop == -1) {
			System.out.println("Stop codon is not found");
			return "";
		}
		if ((gene_stop - gene_start + 3) % 3 == 0) {
			//s = s.substring(gene_start, gene_stop+3).toLowerCase();
			return s.substring(gene_start, gene_stop+3);
		}
		else {
			return "";
		}
	}
	
	public void testSimpleGene() {
		String s1 = findSimpleGene("AAATGCCCTAACTAGATTAAGAAACC", "ATG", "TAA");
		String s2 = findSimpleGene("TTTCCCTAAA", "ATG", "TAA");
		String s3 = findSimpleGene("ATGAAATTTCCC", "ATG", "TAA");
		String s4 = findSimpleGene("TTTCCCGGG", "ATG", "TAA");
		String s5 = findSimpleGene("ATGAAATTTCCTAA", "ATG", "TAA");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
	}

	public static void main (String[] args) {
		Part2 p2 = new Part2();
		p2.testSimpleGene();
	}
	
}
