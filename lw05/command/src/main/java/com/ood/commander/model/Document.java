package com.ood.commander.model;

public interface Document {

    Paragraph insertParagraph(String text, int position) throws Exception;

    Image insertImage(String path, int width, int height, int position) throws Exception;

    int getItemsCount();

    DocumentItem getDocumentItem(int index);

    void deleteItem(int index) throws Exception;

    Text getTitle();

    void setTitle(String title) throws Exception;

    boolean canUndo();

    void undo();

    boolean canRedo();

    void redo();

    void save(String path);

}
