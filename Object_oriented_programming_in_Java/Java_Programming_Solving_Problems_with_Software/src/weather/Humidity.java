package weather;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Humidity {
	
	public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
		//If largestSoFar is nothing
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		else {
			double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
			double smallestHumidity = Double.parseDouble(smallestSoFar.get("Humidity"));
			//Check if currentRow’s Humidity < largestSoFar’s
			if (currentHumidity < smallestHumidity) {
				//If so update largestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		return smallestSoFar;
	}
	
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		//start with largestSoFar as nothing
		CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			if (currentRow.get("Humidity").equals("N/A")) {
				continue;
			}
			smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
		}
		//The largestSoFar is the answer
		return smallestSoFar;
	}
	
	public void testHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord csv = lowestHumidityInFile(parser);
		System.out.println("Lowest humidity was "+csv.get("Humidity")+ " at "+csv.get("DateUTC"));
	}
	
	public CSVRecord humidityInManyDays() {
		DirectoryResource dr = new DirectoryResource();
		CSVRecord humidityLowest = null;
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			humidityLowest = getSmallestOfTwo(currentRow, humidityLowest);
			
		}
		return humidityLowest;		
	}
	
	public void testHumidityInManyDays() {
		CSVRecord lowest = humidityInManyDays();
		System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
	}
	
	public static void main(String args[]) {
		Humidity hum = new Humidity();
//		hum.testHumidityInFile();
		hum.testHumidityInManyDays();
	}

}
