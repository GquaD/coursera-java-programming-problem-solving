package CourseraJavaProgramming.week2.StringsFirstAssignment;

/**
 * Created by Behzod on 19, July, 2020
 */
public class Part3 {


    public boolean twoOccurrences(String sample, String text) {
        String[] parts = text.split(sample);
        return parts.length > 1;
    }

    public boolean showTwoOccurences(String sample, String text) {
        int number = 0;
        int index = 0;
        while (text.indexOf(sample, index + sample.length()) != -1) {
            index = text.indexOf(sample, index + sample.length());
            number++;
        }
        return number > 1;
    }

    public void testTwoOccurrences() {
        printSimpleGene("by", "A story by Abby Long");
        printSimpleGene("a", "banana");

        printSimpleGene2("by", "A story by Abby Long");
        printSimpleGene2("a", "banana");

        //test lastPart()
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("b", "banana"));
        System.out.println(lastPart("o", "forest"));
        System.out.println(lastPart("zoo", "forest"));
    }

    public String lastPart(String a, String b) {
        if (!b.contains(a)) {
            return b;
        }
        int index = b.indexOf(a) + a.length();
        return b.substring(index);
    }

    public void printSimpleGene(String sample, String text) {
        System.out.println("1. Sample \"" + sample + "\" from text - " + text);
        System.out.println("Two or more occurrences: " + twoOccurrences(sample, text));
    }

    public void printSimpleGene2(String sample, String text) {
        System.out.println("2. Sample \"" + sample + "\" from text - " + text);
        System.out.println("Two or more occurrences: " + showTwoOccurences(sample, text));
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testTwoOccurrences();
    }
}
