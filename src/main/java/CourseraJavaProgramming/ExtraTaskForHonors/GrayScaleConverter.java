package CourseraJavaProgramming.ExtraTaskForHonors;

import CourseraJavaProgramming.edu.duke.DirectoryResource;
import CourseraJavaProgramming.edu.duke.ImageResource;
import CourseraJavaProgramming.edu.duke.Pixel;

import java.io.File;

/**
 * Created by Behzod on 01, August, 2020
 */
public class GrayScaleConverter {
    public ImageResource convertToGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for (Pixel pixel :
                outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = convertToGray(inImage);
            gray.setFileName("gray-" + inImage.getFileName());
            //gray.draw();
            gray.save();
        }
        System.out.println("Finished converting to gray");
    }

    public  void testGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray = convertToGray(ir);
        gray.draw();
        ir.draw();
    }

    public static void main(String[] args) {
        GrayScaleConverter converter = new GrayScaleConverter();
        converter.selectAndConvert();
    }
}
