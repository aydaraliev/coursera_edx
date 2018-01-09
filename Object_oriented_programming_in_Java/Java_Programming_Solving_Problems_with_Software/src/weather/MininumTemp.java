package weather;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MininumTemp {
	
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
			//Check if currentRow’s temperature > largestSoFar’s
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
	
	public static void main(String[] args) {
		MininumTemp mt = new MininumTemp();
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord coldest = mt.coldestHourInFile(parser);
		System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
				   " at " + coldest.get("DateUTC"));
	}

}
