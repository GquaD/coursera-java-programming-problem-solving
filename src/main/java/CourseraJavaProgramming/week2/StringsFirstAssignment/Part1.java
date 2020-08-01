package CourseraJavaProgramming.week2.StringsFirstAssignment;

/**
 * Created by Behzod on 19, July, 2020
 */
public class Part1 {


    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "no start";
        }
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if (endIndex == -1) {
            return "no end";
        }
        return dna.substring(startIndex, endIndex + 3);
    }

    public void testSimpleGene() {
        printSimpleGene("AATGCATGATTAA");
        printSimpleGene("AAGAATGGATTAA");
        printSimpleGene("AACGCACGATTAA");
        printSimpleGene("AATGCACGATTAC");
        printSimpleGene("AATGCATCTAGTAGTAGATTAACTAACT");
        System.out.println();
        printSimpleGene("AAATGCCCTAACTAGATTAAGAAACC");
    }

    public void printSimpleGene(String dna) {
        System.out.println("String is: " + dna);
        System.out.println("Gene found: " + findSimpleGene(dna));
    }

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testSimpleGene();
    }
}
