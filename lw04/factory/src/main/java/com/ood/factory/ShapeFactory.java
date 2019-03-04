package com.ood.factory;

import com.ood.exceptions.UnknownColorException;
import com.ood.exceptions.UnknownShapeException;
import com.ood.exceptions.WrongAmountException;

public interface ShapeFactory {

    Shape createShape(String description) throws UnknownShapeException, WrongAmountException, UnknownColorException;

}
