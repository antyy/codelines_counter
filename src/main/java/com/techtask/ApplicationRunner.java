package com.techtask;

import com.techtask.input.InputControllerImpl;

import java.io.IOException;

public class ApplicationRunner {
    public static void main(String[] args) throws IOException {
////        Application app = new Application();
//        Console console = System.console();
//        System.out.println(console);
//        if (console != null) {
//            console.writer().println("Please, input an string");
//            String input = console.readLine();
//            System.out.println("your string is:");
//            System.out.println(input);
//        } else {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("Please, input an string");
//            String input = reader.readLine();
//            System.out.println("your string is:");
//            System.out.println(input);
//        }
        InputControllerImpl inputHandler = new InputControllerImpl();
        String input = inputHandler.handleInput(args);
        System.out.println(input);
    }
}
