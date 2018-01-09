package StringThirdAssignments;


public class Part2 {
	
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
	
	public void testCgRatio() {
		System.out.println(cgRatio("ATGCCATAG"));
	}
	
	public void testCountCTG() {
		System.out.println(countCTG("CTGCTGSADASDAASDASDCTGSSDACSDfCTG"));
	}
	
	public static void main(String[] args) {
		Part2 p2 = new Part2();
		p2.testCgRatio();
		p2.testCountCTG();
	}

}
