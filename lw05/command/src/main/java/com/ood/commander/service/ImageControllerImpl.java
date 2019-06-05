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

public class ImageControllerImpl implements ImageController {

    private String directory;

    private Set<String> imagesToRemove;

    public ImageControllerImpl(String path) {
        this.directory = path;
        this.checkDirectory();
        this.imagesToRemove = new HashSet<>();
    }

    public void setDirectory(String directory) {
        this.directory = directory;
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

        return newFilePath;
    }

    public void delete(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Incorrect file path: " + path);
        }
        if (file.delete()) {
            System.out.println("Successfully delete: " + path);
        } else {
            System.out.println("Deletion didn't happen: " + path);
        }
    }

    public void markForDeletion(String path, boolean remove) {
        if (remove) {
            this.imagesToRemove.add(path);
        } else {
            if (this.imagesToRemove.contains(path)) {
                this.imagesToRemove.remove(path);
            }
        }
    }

    public void deleteUnusedImages() {
        for (String path: this.imagesToRemove) {
            this.delete(path);
        }
        this.imagesToRemove.clear();
    }

    public Set<String> getImagesToRemove() {
        return this.imagesToRemove;
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
