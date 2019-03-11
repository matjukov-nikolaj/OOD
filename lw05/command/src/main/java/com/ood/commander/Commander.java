package com.ood.commander;

import com.ood.commander.model.*;
import com.ood.commander.service.Menu;
import com.ood.exception.WrongPositionException;

import com.ood.commander.model.Constants;

public class Commander {

    private Menu menu;

    private Document document;

    public Commander() {
        this.menu = new Menu();
        this.document = new DocumentImpl();

        this.menu.addItem(Constants.INSERT_PARAGRAPH, Constants.INSERT_PARAGRAPH + " <position> <text>. Position can be \"end\"", this.insertParagraph());
        this.menu.addItem(Constants.INSERT_IMAGE, Constants.INSERT_IMAGE + " <position> <width> <height> <file path>. Position can be \"end\"", this.insertImage());
        this.menu.addItem(Constants.SET_TITLE, Constants.SET_TITLE + " <title>", this.setTitle());
        this.menu.addItem(Constants.LIST, Constants.LIST + ". " + Constants.WITHOUT_PARAMETERS, this.showDocument());
        this.menu.addItem(Constants.REPLACE_TEXT, Constants.REPLACE_TEXT + " <position> <text>", this.replaceText());
        this.menu.addItem(Constants.RESIZE_IMAGE, Constants.RESIZE_IMAGE + " <position> <width> <height>", this.resizeImage());
        this.menu.addItem(Constants.DELETE_ITEM, Constants.DELETE_ITEM + " <position>", this.deleteItem());
        this.menu.addItem(Constants.HELP, Constants.HELP + ". " + Constants.WITHOUT_PARAMETERS, this.showHelpBlock());
        this.menu.addItem(Constants.UNDO, Constants.UNDO + ". " + Constants.WITHOUT_PARAMETERS, this.undo());
        this.menu.addItem(Constants.REDO, Constants.REDO + ". " + Constants.WITHOUT_PARAMETERS , this.redo());
        this.menu.addItem(Constants.SAVE, Constants.SAVE + " <path to save>", this.save());
        this.menu.addItem(Constants.EXIT, Constants.EXIT + ". " + Constants.WITHOUT_PARAMETERS, this.exit());
    }

    public void start() {
        this.menu.run();
    }

    private int getPosition(String pos) throws WrongPositionException {
        if (pos.equalsIgnoreCase(Constants.END_POSITION)) {
            return -1;
        }
        Integer value = Integer.parseInt(pos);
        if (value < 0) {
            throw new WrongPositionException(value);
        }
        return value;
    }

    private void showErrorForCommand(String message) {
        System.out.println("Incorrect number of parameter for \"" + message + "\" command.");
        this.menu.showInstruction();
    }

    private Function insertParagraph() {
        return (String[] args) -> {
            if (args.length != 3) {
                this.showErrorForCommand(Constants.INSERT_PARAGRAPH);
                return;
            }

            try {
                int position = this.getPosition(args[1]);
                String text = args[2];
                this.document.insertParagraph(text, position);
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function insertImage() {
        return (String[] args) -> {
            if (args.length != 5) {
                this.showErrorForCommand(Constants.INSERT_IMAGE);
                return;
            }

            try {
                int position = this.getPosition(args[1]);
                int width = Integer.parseInt(args[2]);
                int height = Integer.parseInt(args[3]);
                this.document.insertImage(args[4], width, height, position);
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function setTitle() {
        return (String[] args) -> {
            if (args.length != 2) {
                this.showErrorForCommand(Constants.SET_TITLE);
                return;
            }
            try {
                this.document.setTitle(args[1]);
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function showDocument() {
        return (String[] args) -> {
            if (args.length != 1) {
                this.showErrorForCommand(Constants.LIST);
                return;
            }

            try {
                System.out.println("---------------------Elements---------------------");
                System.out.println("Title: " + this.document.getTitle());

                for (int i = 0; i < this.document.getItemsCount(); ++i) {
                    System.out.print(i + ". ");
                    DocumentItem item = this.document.getDocumentItem(i);
                    Image image = item.getImage();
                    Paragraph paragraph = item.getParagraph();

                    if (image != null) {
                        System.out.println("Image: " + image.getWidth() + " " + image.getHeight() + " " + image.getPath());
                    } else if (paragraph != null) {
                        System.out.println("Paragraph: " + paragraph.getText());
                    }
                }
                System.out.println("--------------------------------------------------");
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function replaceText() {
        return (String[] args) -> {
            if (args.length != 3) {
                this.showErrorForCommand(Constants.REPLACE_TEXT);
                return;
            }
            try {
                int position = this.getPosition(args[1]);
                DocumentItem item = this.document.getDocumentItem(position);
                Paragraph paragraph = item.getParagraph();
                if (paragraph != null) {
                    paragraph.setText(args[2]);
                } else {
                    throw new IllegalArgumentException("Element with position number = " + position + ". Is not a paragraph");
                }
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function resizeImage() {
        return (String[] args) -> {
            if (args.length != 4) {
                this.showErrorForCommand(Constants.RESIZE_IMAGE);
                return;
            }
            try {
                int position = this.getPosition(args[1]);
                int width = Integer.parseInt(args[2]);
                int height = Integer.parseInt(args[3]);
                DocumentItem item = this.document.getDocumentItem(position);
                Image image = item.getImage();
                if (image != null) {
                    image.resize(width, height);
                } else {
                    throw new IllegalArgumentException("Element with position number = " + position + ". Is not a image");
                }
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }

        };
    }

    private Function deleteItem() {
        return (String[] args) -> {
            if (args.length != 2) {
                this.showErrorForCommand(Constants.DELETE_ITEM);
                return;
            }
            try {
                int position = this.getPosition(args[1]);
                this.document.deleteItem(position);
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function showHelpBlock() {
        return (String[] args) -> {
            if (args.length != 1) {
                this.showErrorForCommand(Constants.HELP);
                return;
            }
            try {
                this.menu.showInstruction();
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function undo() {
        return (String[] args) -> {
            if (args.length != 1) {
                this.showErrorForCommand(Constants.UNDO);
                return;
            }
            if (document.canUndo()) {
                this.document.undo();
            } else {
                System.out.println("Can't undo");
            }
        };
    }

    private Function redo() {
        return (String[] args) -> {
            if (args.length != 1) {
                this.showErrorForCommand(Constants.REDO);
                return;
            }
            if (document.canRedo()) {
                this.document.redo();
            } else {
                System.out.println("Can't redo");
            }
        };
    }

    private Function save() {
        return (String[] args) -> {
            if (args.length != 2) {
                this.showErrorForCommand(Constants.SAVE);
                return;
            }
            this.document.save(args[1]);
        };
    }

    private Function exit() {
        return (String[] args) -> {
            if (args.length != 1) {
                this.showErrorForCommand(Constants.EXIT);
                return;
            }
            this.menu.exit();
        };
    }

}
