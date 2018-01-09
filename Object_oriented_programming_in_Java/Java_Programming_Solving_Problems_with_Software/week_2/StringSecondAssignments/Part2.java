package StringSecondAssignments;

public class Part2 {
	
	public int howMany(String stringa, String stringb) {
		int count = 0;
		int currentIndex = stringb.indexOf(stringa);
		if (currentIndex == -1) {
			return 0;
		}
		while (currentIndex != -1) {
			count++;
			currentIndex = stringb.indexOf(stringa, currentIndex + stringa.length());
		}
		return count;
	}
	
	public void testHowMany() {
		System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
		System.out.println(howMany("AA", "ATAAAA"));
	}
	
	public static void main(String[] args) {
		Part2 p2 = new Part2();
		p2.testHowMany();
	}

}
