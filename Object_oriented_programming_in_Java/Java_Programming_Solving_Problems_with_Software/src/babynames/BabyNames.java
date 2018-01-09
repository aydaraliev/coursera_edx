package babynames;

import edu.duke.*;

import java.io.File;

import org.apache.commons.csv.*;

public class BabyNames {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalBoysNames = 0;
		int totalGirlsNames = 0;
		int totalNames = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				totalBoysNames++;
			}
			else {
				totalGirls += numBorn;
				totalGirlsNames++;
			}
		}
		totalNames = totalGirlsNames+totalBoysNames;
		System.out.println("total births = " + totalBirths);
		System.out.println("Total girls names: "+ totalGirlsNames);
		System.out.println("female girls = " + totalGirls);
		System.out.println("Total boys names: "+ totalBoysNames);
		System.out.println("male boys = " + totalBoys);
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource();
		totalBirths(fr);
	}
	
	public int getRank(String name, String gender, FileResource fr) {
		int rank = -1;
		int count = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				count++;
				if (rec.get(0).equals(name) ) {
					rank = count;
				}
			}
		}
		return rank;
	}
	
	public void testGetRank() {
		FileResource fr = new FileResource();
		int rank = getRank("Frank", "M", fr);
		System.out.println("The rank of the name is "+rank);
	}
	
	public String getName(int year, int rank, String geneder) {
		FileResource fr = new FileResource();
		String name = "NO NAME";
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(geneder)) {
				System.out.println(rec.getRecordNumber());
				if (rank == rec.getRecordNumber()) {
					System.out.println("Yay");
					name = rec.get(0);
				}
			}
		}
		
		return name;
	}
	
	public void testGetName() {
		String name = getName(2012, 450, "M");
		System.out.println(name);
	}
	
	public void whatIsNameInYear(String name, int year, int new_year, String gender) {
		FileResource fr_1 = new FileResource();
		FileResource fr_2 = new FileResource();
		int rank_1 = 0;
		String name_2 = "NO NAME";
		int rank_2 = 0;
		for (CSVRecord rec : fr_1.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				rank_1++;
				if (rec.get(0).equals(name)) {
					break;
				}
			}
		}
		for (CSVRecord rec: fr_2.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				rank_2++;
				if (rank_2 == rank_1) {
					name_2 = rec.get(0);
					break;
				}
			}
		}
		System.out.println(name+" born in "+year+" would be "+name_2+" born in "+new_year);
	}
	
	public void testWhatIsNameInYear() {
		whatIsNameInYear("Owen", 2012, 2014, "M");
	}
	
	public int yearOfHighestRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int year = -1;
		int rank = -1;
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			int current_rank = getRank(name, gender, fr);
			if (current_rank != -1) {
				if (rank == -1) {
					rank = current_rank;
					String file_path = f.getAbsolutePath();
					int start_index = file_path.indexOf("yob") + 3;
					int stop_index = file_path.indexOf(".csv");
					year = Integer.parseInt(file_path.substring(start_index,stop_index));
				}
				else if (current_rank < rank) {
					rank = current_rank;
					String file_path = f.getAbsolutePath();
					int start_index = file_path.indexOf("yob") + 3;
					int stop_index = file_path.indexOf(".csv");
					year = Integer.parseInt(file_path.substring(start_index,stop_index));
				}
			}
			else {
				continue;
			}
		}
		return year;
	}
	
	public void testYearOfhighestRank() {
		int year = yearOfHighestRank("Mich", "M");
		System.out.println(year);
	}
	
	public Double getAverageRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		Double rank = 0.0;
		int counter = 0;
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			Double current_rank = (double) getRank(name, gender, fr);
			if (current_rank == -1) {
				continue;
			}
			else {
				counter++;
				rank += current_rank;
			}
		}
		if (rank != 0) {
			rank = rank/counter;
		}
		else {
			rank = -1.0;
		}
		return rank;
	}
	
	public void testGetAverageRank() {
		Double av_rank = getAverageRank("Robert", "M");
		System.out.println(av_rank);
	}
	
	public int getTotalBirthsRankedHigher(String name, String gender, FileResource fr) {
		int name_rank = getRank(name, gender, fr);
		int current_rank = 0;
		int sum_births_before = 0;
		if (name_rank != -1) {
			for (CSVRecord rec : fr.getCSVParser(false)) {
				if (rec.get(1).equals(gender)) {
					sum_births_before += Integer.parseInt(rec.get(2));
					current_rank++;
					if (name_rank - current_rank == 1) {
						break;
					}
				}
			}
		}
		
		return sum_births_before;
	}
	
	public void testGetTotalBirthsRankedHigher() {
		FileResource fr = new FileResource();
		int births = getTotalBirthsRankedHigher("Drew", "M", fr);
		System.out.println(births);
	}
	
	public int numberOfNames(String gender, FileResource fr) {
		int count = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				count++;
			}
		}
		return count;
	}
	
	public void testNumberOfNames() {
		FileResource fr = new FileResource();
		int num = numberOfNames("M", fr);
		System.out.println(num);
	}
	
	public static void main(String args[]) {
		BabyNames bn = new BabyNames();
//		bn.testTotalBirths();
//		bn.testGetRank();
//		bn.testGetName();
//		bn.testWhatIsNameInYear();
		bn.testYearOfhighestRank();
//		bn.testGetAverageRank();
//		bn.testGetTotalBirthsRankedHigher();
//		bn.testNumberOfNames();

	}

}
