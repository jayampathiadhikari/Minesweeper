package com.assignment.minesweeper;


public class Utils {
    public static String convertToLetter(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }

        StringBuilder result = new StringBuilder();

        while (number >= 0) {
            int remainder = number % 26;
            result.insert(0, (char) (remainder + 'A'));
            number = (number / 26) - 1;
        }

        return result.toString();
    }
}
