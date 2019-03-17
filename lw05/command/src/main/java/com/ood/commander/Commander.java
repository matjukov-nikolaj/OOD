package com.ood.commander;

import com.ood.commander.model.*;
import com.ood.commander.service.Menu;
import com.ood.exception.WrongPositionException;

import java.util.List;

public class Commander {

    private Menu menu;

    private Document document;

    private static final String INSERT_PARAGRAPH = "ip";

    private static final String INSERT_IMAGE = "ii";

    private static final String SET_TITLE = "st";

    private static final String LIST = "List";

    private static final String REPLACE_TEXT = "rt";

    private static final String RESIZE_IMAGE = "ri";

    private static final String DELETE_ITEM = "di";

    private static final String HELP = "Help";

    private static final String UNDO = "Undo";

    private static final String REDO = "Redo";

    private static final String SAVE = "Save";

    private static final String EXIT = "Exit";

    private static final String WITHOUT_PARAMETERS = "Without parameters.";

    private static final String END_POSITION = "end";

    public Commander() {
        this.menu = new Menu();
        this.document = new DocumentImpl();

        this.menu.addItem(INSERT_PARAGRAPH, INSERT_PARAGRAPH + " <position> <text>. Position can be \"end\"", this.insertParagraph());
        this.menu.addItem(INSERT_IMAGE, INSERT_IMAGE + " <position> <width> <height> <file path>. Position can be \"end\"", this.insertImage());
        this.menu.addItem(SET_TITLE, SET_TITLE + " <title>", this.setTitle());
        this.menu.addItem(LIST, LIST + ". " + WITHOUT_PARAMETERS, this.showDocument());
        this.menu.addItem(REPLACE_TEXT, REPLACE_TEXT + " <position> <text>", this.replaceText());
        this.menu.addItem(RESIZE_IMAGE, RESIZE_IMAGE + " <position> <width> <height>", this.resizeImage());
        this.menu.addItem(DELETE_ITEM, DELETE_ITEM + " <position>", this.deleteItem());
        this.menu.addItem(HELP, HELP + ". " + WITHOUT_PARAMETERS, this.showHelpBlock());
        this.menu.addItem(UNDO, UNDO + ". " + WITHOUT_PARAMETERS, this.undo());
        this.menu.addItem(REDO, REDO + ". " + WITHOUT_PARAMETERS , this.redo());
        this.menu.addItem(SAVE, SAVE + " <path to save>", this.save());
        this.menu.addItem(EXIT, EXIT + ". " + WITHOUT_PARAMETERS, this.exit());
    }

    public void start() {
        this.menu.run();
    }

    private int getPosition(String pos) throws WrongPositionException {
        if (pos.equalsIgnoreCase(END_POSITION)) {
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
        return (List<String> args) -> {
            if (args.size() < 3) {
                this.showErrorForCommand(INSERT_PARAGRAPH);
                return;
            }

            try {
                int position = this.getPosition(args.get(1));
                String text = String.join(" ", args.subList(2, args.size()));

                this.document.insertParagraph(text, position);
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function insertImage() {
        return (List<String> args) -> {
            if (args.size() != 5) {
                this.showErrorForCommand(INSERT_IMAGE);
                return;
            }

            try {
                int position = this.getPosition(args.get(1));
                int width = Integer.parseInt(args.get(2));
                int height = Integer.parseInt(args.get(3));
                this.document.insertImage(args.get(4), width, height, position);
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function setTitle() {
        return (List<String> args) -> {
            if (args.size() != 2) {
                this.showErrorForCommand(SET_TITLE);
                return;
            }
            try {
                this.document.setTitle(args.get(1));
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function showDocument() {
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(LIST);
                return;
            }

            try {
                System.out.println("---------------------Elements---------------------");
                System.out.println("Title: " + (this.document.getTitle() == null ? "\"\"" : this.document.getTitle()));

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
        return (List<String> args) -> {
            if (args.size() != 3) {
                this.showErrorForCommand(REPLACE_TEXT);
                return;
            }
            try {
                int position = this.getPosition(args.get(1));
                DocumentItem item = this.document.getDocumentItem(position);
                Paragraph paragraph = item.getParagraph();
                if (paragraph != null) {
                    String text = String.join(" ", args.subList(2, args.size()));
                    paragraph.setText(text);
                } else {
                    throw new IllegalArgumentException("Element with position number = " + position + ". Is not a paragraph");
                }
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function resizeImage() {
        return (List<String> args) -> {
            if (args.size() != 4) {
                this.showErrorForCommand(RESIZE_IMAGE);
                return;
            }
            try {
                int position = this.getPosition(args.get(1));
                int width = Integer.parseInt(args.get(2));
                int height = Integer.parseInt(args.get(3));
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
        return (List<String> args) -> {
            if (args.size() != 2) {
                this.showErrorForCommand(DELETE_ITEM);
                return;
            }
            try {
                int position = this.getPosition(args.get(1));
                this.document.deleteItem(position);
            } catch (Exception e) {
                System.out.println(e.getClass() + ": "+ e.getMessage());
            }
        };
    }

    private Function showHelpBlock() {
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(HELP);
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
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(UNDO);
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
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(REDO);
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
        return (List<String> args) -> {
            if (args.size() != 2) {
                this.showErrorForCommand(SAVE);
                return;
            }
            this.document.save(args.get(1));
        };
    }

    private Function exit() {
        return (List<String> args) -> {
            if (args.size() != 1) {
                this.showErrorForCommand(EXIT);
                return;
            }
            this.menu.exit();
        };
    }

}
