package weather;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WeatherMultipleFiles {
	
	public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
		//If largestSoFar is nothing
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
			if (smallestTemp == -9999) {
				smallestTemp = 9999;
			}
			//Check if currentRow’s temperature < largestSoFar’s
			if (currentTemp < smallestTemp) {
				//If so update largestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		return smallestSoFar;
	}
	
	public CSVRecord coldestHourInFile(CSVParser parser) {
		//start with largestSoFar as nothing
		CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
		}
		//The largestSoFar is the answer
		return smallestSoFar;
	}
	
	public String coldestInManyDays() {
		DirectoryResource dr = new DirectoryResource();
		Double coldestTemp = null;
		String filename = null;
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			Double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			if (coldestTemp == null) {
				coldestTemp = currentTemp;
				filename = f.getAbsolutePath();
			}
			if (coldestTemp > currentTemp) {
				filename = f.getAbsolutePath();
				coldestTemp = currentTemp;
			}
			
		}
		return filename;		
	}
	
	public void testColdestInManyDays() {
		String filename = coldestInManyDays();
		FileResource fr = new FileResource(filename);
		CSVRecord coldestThisDay = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest day was in file " + filename.substring(filename.indexOf("weather")));
		System.out.println("Coldest temperature on that day was "+coldestThisDay.get("TemperatureF"));
		System.out.println("All the Temperatures on the coldest day were:");
		for (CSVRecord currentRow : fr.getCSVParser()) {
			System.out.println(currentRow.get("DateUTC") + " " + currentRow.get("TemperatureF"));
		}
	}
	
	public static void main(String args[]) {
		WeatherMultipleFiles wmf = new WeatherMultipleFiles();
		wmf.testColdestInManyDays();
	}

}
