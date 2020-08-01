package CourseraJavaProgramming.week3;

import CourseraJavaProgramming.edu.duke.DirectoryResource;
import CourseraJavaProgramming.edu.duke.FileResource;
import CourseraJavaProgramming.org.apache.commons.csv.CSVParser;
import CourseraJavaProgramming.org.apache.commons.csv.CSVRecord;

import java.io.File;

/**
 * Created by Behzod on 27, July, 2020
 */
public class Weather {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public void hottestInDay() {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temp was " + largest.get("TemperatureF") + " at" + largest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            String humidity = currentRow.get("Humidity");
            if (humidity.equals("N/A")) {
                return largestSoFar;
            }
            if (currentTemp < largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar, String param) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get(param));
            double largestTemp = Double.parseDouble(largestSoFar.get(param));
            if (currentTemp < largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public void testHottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temp was " + largest.get("TemperatureF") + " at" + largest.get("DateUTC"));
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getSmallestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temp was " + coldest.get("TemperatureF") + " at" + coldest.get("DateUTC"));
    }

    public String fileWithColdestTemperature () {
        String fileName = "";
        File coldestFile = null;
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
            if (smallestSoFar.equals(currentRow)) {
                fileName = f.getName();
                coldestFile = f;
            }
        }
        FileResource fr = new FileResource(coldestFile);
        System.out.println(coldestHourInFile(fr.getCSVParser()).get("DateUTC") + ": " + coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
        return fileName;
    }

    public void testFileWithColdestTemperature() {
        System.out.println("Coldest day was in file: " + fileWithColdestTemperature());
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord currentRow : parser) {
            if (lowest == null) {
                lowest = currentRow;
            } else {
                if (currentRow.get("Humidity").equals("N/A")){
                    continue;
                }
                double currentHum = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHum = Double.parseDouble(lowest.get("Humidity"));
                if (currentHum < lowestHum) {
                    lowest = currentRow;
                }
            }
        }
        return lowest;
    }

    public double lowestHumidityInYear() {
        String fileName = "";
        File coldestFile = null;
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, "Humidity");
            if (smallestSoFar.equals(currentRow)) {
                coldestFile = f;
            }
        }
        FileResource fr = new FileResource(coldestFile);
        System.out.println("DateUTC: " + lowestHumidityInFile(fr.getCSVParser()).get("DateUTC") + ": lowest humidity: " + lowestHumidityInFile(fr.getCSVParser()).get("Humidity"));
        //System.out.println("DateUTC:" + lowestHumidityInFile(fr.getCSVParser()).get("DateUTC"));
        return Double.parseDouble(lowestHumidityInFile(fr.getCSVParser()).get("Humidity"));
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {
        int count = 0;
        double sum = 0.0;
        for (CSVRecord record: parser) {
            sum += Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        return sum / count;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avg);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int count = 0;
        double sum = 0.0;
        for (CSVRecord record: parser) {
            double humidity = Double.parseDouble(record.get("Humidity"));
            if(humidity >= value) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        return count == 0 ? 0.0 : sum / count;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avg == 0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + avg);
        }
    }



    public static void main(String[] args) {
        Weather weather = new Weather();
        //weather.lowestHumidityInYear();
        //weather.testFileWithColdestTemperature();
        //weather.testAverageTemperatureWithHighHumidityInFile();
        weather.testFileWithColdestTemperature();
    }
}
