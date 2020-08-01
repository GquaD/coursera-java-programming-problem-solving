package CourseraJavaProgramming.ExtraTaskForHonors;

import CourseraJavaProgramming.edu.duke.DirectoryResource;
import CourseraJavaProgramming.edu.duke.ImageResource;

import java.io.File;

/**
 * Created by Behzod on 01, August, 2020
 */
public class ImageSaver {
    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            String newName = "gray-" + fname;
            image.setFileName(newName);
            image.draw();
            image.save();
        }
    }

    public static void main(String[] args) {
        ImageSaver imageSaver = new ImageSaver();
        imageSaver.doSave();
    }
}
