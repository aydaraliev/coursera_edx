package StringSecondAssignments;


public class Part1 {	
	public int findStopCodon(String dna, int startIndex, String stopCodon) {
		int currentIndex = dna.indexOf(stopCodon, startIndex+3);
		while(currentIndex != -1) {
			if ((currentIndex - startIndex)%3 == 0) {
				return currentIndex;
			}
			else {
				currentIndex = dna.indexOf(stopCodon, currentIndex+1);
			}
		}
		return dna.length();
	}
	public String findGene(String dna) {
		int startIndex = dna.indexOf("ATG");
		if (startIndex == -1) {
			return "";
		}
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		int temp = Math.min(taaIndex, tagIndex);
		int minIndex = Math.min(temp, tgaIndex);
		if(minIndex == dna.length()) {
			return "";
		}
		return dna.substring(startIndex, minIndex+3);		
	}
	
	public void printAllGenes(String dna) {
		int startIndex = 0;
		while(true) {
			startIndex = dna.indexOf("ATG", startIndex);
			if (startIndex == -1) {
				System.out.println("Start codon not found");
				break;
			}
			int taaIndex = findStopCodon(dna, startIndex, "TAA");
			int tagIndex = findStopCodon(dna, startIndex, "TAG");
			int tgaIndex = findStopCodon(dna, startIndex, "TGA");
			int temp = Math.min(taaIndex, tagIndex);
			int minIndex = Math.min(temp, tgaIndex);
			if(minIndex == dna.length()) {
				System.out.println("Stop codon not found");
				break;
			}
			System.out.println(dna.substring(startIndex, minIndex+3));
			startIndex = minIndex+3;		
		}
	}
	
	public void testFindStopCodon() {
		System.out.println(findStopCodon("ATGAAATTTCCCTAA", 0, "TAA"));
		System.out.println(findStopCodon("ATGAAATTTCCCCCC", 0, "TAA"));
		System.out.println(findStopCodon("ATGTTTAAATAA", 0, "TAA"));
	}
	
	public void testFindGene() {
		System.out.println(findGene("ATGAAATTTCCCTAA"));
		System.out.println(findGene("ATGAAATTTCCCTAG"));
		System.out.println(findGene("ATGAAATTTCCCTGA"));
		System.out.println(findGene("ATGAAATGACCCTAA"));
	}
	
	public static void main(String[] args) {
		Part1 p1 = new Part1();
		p1.printAllGenes("ATGAAATTTCCCTAA"+"ATGAAATTTCCCTAG"+"ATGAAATTTCCCTGA"+"ATGAAATGACCCTAA");
	}
	
}
