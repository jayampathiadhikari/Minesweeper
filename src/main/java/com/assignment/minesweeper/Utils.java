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

    public static int[] convertStringToIntArray(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        // Split input into letter part and number part
        int letterPartEnd = 0;
        while (letterPartEnd < input.length() && Character.isLetter(input.charAt(letterPartEnd))) {
            letterPartEnd++;
        }

        String letterPart = input.substring(0, letterPartEnd);
        String numberPart = input.substring(letterPartEnd);

        int letterValue = convertLetterPartToInt(letterPart);
        int numberValue = Integer.parseInt(numberPart);

        return new int[]{letterValue, numberValue-1};
    }

    private static int convertLetterPartToInt(String letterPart) {
        int result = 0;
        for (int i = 0; i < letterPart.length(); i++) {
            char letter = letterPart.charAt(i);
            result = result * 26 + (letter - 'A' + 1);
        }
        return result - 1; // Adjust because 'A' -> 0
    }
}
