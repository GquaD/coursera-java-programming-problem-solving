package CourseraJavaProgramming.week2.StringsFirstAssignment;

/**
 * Created by Behzod on 19, July, 2020
 */
public class Part2 {


    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "no start";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1) {
            return "no end";
        }
        return dna.substring(startIndex, stopIndex + 3);
    }

    public void testSimpleGene() {
        printSimpleGene("AATGCATGATTAA", "ATG", "TAA");
        printSimpleGene("AAGAATGGATTAA", "ATG", "TAA");
        printSimpleGene("AACGCACGATTAA", "ATG", "TAA");
        printSimpleGene("AATGCACGATTAC", "ATG", "TAA");
        printSimpleGene("AATGCATCTAGTAGTAGATTAACTAACT", "ATG", "TAA");
        printSimpleGene(("AATGCATCTAGTAGTAGATTAACTAACT".toLowerCase()), "atg", "taa");
        //I don't even need to use toLowerCase() or toUpperCase() methods
        //since I'm passing String and it shows result from that String
        //whether it is uppercase or lowercase
    }

    public void printSimpleGene(String dna, String start, String stop) {
        System.out.println("String is: " + dna);
        System.out.println("Gene found: " + findSimpleGene(dna, start, stop));
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testSimpleGene();
    }
}
