package com.techtask.counter;

import java.nio.file.Paths;

public class RowCounterServiceFactory {
    public static RowCounterService newRowCounterService(String fileName) {
        return Paths.get(fileName).toFile().isFile() ?
                new SingleFileRowCounterService() :
                new DirectoryRowCounterService();
    }
}
