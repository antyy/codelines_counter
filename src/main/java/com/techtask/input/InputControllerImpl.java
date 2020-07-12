package com.techtask.input;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.isNull;

public class InputControllerImpl implements InputController {
    private static final String EXIT_COMMAND = "X";
    private Console console = System.console();
    private BufferedReader reader;

    public String handleInput(String[] args) {
        String fileName;
        if (commandLineStringInputIsInvalid(args)) {
            fileName = provideNewInputFromUser();
        } else {
            fileName = args[0];
        }
        while (notValidFileOrDirectoryInput(fileName)) {
            fileName = provideNewInputFromUser();
        }

        return fileName;
    }

    private String provideNewInputFromUser() {
        System.out.println("Please, input the correct file or directory otherwise 'X' for exit");

        String input = "";
        if (console != null) {
            input = console.readLine();
        } else {
            if (isNull(reader)) {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
            try {
                input = reader.readLine();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        handleExit(input);
        return input;
    }

    private void handleExit(String input) {
        if (EXIT_COMMAND.equals(input)) {
            System.exit(0);
        }
    }

    private boolean notValidFileOrDirectoryInput(String fileName) {
        Path p = Paths.get(fileName);
        try {
            File f = p.toFile();
            return !f.isFile() && !f.isDirectory();
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean commandLineStringInputIsInvalid(String[] args) {
        return isNull(args) || args.length < 1 || isNull(args[0]) || args[0].isBlank();
    }

}
