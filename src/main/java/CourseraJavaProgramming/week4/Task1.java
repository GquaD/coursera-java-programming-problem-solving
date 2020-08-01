package CourseraJavaProgramming.week4;

import CourseraJavaProgramming.edu.duke.DirectoryResource;
import CourseraJavaProgramming.edu.duke.FileResource;
import CourseraJavaProgramming.org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Behzod on 31, July, 2020
 */
public class Task1 {
    private FileResource fileResource = null;

    public Task1() {
        fileResource = new FileResource(("yob2012short.csv"));
    }

    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num born " + rec.get(2));
            }
        }
    }

    public void totalBirths(FileResource fileResource) {
        int totalBirths = 0, totalBoys = 0, totalGirls = 0;
        for (CSVRecord rec :
                fileResource.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births: " + totalBirths);
        System.out.println("total boys: " + totalBoys);
        System.out.println("total girls: " + totalGirls);
    }

    public void testTotalBirths() {
        this.totalBirths(fileResource);
    }

    public int getRank(int year, String name, String gender) {
        int rank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            if (f.getName().contains(year + "")) {
                FileResource fr = new FileResource(f);
                for (CSVRecord record :
                        fr.getCSVParser(false)) {
                    if (record.get(1).equals(gender)) {
                        rank++;
                        if (record.get(0).equals(name)) {
                            System.out.println("Rank of " + name + " in file " +
                                    f.getName() + " is = " + rank);
                            return rank;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public void testGetRank() {
        System.out.println(this.getRank(2013, "William", "M"));
    }

    public String getName(int year, int rank, String gender) {
        String name = "NO NAME";
        int countRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            if (f.getName().contains(year + "")) {
                FileResource fr = new FileResource(f);
                for (CSVRecord record :
                        fr.getCSVParser(false)) {
                    if (record.get(1).equals(gender)) {
                        countRank++;
                    }
                    if (countRank == rank) {
                        name = record.get(0);
                        System.out.println(name + " has rank " + rank + " in " + f.getName());
                        return name;
                    }
                }
            }
        }
        return name;
    }

    public void testGetName() {
        System.out.println(this.getName(2012, 3, "M"));
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        DirectoryResource dr = new DirectoryResource();
        String heShe = gender.toLowerCase().equals("m") ? "he" : "she";
        int rank = 0;
        for (File f : dr.selectedFiles()) {
            if (f.getName().contains(year + "")) {
                FileResource fr = new FileResource(f);
                for (CSVRecord record :
                        fr.getCSVParser(false)) {
                    if (record.get(1).equals(gender)) {
                        rank++;
                    }
                    if (record.get(0).equals(name)) {
                        break;
                    }
                }
            }
        }
        int countNewRank = 0;
        for (File f : dr.selectedFiles()) {
            if (f.getName().contains(newYear + "")) {
                FileResource fr = new FileResource(f);
                for (CSVRecord record :
                        fr.getCSVParser(false)) {
                    if (record.get(1).equals(gender)) {
                        countNewRank++;
                    }
                    if (rank == countNewRank) {
                        System.out.println(name + " born in " + year + " would be " +
                                record.get(0) + " if " + heShe + " born in " + newYear);
                        break;
                    }
                }
            }
        }
        System.out.println("not found");
    }

    public int yearOfHighestRank(String name, String gender) {
        int rank = Integer.MAX_VALUE;
        int year = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int currentRank = 0;
            FileResource fr = new FileResource(f);
            for (CSVRecord record :
                    fr.getCSVParser(false)) {
                if (record.get(1).equals(gender)) {
                    currentRank++;
                }
                if (record.get(0).equals(name)) {
                    System.out.println(name + "'s rank in " + f.getName() + " is " + currentRank);
                    if (currentRank < rank) {
                        rank = currentRank;
                        year = Integer.parseInt(f.getName().substring(3, 7));
                    }
                    break;
                }
            }
        }
        if (year == 0) {
            System.out.println("yearOfHighestRank: not found");
            return -1;
        }
        System.out.println("Highest rank of name " + name + " was in " + year + " and rank = " + rank);
        return year;
    }

    public double getAverageRank(String name, String gender) {
        int rankSum = 0;
        int countFilesWithName = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int currentRank = 0;
            FileResource fr = new FileResource(f);
            for (CSVRecord record :
                    fr.getCSVParser(false)) {
                if (record.get(1).equals(gender)) {
                    currentRank++;
                    if (record.get(0).equals(name)) {
                        System.out.println(name + "'s rank in " + f.getName() + " is " + currentRank);
                        rankSum += currentRank;
                        countFilesWithName++;
                        continue;
                    }
                }
            }
        }
        if (countFilesWithName == 0) {
            System.out.println("getAverageRank: not found");
            return -1.0;
        }
        BigDecimal bigDecimal = new BigDecimal(rankSum  * 1.0 / countFilesWithName);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.FLOOR);
        double result = bigDecimal.doubleValue();
        System.out.println("Average rank of name " + name + " is " + result);
        return result;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int result = 0;
        File currentFile = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            if (f.getName().contains(year + "")) {
                currentFile = f;
                break;
            }
        }
        if (currentFile == null) {
            System.out.println("getTotalBirthsRankedHigher: file not found");
            return -1;
        }
        FileResource fr = new FileResource(currentFile);
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) break;
                result += Integer.parseInt(record.get(2));
            }
        }
        if (result == 0) {
            System.out.println("getTotalBirthsRankedHigher: no result");
            return -1;
        }
        System.out.println("Sum of number of births higher than rank of " + name + " in " + year + " is " + result);
        return result;
    }

    public int numberOfNames(String gender){
        int countRecords = 0;
        String boysGirls = gender.toLowerCase().equals("m") ? "boys" : "girls";
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                countRecords++;
            }
        }
        System.out.println("Number of " + boysGirls + " is " + countRecords);
        return countRecords;
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        //task1.testGetRank();
        //task1.whatIsNameInYear("Noah", 2012, 2014, "M");
        //task1.yearOfHighestRank("Beka", "M");
        //task1.getAverageRank("Jacob", "M");
        //task1.getTotalBirthsRankedHigher(2013, "William", "M");
        
        task1.getTotalBirthsRankedHigher(1990, "Drew", "M");
    }
}
