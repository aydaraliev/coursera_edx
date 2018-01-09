package StringsFirstAssignments;
import edu.duke.*;

public class Part4 {
	
	public void extractYouTube(URLResource url_parsed) {
		
		for(String word : url_parsed.words()) {
			String word_copy = word.toLowerCase();
			int pos = word_copy.indexOf("youtube.com");
			if (pos != -1) {
				int start = word.lastIndexOf("\"",pos);
				int end = word.indexOf("\"", pos+1);
				String link = word.substring(start+1, end);
				System.out.println(link);
				
			}
		}
		
	}
	
	public static void main (String args[]) {
		Part4 p4 = new Part4();
		URLResource url_parsed = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		p4.extractYouTube(url_parsed);
	}

}
