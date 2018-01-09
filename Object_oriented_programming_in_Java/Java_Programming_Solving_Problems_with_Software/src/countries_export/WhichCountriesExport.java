package countries_export;

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
	
	public CSVParser test(FileResource fr) {
		CSVParser parser = fr.getCSVParser();
		
		return parser;
	}
	
	public void countryInfo(CSVParser parser, String countryOfInterest) {
		for (CSVRecord record : parser) {
			String country = record.get("Country");
			if (country.contains(countryOfInterest)) {
				String country_out = record.get("Country");
				String exports_out = record.get("Exports");
				String price_value_out = record.get("Value (dollars)");
				System.out.println(country_out + ": " + exports_out + ": " 
				+ price_value_out);
				
			}
		}
	}
	
	public void listExportersTwoProducts(CSVParser parser, String exp1, 
			String exp2) {
		for (CSVRecord record : parser) {
			String exports = record.get("Exports");
			if (exports.contains(exp1) && exports.contains(exp2)) {
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}
	
	public void numberOfExporters(CSVParser parser, String exportItem) {
		int count = 0;
		for (CSVRecord record: parser) {
			String exports = record.get("Exports");
			if (exports.contains(exportItem)) {
				count++;
			}
		}
		System.out.println(count);
	}
	
	public void bigExporters(CSVParser parser, String amount) {
		for (CSVRecord record: parser) {
			String country_amount = record.get("Value (dollars)");
			int amount_num = amount.length();
			int country_amount_num = country_amount.length();
			if (amount_num < country_amount_num) {
				System.out.println(record.get("Country")+": "+country_amount);
			}
		}
	}
	
	public static void main(String[] args) {
		
		WhichCountriesExport wce = new WhichCountriesExport();
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
//		wce.countryInfo(parser, "Nauru");
//		wce.listExportersTwoProducts(parser, "cotton", "flowers");
//		wce.numberOfExporters(parser, "cocoa");
		wce.bigExporters(parser, "$999,999,999,999");
		
		
	}

}
