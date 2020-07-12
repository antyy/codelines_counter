package com.techtask;

import com.techtask.counter.RowCounterService;
import com.techtask.counter.RowCounterServiceFactory;
import com.techtask.input.InputController;
import com.techtask.input.InputControllerImpl;

public class Application {
    public static void main(String[] args) {
        InputController inputHandler = new InputControllerImpl();
        String inputPath = inputHandler.handleInput(args);
        RowCounterService rowCounterService = RowCounterServiceFactory.newRowCounterService(inputPath);
        String result = rowCounterService.countRows(inputPath);
        System.out.println(result);
    }
}
