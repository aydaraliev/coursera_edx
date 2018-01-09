package StringSecondAssignments;

public class Part3 {
	
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
		return -1;
	}
	public String findGene(String dna, int where) {
		int startIndex = dna.indexOf("ATG", where);
		if (startIndex == -1) {
			return "";
		}
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		//int temp = Math.min(taaIndex, tagIndex);
		//int minIndex = Math.min(temp, tgaIndex);
		int minIndex = 0;
		if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
			minIndex = tgaIndex;
		}
		else {
			minIndex = taaIndex;
		}
		if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
			minIndex = tagIndex;
		}
		
		if(minIndex == -1) {
			return "";
		}
		return dna.substring(startIndex, minIndex+3);		
	}
	
	public int countAllGenes(String dna) {
		int startIndex = 0;
		int count = 0;
		while(true) {
			startIndex = dna.indexOf("ATG", startIndex);
			if (startIndex == -1) {
				return count;
			}
			int taaIndex = findStopCodon(dna, startIndex, "TAA");
			int tagIndex = findStopCodon(dna, startIndex, "TAG");
			int tgaIndex = findStopCodon(dna, startIndex, "TGA");
			int temp = Math.min(taaIndex, tagIndex);
			int minIndex = Math.min(temp, tgaIndex);
			if(minIndex == dna.length()) {
				return count;
			}
			count++;
			startIndex = minIndex+3;		
		}
	}
	
	public void printAllGenes(String dna) {
		int startIndex = 0;
		while (true){
			String currentGene = findGene(dna, startIndex);
			if (currentGene.isEmpty()) {
				break;
			}
			System.out.println(currentGene);
			startIndex = dna.indexOf(currentGene, startIndex) +
					     currentGene.length();
		}
	}
	
	public int countAllGenes2(String dna) {
		int startIndex = 0;
		int count = 0;
		while (true){
			String currentGene = findGene(dna, startIndex);
			if (currentGene.isEmpty()) {
				return count;
			}
			count++;
			startIndex = dna.indexOf(currentGene, startIndex) +
					     currentGene.length();
		}
	}
	
	public void testCountAllGenes2() {
		System.out.println(countAllGenes2("ATGTAAGATGCCCTAGT"));
		System.out.println(countAllGenes2("ATGAAATTTCCCTAA"+
		"ATGAAATTTCCCTAG"+"ATGAAATTTCCCTGA"+"ATGAAATGACCCTAA"));
		System.out.println(countAllGenes2("ATGAAATTTCCC"));
	}
	
	public void testPrintAllGenes() {
		printAllGenes("ATGTAAGATGCCCTAGT");
		printAllGenes("ATGAAATTTCCCTAA"+
		"ATGAAATTTCCCTAG"+"ATGAAATTTCCCTGA"+"ATGAAATGACCCTAA");
		printAllGenes("ATGAAATTTCCC");
	}
	
	public static void main(String[] args) {
		Part3 p3 = new Part3();
		System.out.println(p3.findGene("AATGCTAACTAGCTGACTAAT",0));
		p3.testPrintAllGenes();
		p3.testCountAllGenes2();
	}

}
