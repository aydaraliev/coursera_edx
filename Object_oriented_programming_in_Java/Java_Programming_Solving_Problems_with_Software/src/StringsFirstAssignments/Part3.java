package StringsFirstAssignments;

public class Part3 {	
	
	public boolean twoOccurrences(String stringa, String stringb) {
		
		int count = 0;
		int pos = 0;
		
		while(pos != -1) {
			pos = stringb.indexOf(stringa, pos);
			if(pos != -1) {
				count ++;
				pos += stringa.length();
			}
		}
		
		if (count >= 2) {
			return true;
		}
		
		else {
			return false;
		}
		
	}
	
	public String lastPart(String stringa, String stringb) {
		int pos = stringb.indexOf(stringa);
		if (pos != -1) {
			pos = pos + stringa.length();
			return stringb.substring(pos);
		}
		else {
			return stringb;
		}
	}
	
	public void methodTesting() {
		System.out.println("by");
		System.out.println("A story by Abby Long");
		System.out.println(twoOccurrences("atg", "ctgtatgta"));
		System.out.println("an");
		System.out.println("banana");
		System.out.println(lastPart("zoo", "forest"));
	}
	
	
	
	public static void main (String args[]) {
		Part3 p3 = new Part3();
		p3.methodTesting();
	}

}
