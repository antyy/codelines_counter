package com.techtask.counter;

import java.nio.file.Path;

public class SingleFileRowCounterService implements RowCounterService {
    private final String fileName;
    private Integer numberOfRows;

    public SingleFileRowCounterService(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void countRows() {
        numberOfRows = new RowCounter().countRows(Path.of(fileName));
    }

    @Override
    public String getFormattedResult() {
        return String.format("%s : %d", fileName, numberOfRows);
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }
}
