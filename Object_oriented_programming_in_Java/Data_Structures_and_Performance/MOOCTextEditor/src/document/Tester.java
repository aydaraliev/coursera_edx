package document;

public class Tester extends Document {
	
	protected Tester(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getNumWords() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumSentences() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumSyllables() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args) {
		Tester s = new Tester("one (1), two (2), three (3)");
		System.out.println(s.getTokens("[^,]+"));
		
		String s1 = "My String";
		String text = s1;
		
		System.out.println(text);
	}

}
