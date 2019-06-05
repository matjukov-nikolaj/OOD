package com.ood.commander.command;

import com.ood.commander.model.DocumentItem;
import com.ood.commander.model.Image;
import com.ood.exception.WrongPositionException;

import java.util.List;

public class InsertImageCommand extends AbstractCommand {

    private Image image;

    private int position;

    List<DocumentItem> items;

    public InsertImageCommand(List<DocumentItem> items, Image image, int position) throws WrongPositionException {
        if (position < -1) {
            throw new WrongPositionException(position);
        }
        this.position = position;
        this.image = image;
        this.items = items;
    }

    public void destroy() {
        this.image.getController().delete(this.image.getPath());
    }

    @Override
    protected void doExecute() {
        if (this.position == -1 ) {
            this.items.add(new DocumentItem(this.image));
        } else {
            this.items.add(position, new DocumentItem(this.image));
        }
        this.image.getController().markForDeletion(this.image.getPath(), false);
    }

    @Override
    protected void doUnExecute() {
        if (this.position == -1) {
            this.items.remove(this.items.size() - 1);
        } else {
            this.items.remove(this.position);
        }
        this.image.getController().markForDeletion(this.image.getPath(), true);
    }


}
