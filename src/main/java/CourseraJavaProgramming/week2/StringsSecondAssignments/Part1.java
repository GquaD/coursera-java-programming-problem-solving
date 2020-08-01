package CourseraJavaProgramming.week2.StringsSecondAssignments;

/**
 * Created by Behzod on 24, July, 2020
 */
public class Part1 {
    private final String startCodon = "ATG";

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        //int currIndex = dna.indexOf(startCodon, startIndex + 1);
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if ((stopIndex - startIndex) % 3 == 0) {
            return stopIndex;
        }
        return dna.length();
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
        }
        else return dna.substring(startIndex, minStopCodon + 3);
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

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        //part1.testFindStopCodon();
        //part1.testFindGene();
        part1.testFindAllGenes();
    }
}
