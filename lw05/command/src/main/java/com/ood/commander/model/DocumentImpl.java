package com.ood.commander.model;

import com.ood.Utilities;
import com.ood.commander.command.DeleteItemCommand;
import com.ood.commander.service.History;
import com.ood.commander.command.ChangeStringCommand;
import com.ood.commander.command.InsertImageCommand;
import com.ood.commander.command.InsertParagraphCommand;
import com.ood.exception.WrongPositionException;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DocumentImpl implements Document {

    private Text title;

    private History history = new History();

    private List<DocumentItem> documentItems = new ArrayList<>();

    @Override
    public void setTitle(String title) throws Exception {
        this.title = new TextImpl(title);
        this.history.addAndExecuteCommand(new ChangeStringCommand("", title, this.title));
    }

    @Override
    public Text getTitle() {
        return title == null ? new TextImpl("") : title;
    }

    @Override
    public boolean canRedo() {
        return this.history.canRedo();
    }

    @Override
    public void redo() {
        this.history.redo();
    }

    @Override
    public void undo() {
        this.history.undo();
    }

    @Override
    public boolean canUndo() {
        return this.history.canUndo();
    }

    @Override
    public Paragraph insertParagraph(String text, int position) throws Exception {
        Paragraph paragraph = new ParagraphImpl(text, this.history);
        if (position > this.documentItems.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.history.addAndExecuteCommand(new InsertParagraphCommand(this.documentItems, paragraph, position));
        return paragraph;
    }

    @Override
    public Image insertImage(String path, int width, int height, int position) throws Exception {
        if (position < 0) {
            throw new WrongPositionException(position);
        }
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("Incorrect file path.");
        }
        String fileExtension = FilenameUtils.getExtension(path);
        String uniqueFileName = UUID.randomUUID().toString();
        String currentDir = System.getProperty("user.dir");
        String newFilePath = currentDir + "\\result\\image\\" + uniqueFileName + "." + fileExtension;
        File newFile = new File(newFilePath);
        Utilities.copyFileUsingChannel(file, newFile);

        Image image = new ImageImpl(newFilePath, width, height, this.history);
        this.history.addAndExecuteCommand(new InsertImageCommand(this.documentItems, image, position));
        return image;
    }

    @Override
    public void save(String path) {

    }

    @Override
    public void deleteItem(int index) throws Exception {
        if (index >= this.documentItems.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.history.addAndExecuteCommand(new DeleteItemCommand(index, this.documentItems));
    }

    @Override
    public DocumentItem getDocumentItem(int index) {
        return this.documentItems.get(index);
    }

    @Override
    public int getItemsCount() {
        return this.documentItems.size();
    }

}
