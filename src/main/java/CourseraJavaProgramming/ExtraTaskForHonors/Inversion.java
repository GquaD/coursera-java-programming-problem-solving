package CourseraJavaProgramming.ExtraTaskForHonors;

import CourseraJavaProgramming.edu.duke.DirectoryResource;
import CourseraJavaProgramming.edu.duke.ImageResource;
import CourseraJavaProgramming.edu.duke.Pixel;

import java.io.File;

/**
 * Created by Behzod on 01, August, 2020
 */
public class Inversion {
    public ImageResource invertImage(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for (Pixel pixel :
                outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }

    public void selectAndInvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource inverted = invertImage(inImage);
            inverted.setFileName("inverted-" + inImage.getFileName());
            inverted.draw();
            inverted.save();
        }
        System.out.println("Finished inverting files");
    }

    public static void main(String[] args) {
        Inversion inversion = new Inversion();
        inversion.selectAndInvert();
    }
}
