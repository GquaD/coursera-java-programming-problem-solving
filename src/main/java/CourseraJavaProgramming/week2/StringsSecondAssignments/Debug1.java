package CourseraJavaProgramming.week2.StringsSecondAssignments;

/**
 * Created by Behzod on 26, July, 2020
 */
public class Debug1 {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || index >= input.length() - 3) {
                break;
            }
            //System.out.println(index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc",index+3);
            //System.out.println("index after updating - " + index);
        }
    }

    public void test() {
        //findAbc("abcd");
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        findAbc("abcabcabcabca");
        //findAbc("abcdabc");
    }

    public static void main(String[] args) {
        Debug1 debug1 = new Debug1();
        debug1.test();
    }
}
