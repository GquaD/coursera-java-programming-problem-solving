package CourseraJavaProgramming.week2.StringsThirdAssignment;

/**
 * Created by Behzod on 26, July, 2020
 */
public class Final {
    public String mystery(String dna) {
        int pos = dna.indexOf("T");
        int count = 0;
        int startPos = 0;
        String newDna = "";
        if (pos == -1) {
            return dna;
        }
        while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
                break;
            }
        }
        newDna = newDna + dna.substring(startPos);
        return newDna;
    }

    public static void main(String[] args) {
        Final fin = new Final();
        System.out.println(fin.mystery("asdfasdfsafTasdfasdfasdTasdfasdfasdfTasdfasfasdfasTasdfasdfasdfTasdfasdf"));
    }
}
