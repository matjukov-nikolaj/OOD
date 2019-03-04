package com.ood.factory.shapefactory;

import com.ood.exceptions.IncorrectNumberOfArguments;
import com.ood.exceptions.UnknownColorException;
import com.ood.exceptions.UnknownShapeException;
import com.ood.exceptions.WrongAmountException;
import com.ood.factory.shape.Shape;

public interface ShapeFactory {

    Shape createShape(String description) throws UnknownShapeException, WrongAmountException, UnknownColorException, IncorrectNumberOfArguments;

}
