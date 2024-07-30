package com.assignment.minesweeper;

import java.util.Scanner;

public class UserInputScanner implements AutoCloseable {
    private final Scanner scanner;

    public UserInputScanner() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInputAsString() {
        return scanner.next().trim();
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
