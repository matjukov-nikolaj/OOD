package com.ood.commander.command;

import com.ood.commander.model.DocumentItem;
import com.ood.commander.model.Image;
import com.ood.commander.service.ImageController;
import com.ood.exception.WrongPositionException;

import java.util.List;

public class DeleteItemCommand extends AbstractCommand {

    private List<DocumentItem> items;

    private int position;

    private DocumentItem item;

    private ImageController imageController;

    public DeleteItemCommand(int position, List<DocumentItem> items, ImageController imageController) throws WrongPositionException {
        if (position < -1) {
            throw new WrongPositionException(position);
        }
        this.items = items;
        this.position = position;
        this.item = items.get(position);
        this.imageController = imageController;
    }

    @Override
    public void remove() {
        Image image = this.item.getImage();
        if (image != null) {
            this.imageController.delete(image.getPath());
        }
    }

    public void doExecute() {
        if (this.position == -1) {
            this.items.remove(this.items.size() - 1);
        } else {
            this.items.remove(this.position);
        }
        Image image = this.item.getImage();
        if (image != null) {
            this.imageController.markForDeletion(image.getPath(), true);
        }
    }

    public void doUnExecute() {
        if (this.position == -1) {
            this.items.add(this.item);
        } else {
            this.items.add(this.position, this.item);
        }
        Image image = this.item.getImage();
        if (image != null) {
            this.imageController.markForDeletion(image.getPath(), false);
        }
    }

}
