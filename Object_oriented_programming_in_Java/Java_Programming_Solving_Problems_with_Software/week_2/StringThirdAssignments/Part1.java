package StringThirdAssignments;
import edu.duke.*;

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
	
	public StorageResource getAllGenes(String dna) {
		int startIndex = 0;
		StorageResource geneList = new StorageResource();
		while (true){
			String currentGene = findGene(dna, startIndex);
			if (currentGene.isEmpty()) {
				break;
			}
			geneList.add(currentGene);
			startIndex = dna.indexOf(currentGene, startIndex) +
					     currentGene.length();
		}
		return geneList;
	}
	
	public void testOn(String dna) {
		System.out.println("Testing getAllGenes on " + dna);
		StorageResource genes = getAllGenes(dna);
		for (String g: genes.data()) {
			System.out.println(g);
		}
	}
	
	public static void main(String[] args) {
		Part1 p1 = new Part1();
		p1.testOn("ATGAAATTTCCCTAA"+ "ATGAAATTTCCCTAG"+
		          "ATGAAATTTCCCTGA"+"ATGAAATGACCCTAA");
	}


}
