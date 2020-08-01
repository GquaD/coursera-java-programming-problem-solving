package CourseraJavaProgramming.week2.StringsThirdAssignment;

import CourseraJavaProgramming.edu.duke.StorageResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Behzod on 26, July, 2020
 */
public class Part1 {
    private final String startCodon = "ATG";

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        //int currIndex = dna.indexOf(startCodon, startIndex + 1);
        int start = startIndex;
        int stopIndex = 0;
        while (stopIndex < dna.length()) {
            stopIndex = dna.indexOf(stopCodon, start + 3);
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            }
            if (stopIndex == -1) {
                return -1;
            }
            start = stopIndex + 1;
        }

        return dna.length();
    }

    public int findStopCodonSimple(String dna, int startIndex, String stopCodon) {
        //int currIndex = dna.indexOf(startCodon, startIndex + 1);
        int start = startIndex;
        int stopIndex = 0;
        stopIndex = dna.indexOf(stopCodon, start + 3);
        if (stopIndex == -1) {
            return -1;
        }
        if ((stopIndex - startIndex) % 3 == 0) {
            return stopIndex;
        }

        return -1;
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) return "";

        int indexTAA = findStopCodon(dna, startIndex, "TAA");
        int indexTAG = findStopCodon(dna, startIndex, "TAG");
        int indexTGA = findStopCodon(dna, startIndex, "TGA");

        int minStopCodon = Math.min(indexTAA, Math.min(indexTAG, indexTGA));
        if (minStopCodon == -1 || minStopCodon == dna.length()) {
            return "";
        } else return dna.substring(startIndex, minStopCodon + 3);
    }

    public void findAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            startIndex = dna.indexOf(startCodon, startIndex);
            if (startIndex == -1) {
                System.out.println("NO START");
                break;
            }

            int indexTAA = findStopCodon(dna, startIndex, "TAA");
            int indexTAG = findStopCodon(dna, startIndex, "TAG");
            int indexTGA = findStopCodon(dna, startIndex, "TGA");

            int minStopCodon = Math.min(indexTAA, Math.min(indexTAG, indexTGA));
            if (minStopCodon == -1 || minStopCodon == dna.length()) {
                System.out.println("NO STOP");
                break;
            }

            System.out.println(dna.substring(startIndex, minStopCodon + 3));
            startIndex = minStopCodon + 3;
        }
    }

    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource sr = new StorageResource();
        while (true) {
            startIndex = dna.indexOf(startCodon, startIndex);
            if (startIndex == -1) {
                //System.out.println("NO START");
                break;
            }

            int indexTAA = findStopCodonSimple(dna, startIndex, "TAA");
            int indexTAG = findStopCodonSimple(dna, startIndex, "TAG");
            int indexTGA = findStopCodonSimple(dna, startIndex, "TGA");

            int minStopCodon = Math.min(indexTAA, Math.min(indexTAG, indexTGA));

            if (indexTAA == -1 && indexTAG == -1 && indexTGA != -1) {
                minStopCodon = indexTGA;
            } else if (indexTAA == -1 && indexTGA == -1 && indexTAG != -1) {
                minStopCodon = indexTAG;
            } else if (indexTGA == -1 && indexTAG == -1 && indexTAA != -1) {
                minStopCodon = indexTAA;
            } else if (indexTAA == -1) {
                minStopCodon = Math.min(indexTGA, indexTAG);
            } else if (indexTAG == -1) {
                minStopCodon = Math.min(indexTGA, indexTAA);
            } else if (indexTGA == -1) {
                minStopCodon = Math.min(indexTAA, indexTAG);
            }
            if (minStopCodon == -1 && minStopCodon == dna.length()) {
                //System.out.println("NO STOP");
                break;
            } else if (minStopCodon == dna.length()) {
                startIndex++;
                continue;
            }

            //System.out.println(dna.substring(startIndex, minStopCodon + 3));
            sr.add(dna.substring(startIndex, minStopCodon + 3));
            startIndex = minStopCodon + 3;
        }
        return sr;
    }

    public StorageResource getAllGenes2(String dna) {
        StorageResource sr = new StorageResource();
        int startIndex = 0, countGenes = 0;
        while (dna.indexOf(startCodon, startIndex) != -1) {
            startIndex = dna.indexOf(startCodon, startIndex);
            int indexTAA = findStopCodon(dna, startIndex, "TAA");
            int indexTAG = findStopCodon(dna, startIndex, "TAG");
            int indexTGA = findStopCodon(dna, startIndex, "TGA");
            int stopIndex = dna.length();
            ArrayList<Integer> indexes = new ArrayList<>();
            indexes.add(indexTAA);
            indexes.add(indexTAG);
            indexes.add(indexTGA);
            //stopIndex = findSmallest(indexes, dna.length(), startIndex);
            stopIndex = findSmallest(indexes, dna.length(), startIndex);
            if (stopIndex == -1 || stopIndex == dna.length()) {
                startIndex += 3;
                continue;
            }
            sr.add(dna.substring(startIndex, stopIndex + 3));
            startIndex = stopIndex + 3;
        }
        return sr;
    }

    public int findSmallest(ArrayList<Integer> numbers, int length, int startIndex) {
        int smallest = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (smallest > numbers.get(i)) {
                smallest = numbers.get(i);
            }
        }
        if (smallest == -1) {
            int largest = -1;
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) > largest) {
                    largest = numbers.get(i);
                }
            }
            numbers.remove(new Integer(-1));
            numbers.remove(new Integer(largest));
            int result = Math.min(largest, numbers.get(0));
            return (result - startIndex) % 3 == 0 ? result : -1;
        }
        if (smallest == length) {
            return -1;
        }

        return (smallest - startIndex) % 3 == 0 ? smallest : -1;
    }

    public int findSmallestSimple(List<Integer> numbers, int length) {
        int smallest = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (smallest > numbers.get(i)) {
                smallest = numbers.get(i);
            }
        }
        if (smallest == -1) {
            numbers.remove(new Integer(-1));
            smallest = Math.min(numbers.get(0), numbers.get(1));
            if (smallest == -1) {
                numbers.remove(new Integer(-1));
                return numbers.get(0);
            }
            return smallest;
        }

        if (smallest == length) {
            return -1;
        }

        return -1;
    }

    public void testFindStopCodon() {
        System.out.println(findStopCodon("ATGCCGTCATAAC", 0, "TAA"));
        System.out.println(findStopCodon("AAATGCCGTCATAAC", 2, "TAA"));
        System.out.println(findStopCodon("ATGCCGTCATAGC", 0, "TAA"));
        System.out.println(findStopCodon("ATGCCGTCATAGC", 0, "TAG"));
        System.out.println(findStopCodon("ATGCCGTCATAGC", 0, "TAA"));
    }

    public void testFindGene() {
        System.out.println(findGene("ATGCCGTCATAAC"));
        System.out.println(findGene("ATGCCGTCACTAAC"));
        System.out.println(findGene("ATGCCGTGATAAC"));
        System.out.println(findGene("ATGTAGTCATAAC"));
        System.out.println(findGene("ACGCCGTCATAAC"));
    }

    public void testFindAllGenes() {
        //                 V  V  V  V  V  V  V  V  V  V  V  V  V
        findAllGenes("ATGCCGTCATAA" +
                "CATGTAG" +
                "TCATAAATGCCGTGA" +
                "TATGAC");
    }

    public void testGelAllGenes() {
        StorageResource genes = getAllGenes("ATGTAAGATGCCCTAGTCAATGCCCTGA");
        for (String gene : genes.data()) {
            System.out.println(gene);
        }
    }

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        //part1.testFindStopCodon();
        //part1.testFindGene();
        part1.testGelAllGenes();
    }
}
