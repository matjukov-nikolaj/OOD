package com.ood.commander;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FilenameFilter;

import static org.junit.Assert.fail;

public class Utilities {

    public static void handleImageDirectory() {
        File file = new File(Constants.IMAGE_DIRECTORY);
        try {
            if (file.exists()) {
                FileUtils.deleteDirectory(file);
            }
            if (file.mkdirs()) {
                System.out.println("Create images directory.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public static String[] getJpgFiles(File folder) {
        return folder.list(new FilenameFilter() {

            @Override public boolean accept(File folder, String name) {
                return name.endsWith(".jpg");
            }

        });
    }

}
