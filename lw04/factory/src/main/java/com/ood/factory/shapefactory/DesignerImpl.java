package com.ood.factory.shapefactory;

import com.ood.exceptions.IncorrectNumberOfArguments;
import com.ood.exceptions.UnknownColorException;
import com.ood.exceptions.UnknownShapeException;
import com.ood.exceptions.WrongAmountException;

import java.io.InputStream;
import java.util.Scanner;

public class DesignerImpl implements Designer {

    private ShapeFactory factory;

    public DesignerImpl(ShapeFactory factory) {
        this.factory = factory;
    }

    @Override
    public PictureDraft createDraft(InputStream in) {
        this.printUsage();
        PictureDraft pictureDraft = new PictureDraft();
        Scanner input = new Scanner(in);
        input.useDelimiter("\n");
        while (true) {
            System.out.print("> ");
            String inputStr = input.next().trim();
            if (inputStr.toLowerCase().equals("exit")) {
                break;
            }
            try {
                pictureDraft.addShape(this.factory.createShape(inputStr));
            } catch (UnknownShapeException
                    | WrongAmountException
                    | UnknownColorException
                    | NumberFormatException
                    | IncorrectNumberOfArguments e ) {
                this.printUsage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        return pictureDraft;
    }

    private void printUsage() {
        System.out.println(" =============================================================================================");
        System.out.println("|                                               HELP                                          |");
        System.out.println(" =============================================================================================");
        System.out.println("|                            COLORS: green, red, blue, yellow, pink, black                    |");
        System.out.println(" ---------------------------------------------------------------------------------------------");
        System.out.println("|    rectangle <leftTop.x> <leftTop.y> <rightBottom.x> <rightBottom.y> <color>                |");
        System.out.println("|    triangle <vertex1.x> <vertex1.y> <vertex2.x> <vertex2.y> <vertex3.x> <vertex3.y> <color> |");
        System.out.println("|    ellipse <center.x> <center.y> <horizontalRadius> <verticalRadius> <color>                |");
        System.out.println("|    polygon <center.x> <center.y> <vertexCount> <radius> <color>                             |");
        System.out.println(" ---------------------------------------------------------------------------------------------");
    }
}
