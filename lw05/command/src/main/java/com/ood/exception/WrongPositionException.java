package com.ood.exception;

public class WrongPositionException extends Exception {
    public WrongPositionException(int position) {
        super("Wrong position: " + position);
    }
}
