package org.british.spoken.time;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BritishTimeConverter converter = new BritishTimeConverter();

        System.out.println("British Spoken Time Converter");
        System.out.println("Enter time (or 'exit' to quit):");
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Goodbye!");
                break;
            }

            String spokenTime = converter.convertToSpoken(input);
            System.out.println(spokenTime);
        }
        
        scanner.close();
    }
}