package Ex1;
import java.util.Scanner;

/**
 * Intro2CS, Ex1 - very basic "main template"
 * Make sure your implementation of this main performs as the Ex1Sol.jar solution implement all needed functions.
 *
 */
public class Ex1Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = "", num2 = "", quit = "quit";
        while (!num1.equals(quit) && !num2.equals(quit)) {
            System.out.println();
            System.out.println("Ex1 class solution:");
            System.out.println("Enter a string as number#1 (or \"quit\" to end the program): ");
            num1 = sc.next();
            if (num1.equals(quit)) break;

            // Check if num1 is valid
            if (Ex1.isNumber(num1)) {
                System.out.println("Valid input. Now pick another number.");
            } else {
                System.out.println("ERR: num1 is in the wrong format! (" + num1 + ")");
                continue; // Skip the rest of the loop
            }

            // Get the second number
            System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");
            num2 = sc.next();
            if (num2.equals(quit)) break;

            // Check if num2 is valid
            if (Ex1.isNumber(num2)) {
                int num1Value = Ex1.number2Int(num1);
                int num2Value = Ex1.number2Int(num2);

                System.out.println("num1 = " + num1 + " is number: true, value: " + num1Value);
                System.out.println("num2 = " + num2 + " is number: true, value: " + num2Value);

                System.out.println("Enter a base for output: (a number [2,16]): ");
                int base = sc.nextInt();
                sc.nextLine(); // Consume newline character

                String sum = Ex1.int2Number(num1Value + num2Value, base);
                String product = Ex1.int2Number(num1Value * num2Value, base);

                System.out.println(num1 + " + " + num2 + " = " + sum);
                System.out.println(num1 + " * " + num2 + " = " + product);

                String[] arrayOfNumbers = {num1, num2, sum, product};
                int maxIndex = Ex1.maxIndex(arrayOfNumbers);
                System.out.println("Max number over [" + String.join(", ", arrayOfNumbers) + "] is: " + arrayOfNumbers[maxIndex]);
            } else {
                System.out.println("ERR: num2 is in the wrong format! (" + num2 + ")");
            }
        }

        System.out.println("Quitting now...");
        sc.close();
    }
}