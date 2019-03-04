package com.ood.factory.shapefactory;

import com.ood.exceptions.UnknownColorException;
import com.ood.exceptions.UnknownShapeException;
import com.ood.exceptions.WrongAmountException;
import com.ood.factory.shape.*;

public class ShapeFactoryImpl implements ShapeFactory {

    @Override
    public Shape createShape(String description) throws UnknownShapeException, WrongAmountException, UnknownColorException {

        String[] parsedDescription = description.split(" ");
        switch (parsedDescription[0].toLowerCase()) {
            case "rectangle":
                return this.createRectangle(parsedDescription);
            case "triangle":
                return this.createTriangle(parsedDescription);
            case "ellipse":
                return this.createEllipse(parsedDescription);
            case "polygon":
                return this.createPolygon(parsedDescription);
            default:
                break;
        }
        throw new UnknownShapeException();
    }

    private Shape createRectangle(String[] parsedDescription) throws UnknownColorException {
        if (parsedDescription.length != 6) {
            throw new IllegalArgumentException();
        }
        Point leftTop = new Point(Double.parseDouble(parsedDescription[1]), Double.parseDouble(parsedDescription[2]));
        Point rightBottom = new Point(Double.parseDouble(parsedDescription[3]), Double.parseDouble(parsedDescription[4]));
        Color color = Color.createFromString(parsedDescription[5]);

        return new Rectangle(leftTop, rightBottom, color);
    }

    private Shape createTriangle(String[] parsedDescription) throws UnknownColorException {
        if (parsedDescription.length != 8) {
            throw new IllegalArgumentException();
        }
        Point vertex1 = new Point(Double.parseDouble(parsedDescription[1]), Double.parseDouble(parsedDescription[2]));
        Point vertex2 = new Point(Double.parseDouble(parsedDescription[3]), Double.parseDouble(parsedDescription[4]));
        Point vertex3 = new Point(Double.parseDouble(parsedDescription[5]), Double.parseDouble(parsedDescription[6]));
        Color color = Color.createFromString(parsedDescription[7]);

        return new Triangle(vertex1, vertex2, vertex3, color);
    }

    private Shape createEllipse(String[] parsedDescription) throws UnknownColorException {
        if (parsedDescription.length != 6) {
            throw new IllegalArgumentException();
        }
        Point center = new Point(Double.parseDouble(parsedDescription[1]), Double.parseDouble(parsedDescription[2]));
        double horizontalRadius = Double.parseDouble(parsedDescription[3]);
        double verticalRadius = Double.parseDouble(parsedDescription[4]);
        Color color = Color.createFromString(parsedDescription[5]);

        return new Ellipse(center, horizontalRadius, verticalRadius, color);
    }

    private Shape createPolygon(String[] parsedDescription) throws WrongAmountException, UnknownColorException {
        if (parsedDescription.length != 6) {
            throw new IllegalArgumentException();
        }
        Point center = new Point(Double.parseDouble(parsedDescription[1]), Double.parseDouble(parsedDescription[2]));
        int vertexCount = Integer.parseInt(parsedDescription[3]);
        double radius = Double.parseDouble(parsedDescription[4]);
        Color color = Color.createFromString(parsedDescription[5]);

        return new RegularPolygon(center, vertexCount, radius, color);
    }
}
