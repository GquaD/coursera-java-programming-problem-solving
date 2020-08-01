package CourseraJavaProgramming.week2.StringsSecondAssignments;

/**
 * Created by Behzod on 26, July, 2020
 */
public class Part3{
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

    public int findAllGenes(String dna) {
        int startIndex = 0, count = 0;
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
            count++;
        }
        return count;
    }

    public int countGenes(String dna) {
        return findAllGenes(dna);
    }

    public void testCountGenes() {
        //System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
        //System.out.println(countGenes("ATGTAAGATGCCCTAGTCAATGCCCTGA"));
        System.out.println(countGenes("AATGCTAACTAGCTGACTAAT"));
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testCountGenes();
    }
}
