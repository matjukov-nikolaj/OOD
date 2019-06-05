package com.ood.commander.service;

import java.util.Set;

public interface ImageController {

    void setDirectory(String directory);

    String add(String path);

    void delete(String path);

    void markForDeletion(String path, boolean remove);

    void deleteUnusedImages();

    Set<String> getImagesToRemove();


}
