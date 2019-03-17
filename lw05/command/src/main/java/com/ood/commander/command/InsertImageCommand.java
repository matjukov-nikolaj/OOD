package com.ood.commander.command;

import com.ood.commander.model.DocumentItem;
import com.ood.commander.model.Image;
import com.ood.commander.service.ImageController;
import com.ood.exception.WrongPositionException;

import java.util.List;

public class InsertImageCommand extends AbstractCommand {

    private Image image;

    private int position;

    List<DocumentItem> items;

    private ImageController imageController;

    public InsertImageCommand(List<DocumentItem> items, Image image, int position, ImageController imageController) throws WrongPositionException {
        if (position < -1) {
            throw new WrongPositionException(position);
        }
        this.position = position;
        this.image = image;
        this.items = items;
        this.imageController = imageController;
    }

    public void remove() {
        this.imageController.delete(this.image.getPath());
    }

    @Override
    protected void doExecute() {
        if (this.position == -1 ) {
            this.items.add(new DocumentItem(this.image));
        } else {
            this.items.add(position, new DocumentItem(this.image));
        }
        this.imageController.markForDeletion(this.image.getPath(), false);
    }

    @Override
    protected void doUnExecute() {
        if (this.position == -1) {
            this.items.remove(this.items.size() - 1);
        } else {
            this.items.remove(this.position);
        }
        this.imageController.markForDeletion(this.image.getPath(), true);
    }


}
