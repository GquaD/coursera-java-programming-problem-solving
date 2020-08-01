package CourseraJavaProgramming.week1;

import CourseraJavaProgramming.edu.duke.*;

import java.io.File;
import java.util.ArrayList;

public class PerimeterAssignmentRunner {

    private double largestPerimeter;
    private File largestFile;

    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        Iterable<Point> points = s.getPoints();
        for (Point p : points) {
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here

        return getPerimeter(s) / getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        ArrayList<Point> points = (ArrayList<Point>) s.getPoints();
        double largest = 0.0;
        for (int i = 0; i < points.size() - 1; i++) {
            double distance = points.get(i).distance(points.get(i + 1));
            if (largest < distance) largest = distance;
        }
        double distance = points.get(0).distance(points.get(points.size() - 1));
        if (largest < distance) largest = distance;
        return largest;
    }

    public double getLargestX(Shape s) {
        // Put code here
        ArrayList<Point> points = (ArrayList<Point>) s.getPoints();
        double largestX = 0.0;
        int num = getNumPoints(s);
        for (int i = 0; i < points.size() - 1; i++) {
            double x = points.get(i).getX();
            if (largestX < x) largestX = x;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        File folder = new File("files");
        for (File file: folder.listFiles()) {
            testPerimeter(file);
        }
        return 0.0;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here

        File temp = largestFile;    // replace this code
        return "\nFile with largest perimeter is " + temp.getName();
    }

    public void testPerimeter () {
        File file = new File("example1.txt");
        FileResource fr = new FileResource(file);
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);

        int numPoints = getNumPoints(s);
        System.out.println("number of points = " + numPoints);

        double avgLength = getAverageLength(s);
        System.out.println("average length = " + avgLength);

        double largest = getLargestSide(s);
        System.out.println("largest side = " + largest);

        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);
    }

    public void testPerimeter (File entry) {
        File file = new File(entry.getAbsolutePath());
        FileResource fr = new FileResource(file);
        Shape s = new Shape(fr);
        double perimeter = getPerimeter(s);
        System.out.println("File: " + entry.getName());
        System.out.println("perimeter = " + perimeter);

        recordLargestPerimeter(entry, perimeter);

        int numPoints = getNumPoints(s);
        System.out.println("number of points = " + numPoints);

        double avgLength = getAverageLength(s);
        System.out.println("average length = " + avgLength);

        double largest = getLargestSide(s);
        System.out.println("largest side = " + largest);

        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);
        System.out.println();
    }

    //my method
    public void recordLargestPerimeter(File file, double perimeter) {
        if (perimeter > largestPerimeter) {
            largestPerimeter = perimeter;
            largestFile = file;
        }
    }

    public void testPerimeterMultipleFiles() {
        // Put code here

    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public static void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
//        pr.testPerimeter();
        pr.getLargestPerimeterMultipleFiles();
        System.out.println(pr.getFileWithLargestPerimeter());
        System.out.println("It's perimeter = " + pr.largestPerimeter);
    }
    //1 59.45
    //2 7.66 x
    //3 9.21
    //4 62.65 x
    //5 example3
    //6 count+count
    //7 pos x and neg y
}
