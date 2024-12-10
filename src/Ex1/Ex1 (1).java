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
    public static int number2Int(String num) {
        if (num == null || num.isEmpty()) return -1;

        // Check if it's a natural number
        boolean isNatural = true;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (c < '0' || c > '9') {
                isNatural = false;
                break;
            }
        }
        if (isNatural) {
            int result = 0;
            for (int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                result = result * 10 + (c - '0');
            }
            return result;
        }

        if (!num.contains("b")) return -1;

        String[] parts = num.split("b");
        if (parts.length != 2) return -1;

        String numberPart = parts[0];
        String basePart = parts[1];

        if (basePart.length() != 1) return -1;
        char baseChar = basePart.charAt(0);
        int base = -1;
        if (baseChar >= '2' && baseChar <= '9') {
            base = baseChar - '0';
        } else if (baseChar >= 'A' && baseChar <= 'G') {
            base = 10 + (baseChar - 'A');
        }
        if (base == -1) return -1;

        int result = 0;
        for (int i = 0; i < numberPart.length(); i++) {
            char c = numberPart.charAt(i);
            int digitValue = Character.digit(c, base);
            if (digitValue == -1 || digitValue >= base) return -1;
            result = result * base + digitValue;
        }

        return result;
    }


    /**
     * This static function checks if the given String (g) is in a valid "number" format.
     *
     * @param a a String representing a number
     * @return true iff the given String is in a number format
     */
    public static boolean isNumber(String a) {
        if (a == null || a.isEmpty()) return false;

        // Check if it's a natural number
        boolean isNatural = true;
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            if (c < '0' || c > '9') {
                isNatural = false;
                break;
            }
        }
        if (isNatural) return true;

        if (!a.contains("b")) return false;

        String[] parts = a.split("b");
        if (parts.length != 2) return false;

        String numberPart = parts[0];
        String basePart = parts[1];

        if (basePart.length() != 1) return false;
        char baseChar = basePart.charAt(0);
        int base = -1;
        if (baseChar >= '2' && baseChar <= '9') {
            base = baseChar - '0';
        } else if (baseChar >= 'A' && baseChar <= 'G') {
            base = 10 + (baseChar - 'A');
        }
        if (base == -1) return false;

        for (int i = 0; i < numberPart.length(); i++) {
            char c = numberPart.charAt(i);
            int digitValue = Character.digit(c, base);
            if (digitValue == -1 || digitValue >= base) return false;
        }

        return true;
    }
    /**
     * Calculate the number representation (in basis base)
     * of the given natural number (represented as an integer).
     * If num<0 or base is not in [2,16] the function should return "" (the empty String).
     *
     * @param num  the natural number (include 0).
     * @param base the basis [2,16]
     * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
     */
    public static String int2Number(int num, int base) {
        if (num < 0 || base < 2 || base > 16) {
            return ""; // Invalid input
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
            num /= base;
        }

        char baseChar;
        if (base >= 2 && base <= 9) {
            baseChar = (char) ('0' + base);
        } else {
            baseChar = (char) ('A' + (base - 10));
        }

        if (result.isEmpty()) {
            result = "0";
        }

        return result + "b" + baseChar;
    }

    /**
     * Checks if the two numbers have the same value.
     *
     * @param n1 first number
     * @param n2 second number
     * @return true iff the two numbers have the same values.
     */
    public static boolean equals(String n1, String n2) {
        int numb1 = number2Int(n1);
        int numb2 = number2Int(n2);
        return numb1 != -1 && numb2 != -1 && numb1 == numb2;
    }

    /**
     * This static function search for the array index with the largest number (in value).
     * In case there are more than one maximum - returns the first index.
     * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
     *
     * @param arr an array of numbers
     * @return the index in the array in with the largest number (in value).
     */
    public static int maxIndex(String[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

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


