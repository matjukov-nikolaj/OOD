package com.ood.commander.command;

import com.ood.commander.model.DocumentItem;
import com.ood.commander.model.Paragraph;
import com.ood.exception.WrongPositionException;

import java.util.List;

public class InsertParagraphCommand extends AbstractCommand {

    private Paragraph paragraph;

    int position;

    List<DocumentItem> items;

    public InsertParagraphCommand(List<DocumentItem> items, Paragraph paragraph, int position) throws WrongPositionException {
        if (position < -1) {
            throw new WrongPositionException(position);
        }
        this.position = position;
        this.items = items;
        this.paragraph = paragraph;
    }

    @Override
    public void destroy() {

    }

    @Override
    protected void doExecute() {
        if (this.position == -1 ) {
            this.items.add(new DocumentItem(this.paragraph));
        } else {
            this.items.add(position, new DocumentItem(this.paragraph));
        }
    }

    @Override
    protected void doUnExecute() {
        if (this.position == -1) {
            this.items.remove(this.items.size() - 1);
        } else {
            this.items.remove(this.position);
        }
    }
}
