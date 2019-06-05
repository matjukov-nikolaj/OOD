package com.ood.streams;

import java.io.InputStream;

public class FileInputStream implements InputDataStream {

    private InputStream inputStream;

    public FileInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public boolean isEOF() {
        return false;
    }

    @Override
    public int readBlock() {
        return 0;
    }

    @Override
    public int readByte() {
        return 0;
    }
}
