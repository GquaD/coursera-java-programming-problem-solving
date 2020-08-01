package CourseraJavaProgramming.week3;

import CourseraJavaProgramming.edu.duke.FileResource;
import CourseraJavaProgramming.org.apache.commons.csv.CSVParser;
import CourseraJavaProgramming.org.apache.commons.csv.CSVRecord;

/**
 * Created by Behzod on 27, July, 2020
 */
public class Exports {
    public void tester(){
        FileResource resource = new FileResource("exportdata.csv");
        CSVParser parser = resource.getCSVParser();
        this.countryInfo(parser, "Nauru");
        parser = resource.getCSVParser();
        /*this.countryInfo(parser, "Malawi");
        parser = resource.getCSVParser();
        this.countryInfo(parser, "Uzbekistan");
        parser = resource.getCSVParser();*/
        System.out.println("Exporters of cotton and flowers: ");
        this.listExportersTwoProducts(parser, "cotton", "flowers");
        parser = resource.getCSVParser();
        this.numberOfExporters(parser, "cocoa");
        parser = resource.getCSVParser();
        this.bigExporters(parser, "$999,999,999,999");
    }

    public void countryInfo(CSVParser parser, String country) {
        boolean found = false;
        for (CSVRecord record : parser) {
            String currentCountry = record.toString();
            if (currentCountry.contains(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(country + ": " + exports + ": " + value);
                found = true;
            }
        }
        if (!found) {
            System.out.println(country + " records not found");
        }
    }

    public void listExportersTwoProducts(CSVParser parser, String item1, String item2) {
        boolean found = false;
        int count = 1;
        for (CSVRecord record : parser) {
            String currentRecord = record.get("Exports");
            if (currentRecord.contains(item1) && currentRecord.contains(item2)) {
                System.out.println(count + ". " + record.get("Country"));
                count++;
                found = true;
            }
        }
        if (!found) {
            System.out.println("Records not found");
        }
    }

    public void numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        System.out.println("Number of countries who export " + exportItem.toUpperCase() + ": " + count);
    }

    public void bigExporters(CSVParser parser, String value) {
        for (CSVRecord record : parser) {
            String dollars = record.get("Value (dollars)");
            if (dollars.length() > value.length()) {
                String country = record.get("Country");
                System.out.println(country + " " + dollars);
            }
        }
    }

    public static void main(String[] args) {
        Exports exports = new Exports();
        exports.tester();
    }
}
