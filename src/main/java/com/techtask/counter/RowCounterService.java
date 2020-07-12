package com.techtask.counter;

public interface RowCounterService {
    void countRows();

    default void printResultToConsole() {
        System.out.println(getFormattedResult());
    }

    String getFormattedResult();
}
