package com.techtask.counter;

import java.nio.file.Path;

public class SingleFileRowCounterService implements RowCounterService {

    @Override
    public String countRows(String fileName) {
        Path file = Path.of(fileName);
        int numberOfRows = new RowCounter().countRows(file);
        return String.format("%s : %d", file.getFileName(), numberOfRows);
    }

}
