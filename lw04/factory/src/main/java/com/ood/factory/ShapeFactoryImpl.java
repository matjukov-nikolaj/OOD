package com.ood.factory;

import com.ood.exceptions.UnknownShapeException;

public class ShapeFactoryImpl implements ShapeFactory {

    @Override
    public Shape createShape(String description) throws UnknownShapeException {

        String[] descriptionArray = description.split(" ");
        switch (descriptionArray[0].toLowerCase()) {
            case "rectangle":
                break;
            case "triangle":
                break;
            case "ellipse":
                break;
            case "polygon":
                break;
            default:
                throw new UnknownShapeException();
                break;
        }

    }
}
