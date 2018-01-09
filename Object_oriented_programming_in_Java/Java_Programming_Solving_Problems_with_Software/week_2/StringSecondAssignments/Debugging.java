package StringSecondAssignments;

public class Debugging {
	
	public void findAbc(String input) {
	    int index = input.indexOf("abc");
	    while (true) {
	        if (index == -1) {
	            break;
	        }
	        else if (index >= input.length() - 3) {
	        	break;
	        }
	        System.out.println("Index " +index);
	        String found = input.substring(index+1, index+4);
	        System.out.println(found);
	        //index = input.indexOf("abc", index+4);
	        index = input.indexOf("abc",index+3);
	        System.out.println("index after updating " + index);
	    }
	}
	   public void test() {
		   findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
		   
	}
	   
	   public String mystery(String dna) {
		   int pos = dna.indexOf("T");
		   int count = 0;
		   int startPos = 0;
		   String newDna = "";
		   if (pos == -1) {
		     return dna;
		   }
		   while (count < 3) {
		     count += 1;
		     newDna = newDna + dna.substring(startPos,pos);
		     startPos = pos+1;
		     pos = dna.indexOf("T", startPos);
		     if (pos == -1) {
		       break;
		     }
		   }
		   newDna = newDna + dna.substring(startPos);
		   return newDna;
		 }
	   
	   public void testMystery() {
		   String test = mystery("ATGATTGASGAALSKVMSDKVTTTKMASNTASKMT");
		   System.out.println(test);
	   }
	   
	public static void main(String[] args) {
		Debugging db = new Debugging();
		db.test();
		db.testMystery();
	}

}
