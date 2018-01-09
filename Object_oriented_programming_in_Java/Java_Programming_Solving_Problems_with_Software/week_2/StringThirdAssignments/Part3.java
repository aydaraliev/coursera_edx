package StringThirdAssignments;
import edu.duke.*;

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

	
	public static int countOccurrences(String haystack, char needle)
	{
	    int count = 0;
	    for (int i=0; i < haystack.length(); i++)
	    {
	        if (haystack.charAt(i) == needle)
	        {
	             count++;
	        }
	    }
	    return count;
	}
	
	public float cgRatio(String dna) {
		int countC = countOccurrences(dna, 'C');
		int countG = countOccurrences(dna, 'G');
		return (countC + countG) / (float) dna.length();
	}
	
	public int countCTG(String dna) {
		int index = dna.indexOf("CTG");
		int count = 0;
		while (index != -1) {
		    count++;
		    dna = dna.substring(index + 3);
		    index = dna.indexOf("CTG");
		}
		return count;
	}
	
	public void processGenes(StorageResource sr) {
		int count = 0;
		int countCGratio = 0;
		int longestGene = 0;
		for (String gene : sr.data()) {
			if (gene.length() > 60) {
				System.out.println("This is a big gene " + gene);
				count++;
			}
			if (cgRatio(gene) > 0.35) {
				System.out.println("This gene has a high CG ratio "+gene);
				countCGratio++;
			}
			if (gene.length() > longestGene) {
				longestGene = gene.length();
			}
			
		}
		System.out.println("Genes longer than 9 is " + count);
		System.out.println("Number of genes with high CG ratio is " + countCGratio);
		System.out.println("Length of a longest gene is "+longestGene);
	}
	
	public static void main(String[] args) {
		Part3 p3 = new Part3();
		FileResource fr = new FileResource("GRch38dnapart.fa");
		String dna = fr.asString().toUpperCase();
		StorageResource sr = p3.getAllGenes(dna);
		System.out.println(sr.size());
		p3.processGenes(sr);
		System.out.println("Number of genes is " + sr.size());
		System.out.println("CTG appears " + p3.countCTG(dna)+ " times");
	}

}
