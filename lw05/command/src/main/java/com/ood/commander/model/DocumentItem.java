package com.ood.commander.model;

public class DocumentItem{

    private Paragraph paragraph = null;

    private Image image = null;

    public DocumentItem(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    public DocumentItem(Image image) {
        this.image = image;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public Image getImage() {
        return image;
    }

}
