package CourseraJavaProgramming.week2.StringsSecondAssignments;

/**
 * Created by Behzod on 26, July, 2020
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0, tempIndex = 0;
        while(stringb.indexOf(stringa, tempIndex) != -1) {
            count++;
            tempIndex = stringb.indexOf(stringa, tempIndex) + stringa.length();
        }
        return count;
    }

    public void testHowMany(){
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testHowMany();
    }
}
