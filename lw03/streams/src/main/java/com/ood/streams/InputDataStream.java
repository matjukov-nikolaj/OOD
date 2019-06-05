package com.ood.streams;

public interface InputDataStream {

    boolean isEOF();

    int readByte();

    int readBlock();

}
