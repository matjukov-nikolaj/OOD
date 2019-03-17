package com.ood.commander.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ImageController {

    private String directory;

    private Set<String> images;

    private static final String IMAGE_FOLDER = "\\images\\";

    public ImageController() {
        this.directory = System.getProperty("user.dir") + IMAGE_FOLDER;
        this.checkDirectory();
        this.images = new HashSet<>();
    }

    public String add(String path) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("Incorrect file path: " + path);
        }
        String fileExtension = FilenameUtils.getExtension(path);
        String uniqueFileName = UUID.randomUUID().toString();
        String newFilePath = this.directory + uniqueFileName + "." + fileExtension;
        File newFile = new File(newFilePath);
        copyFileUsingChannel(file, newFile);

        this.images.add(newFilePath);
        return newFilePath;
    }

    public void delete(String path) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("Incorrect file path: " + path);
        }
        if (file.delete()) {
            System.out.println("Successfully delete: " + path);
        } else {
            System.out.println("Deletion didn't happen: " + path);
        }
        this.images.remove(path);
    }

    public void handle(String path) {
        if (this.images.contains(path)) {
            this.images.remove(path);
        } else {
            this.images.add(path);
        }
    }

    private void copyFileUsingChannel(File source, File dest) {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel destChannel = new FileOutputStream(dest).getChannel();){
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkDirectory() {
        File dir = new File(this.directory);
        if (dir.exists()) {
            try {
                FileUtils.cleanDirectory(dir);
            } catch (Exception e) {
                System.out.println(e.getClass() + ": " + e.getMessage());
            }
        } else {
            if (dir.mkdirs()) {
                System.out.println("Create the directory: " + directory);
            }
        }

    }

}
