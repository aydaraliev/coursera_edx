package StringsFirstAssignments;

public class Part1 {

	public String findSimpleGene(String s) {
		int gene_start = s.indexOf("ATG");
		if (gene_start == -1) {
			return "";
		}
		int gene_stop = s.indexOf("TAA", gene_start + 3);
		if (gene_stop == -1) {
			return "";
		}
		if ((gene_stop - gene_start + 3) % 3 == 0) {
			return s.substring(gene_start, gene_stop+3);
		}
		else {
			return "";
		}
	}
	
	public void testSimpleGene() {
		String s1 = findSimpleGene("ATGAAATTTCCCTAA");
		String s2 = findSimpleGene("TTTCCCTAAA");
		String s3 = findSimpleGene("ATGAAATTTCCC");
		String s4 = findSimpleGene("TTTCCCGGG");
		String s5 = findSimpleGene("ATGAAATTTCCTAA");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
	}
	
	public static void main (String[] args) {
		Part1 p1 = new Part1();
		p1.testSimpleGene();
	}
}
