package Ex1;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import javax.swing.*;
import java.util.function.ToIntFunction;

/**
 * This class represents a simple solution for Ex1.
 * As defined here: https://docs.google.com/document/d/1AJ9wtnL1qdEs4DAKqBlO1bXCM6r6GJ_J/r/edit/edit
 * In this assignment, we will design a number formatting converter and calculator.
 * In general, we will use Strings as numbers over basis of binary till Hexa.
 * [2-16], 10-16 are represented by A,B,..G.
 * The general representation of the numbers is as a String with the following format:
 * <number><b><base> e.g., “135bA” (i.e., “135”, as 10 is the default base), “100111b2”, “12345b6”,”012b5”, “123bG”, “EFbG”.
 * The following are NOT in the format (not a valid number):
 * “b2”, “0b1”, “123b”, “1234b11”, “3b3”, “-3b5”, “3 b4”, “GbG”, "", null,
 * You should implement the following static functions:
 */
 class Ex1 {
    /**
     * Convert the given number (num) to a decimal representation (as int).
     * It the given number is not in a valid format returns -1.
     *
     * @param num a String representing a number in basis [2,16]
     * @return ans
     */

    /**
     * This class represents a simple solution for Ex1.
     * It allows converting and validating numbers between different bases (2 to 16) and also works for natural numbers.
     */
        public static int number2Int(String num) {
            if (num == null) {
                // If the input is null, return -1
                return -1;
            }

            // Check if the input is a natural number (all digits, no "b" symbol)
            boolean isNatural = true;
            for (int i = 0; i < num.length(); i++) {
                if (!Character.isDigit(num.charAt(i))) {
                    isNatural = false;
                    break;
                }
            }

            if (isNatural) {
                // If it is a natural number, convert it to an integer
                int result = 0;
                for (int i = 0; i < num.length(); i++) {
                    char c = num.charAt(i);
                    result = result * 10 + (c - '0');
                }
                return result;
            }

            // Check if the number has a valid 'b' character for base notation
            if (!num.contains("b")) {
                return -1;
            }

            // Split the number into two parts: the number and the base
            String[] sections = num.split("b");
            if (sections.length != 2) {
                return -1;
            }

            String numberPart = sections[0];
            String basePart = sections[1];

            // Convert the base character to an integer value
            int base = 0;
            if (basePart.length() == 1) {
                char baseChar = basePart.charAt(0);
                if (baseChar >= '2' && baseChar <= '9') {
                    base = baseChar - '0';
                } else if (baseChar >= 'A' && baseChar <= 'G') {
                    base = 10 + (baseChar - 'A');
                } else {
                    return -1;
                }
            } else {
                return -1;
            }

            // Validate that each character in the number is valid for the given base
            for (int i = 0; i < numberPart.length(); i++) {
                char c = numberPart.charAt(i);
                int digitValue = Character.digit(c, base);
                if (digitValue == -1) {
                    return -1;
                }
            }

            // Convert the number to a decimal value
            int result = 0;
            for (int i = 0; i < numberPart.length(); i++) {
                char c = numberPart.charAt(i);
                int digitValue = Character.digit(c, base);
                result = result * base + digitValue;
            }

            return result;
        }


        /**
         * Checks if the given string (a) is in a valid "number" format.
         * This method checks for natural numbers or numbers in <number><b><base> format.
         *
         * @param a a String representing a number
         * @return true if the input is a valid number, false otherwise
         */
        public static boolean isNumber(String a) {

            if (a == null || a.trim().isEmpty()) {
                return false; // Check if the input is null or empty
            }

            if (!a.equals(a.trim()) || a.contains(" ")) {
                return false; // Check if the input contains spaces
            }

            if (a.contains("b")) {
                // Split the input into number and base parts
                String[] parts = a.split("b");
                if (parts.length != 2) {
                    return false;
                }

                String numberPart = parts[0];
                String basePart = parts[1];

                if (numberPart.isEmpty() || !numberPart.matches("[0-9A-G]+")) {
                    return false;
                }

                if (basePart.isEmpty() || !basePart.matches("[2-9A-G]")) {
                    return false;
                }

                int base;
                if (basePart.length() == 1 && basePart.charAt(0) >= 'A' && basePart.charAt(0) <= 'G') {
                    base = 10 + (basePart.charAt(0) - 'A');
                } else {
                    base = Integer.parseInt(basePart);
                }

                for (char c : numberPart.toCharArray()) {
                    int value;
                    if (c >= '0' && c <= '9') {
                        value = c - '0';
                    } else if (c >= 'A' && c <= 'G') {
                        value = 10 + (c - 'A');
                    } else {
                        return false;
                    }

                    if (value < 0 || value >= base) {
                        return false;
                    }
                }

                return true;
            }

            return a.matches("[0-9]+");
        }


        /**
         * Converts an integer to its representation in a given base.
         *
         * @param num  The number to convert
         * @param base The base (must be between 2 and 16)
         * @return A string representing the number in the specified base
         */
        public static String int2Number(int num, int base) {
            if (num < 0 || base < 2 || base > 16) {
                return "";
            }

            if (num == 0) {
                char baseChar;
                if (base <= 9) {
                    baseChar = (char) ('0' + base);
                } else {
                    baseChar = (char) ('A' + (base - 10));
                }
                return "0b" + baseChar;
            }

            String result = "";
            while (num > 0) {
                int remainder = num % base;
                char digit;
                if (remainder < 10) {
                    digit = (char) ('0' + remainder);
                } else {
                    digit = (char) ('A' + (remainder - 10));
                }
                result = digit + result;
                num = num / base;
            }

            char baseChar;
            if (base >= 2 && base <= 9) {
                baseChar = (char) ('0' + base);
            } else {
                baseChar = (char) ('A' + (base - 10));
            }

            return result + "b" + baseChar;
        }


        /**
         * Checks if two numbers have the same value.
         *
         * @param n1 First number
         * @param n2 Second number
         * @return true if the two numbers have the same value, false otherwise
         */
        public static boolean equals(String n1, String n2) {
            int numb1 = number2Int(n1);
            int numb2 = number2Int(n2);
            return numb1 != -1 && numb2 != -1 && numb1 == numb2;
        }


        /**
         * Finds the index of the largest number in the array.
         * If there are multiple maximums, it returns the first occurrence.
         *
         * @param arr An array of strings representing numbers
         * @return The index of the maximum number
         */
        public static int maxIndex(String[] arr) {
            int maxIndex = -1;
            int maxValue = Integer.MIN_VALUE;

            for (int i = 0; i < arr.length; i++) {
                int value = number2Int(arr[i]);
                if (value > maxValue) {
                    maxValue = value;
                    maxIndex = i;
                }
            }

            return maxIndex;
        }
    }
