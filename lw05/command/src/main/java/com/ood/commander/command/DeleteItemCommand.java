package com.ood.commander.command;

import com.ood.commander.model.DocumentItem;
import com.ood.commander.model.Image;
import com.ood.commander.service.ImageControllerImpl;
import com.ood.exception.WrongPositionException;

import java.util.List;

public class DeleteItemCommand extends AbstractCommand {

    private List<DocumentItem> items;

    private int position;

    private DocumentItem item;

    public DeleteItemCommand(int position, List<DocumentItem> items) throws WrongPositionException {
        if (position < -1) {
            throw new WrongPositionException(position);
        }
        this.items = items;
        this.position = position;
        this.item = items.get(position);
    }

    @Override
    public void destroy() {
        Image image = this.item.getImage();
        if (image != null) {
            image.getController().delete(image.getPath());
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
            image.getController().markForDeletion(image.getPath(), true);
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
            image.getController().markForDeletion(image.getPath(), false);
        }
    }

}
