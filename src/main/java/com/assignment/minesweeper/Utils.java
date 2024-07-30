package com.assignment.minesweeper;


public class Utils {
    public static String convertToLetter(int number) {
        if (number < 0 || number > 25) {
            throw new IllegalArgumentException("Number must be between 0 and 25");
        }
        char chr = (char) (number + 'A');
        return String.valueOf(chr);
    }
}
