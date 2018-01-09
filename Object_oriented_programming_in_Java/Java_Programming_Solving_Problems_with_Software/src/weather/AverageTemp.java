package weather;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class AverageTemp {
	
	public double averageTemperatureInFile(CSVParser parser) {
		double temperature_sum = 0;
		double number_of_lines = 0.0;
		for (CSVRecord record : parser)	{
			Double temp = Double.parseDouble(record.get("TemperatureF"));
			if (temp == -9999.0) {
				continue;
			}
			temperature_sum += temp;
			number_of_lines++;
		}
		
		Double average_temp = temperature_sum/number_of_lines;
		
		return average_temp;
	}
	
	public void testAverageTemperatureInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		Double average_temp = averageTemperatureInFile(parser);
		System.out.println("The average temperature in file is "+average_temp);
	}
	
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double temperature_sum = 0;
		double number_of_lines = 0.0;
		for (CSVRecord record : parser)	{
			Double humidity = Double.parseDouble(record.get("Humidity"));
			if (value <= humidity) {
				Double temp = Double.parseDouble(record.get("TemperatureF"));
				if (temp == -9999.0) {
					continue;
				}
				temperature_sum += temp;
				number_of_lines++;
			}
		}		
		Double average_temp = null;		
		if (number_of_lines != 0) {
			average_temp = temperature_sum/number_of_lines;
		}
		else {
			average_temp = 99999.0;
		}
		return average_temp;		
	}
	
	public void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		Double average_temp = averageTemperatureWithHighHumidityInFile(parser, 80);
		if (average_temp == 99999.0) {
			System.out.println("No temperatures with that humidity");
		}
		else {
			System.out.println("Average Temp when high Humidity is "+average_temp);
		}
	}
	
	public static void main(String args[]) {
		AverageTemp at = new AverageTemp();
//		at.testAverageTemperatureInFile();
		at.testAverageTemperatureWithHighHumidityInFile();
	}

}
