package com.techtask;

import com.techtask.counter.RowCounterService;
import com.techtask.input.InputController;
import com.techtask.input.InputControllerImpl;

import static com.techtask.counter.RowCounterServiceFactory.newRowCounterService;

public class Application {
    private final InputController inputController = new InputControllerImpl();

    public void run(String[] args) {
        String fileName = inputController.handleInput(args);

        RowCounterService stringCounter = newRowCounterService(fileName);

        stringCounter.countRows();
        stringCounter.printResultToConsole();
    }

}
