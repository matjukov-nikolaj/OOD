package com.ood;

import java.io.*;
import java.nio.channels.FileChannel;

public class Utilities {

    public static void copyFileUsingChannel(File source, File dest) {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel destChannel = new FileOutputStream(dest).getChannel();){
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void swap(String first, String second) {
        first = first + second;
        second = first.substring(0, (first.length() - second.length()));
        first = first.substring(second.length());
    }

}
