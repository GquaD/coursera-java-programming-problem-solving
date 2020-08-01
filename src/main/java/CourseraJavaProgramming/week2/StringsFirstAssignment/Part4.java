package CourseraJavaProgramming.week2.StringsFirstAssignment;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Behzod on 19, July, 2020
 */
public class Part4 {

    public File createFile(String link) throws IOException {
        URL url = new URL(link);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        File file = new File("page.html");
        PrintWriter out = new PrintWriter(file);

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            out.println(inputLine);
        //System.out.println(inputLine);
        in.close();
        out.close();
        return file;
    }

    public ArrayList<String> findLinksToYoutube(File file) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        ArrayList<String> links = new ArrayList<>();
        int count = 0;
        while (reader.readLine() != null) {
            String line = reader.readLine();
            count++;
            if (line.toLowerCase().contains("youtube.com")) {
                int startIndex = line.indexOf("http");
                int endIndex = line.indexOf("\"", startIndex + 4);
                String link = line.substring(startIndex, endIndex);
                links.add(link);
            }
        }
        return links;
    }

    public void testFindLinks() throws IOException {
        ArrayList<String> links = findLinksToYoutube(createFile("https://www.dukelearntoprogram.com//course2/data/manylinks.html"));
        if (links.size() == 0) {
            System.out.println("Empty");
        }
        for (String link : links) {
            System.out.println(link);
        }
    }


    public static void main(String[] args) throws IOException {
        Part4 part4 = new Part4();
        part4.testFindLinks();
    }
}
