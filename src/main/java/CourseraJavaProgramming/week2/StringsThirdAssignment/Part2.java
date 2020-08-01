package CourseraJavaProgramming.week2.StringsThirdAssignment;

import CourseraJavaProgramming.edu.duke.FileResource;
import CourseraJavaProgramming.edu.duke.StorageResource;

/**
 * Created by Behzod on 26, July, 2020
 */
public class Part2 {
    public double cgRatio(String dna) {
        int countC = 0;
        int countG = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C') {
                countC++;
            } else if (dna.charAt(i) == 'G') {
                countG++;
            }
        }
        int sum = countC + countG;
        return (double) sum / dna.length();
    }

    public int countCTG(String dna) {
        int num = 0, pos = 0;
        while (dna.indexOf("CTG", pos) != -1) {
            num++;
            pos = dna.indexOf("CTG", pos) + 3;
        }
        return num;
    }

    public void printStringsLonger60AndNumber(StorageResource sr) {
        System.out.println("Strings longer than 60: ");
        int count = 0;
        for (String s : sr.data()) {
            if (s.length() > 60) {
                count++;
                System.out.println(s);
            }
        }
        System.out.println("Number of strings longer 60: " + count);
    }

    public void printCGRatioStringsAndNumber(StorageResource sr) {
        System.out.println("Strings with CG ration higher than 35%: ");
        int count = 0;
        for (String s : sr.data()) {
            if (cgRatio(s) > 0.35) {
                count++;
                System.out.println(s);
            }
        }
        System.out.println("Number of strings with CG ration higher than 35%: " + count);
    }

    public void processGenes(StorageResource sr) {
        //printStringsLonger60AndNumber(sr);
        //printCGRatioStringsAndNumber(sr);
        int longest = 0, longer60 = 0, cgRatio = 0;
        for (String s : sr.data()) {
            Part1 part1 = new Part1();
            StorageResource temp = part1.getAllGenes2(s);
            System.out.println("Number of genes: " + temp.size());
            for (String gene : temp.data()) {
                if (gene.length() > longest) {
                    System.out.println(gene);
                    longest = gene.length();
                }
                if (gene.length() > 60) {
                    longer60++;
                }
                if (cgRatio(gene) > 0.35) {
                    cgRatio++;
                }
            }
        }
        System.out.println("Longest gene length is " + longest);
        System.out.println("Number of genes longer 60: " + longer60);
        System.out.println("Number of genes with cgRatio higher than 35%: " + cgRatio);
    }

    public void testCGRatio() {
        System.out.println(cgRatio("ATGCCATAG"));
    }

    public void testCountCTG() {
        System.out.println(countCTG("CTGCTGCCCCCTGCT"));
    }

    public void testCountCTGFile() {
        FileResource fr = new FileResource("D:\\Paymo\\codewars\\src\\main\\java\\CourseraJavaProgramming\\week2\\StringsThirdAssignment\\GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println("CTG appearances: " + countCTG(dna));
    }

    public void testProcessGenes() {
        //FileResource fr = new FileResource("D:\\Paymo\\codewars\\src\\main\\java\\CourseraJavaProgramming\\week2\\StringsThirdAssignment\\brca1line.fa");
        FileResource fr = new FileResource("D:\\Paymo\\codewars\\src\\main\\java\\CourseraJavaProgramming\\week2\\StringsThirdAssignment\\GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        StorageResource sr = new StorageResource();
        /*sr.add("ATGTGACCATGCCCTAGAAAATGTTTCCCTAAA");
        sr.add("ATGTTA");
        sr.add("ACCACACATGAAA");
        sr.add("ATGAAATTTCCCGGGTAA");*/
        sr.add(dna);
        processGenes(sr);
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        //part2.testCGRatio();
        //part2.testCountCTG();
        part2.testProcessGenes();
        part2.testCountCTGFile();
    }
}
