package org.british.spoken.time.ui;

import org.british.spoken.time.service.TimeService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Console-based user interface for the time converter application.
 */
@Component
public class ConsoleInterface {
    
    private static final String EXIT_COMMAND = "exit";
    private static final String PROMPT = "> ";
    
    private final TimeService timeService;
    private final Scanner scanner;

    public ConsoleInterface(TimeService timeService, Scanner scanner) {
        this.timeService = timeService;
        this.scanner = scanner;
    }

    public void start() {
        displayWelcomeMessage();
        
        try {
            while (true) {
                String input = promptForInput();
                
                if (isExitCommand(input)) {
                    displayGoodbyeMessage();
                    break;
                }
                
                processTimeInput(input);
            }
        } finally {
            scanner.close();
        }
    }

    private void displayWelcomeMessage() {
        System.out.println("British Spoken Time Converter");
        System.out.println("Enter time (or 'exit' to quit):");
    }

    private String promptForInput() {
        System.out.print(PROMPT);
        return scanner.nextLine().trim();
    }

    private boolean isExitCommand(String input) {
        return EXIT_COMMAND.equalsIgnoreCase(input);
    }

    private void processTimeInput(String input) {
        try {
            String spokenTime = timeService.parseAndConvert(input);
            System.out.println(spokenTime);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void displayGoodbyeMessage() {
        System.out.println("Goodbye!");
    }
}