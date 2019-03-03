package com.ood.factory;

import com.ood.exceptions.UnknownShapeException;

public interface ShapeFactory {

    Shape createShape(String description) throws UnknownShapeException;

}
