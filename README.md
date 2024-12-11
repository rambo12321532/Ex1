Ex1 - Number Formatting Converter and Calculator

Overview

This project involves converting, validating, and manipulating numbers in different bases (from binary to hexadecimal) and natural numbers. It supports operations on numbers represented as strings with the format <number><b><base>, such as 101b2, FFb16, or 123 (for natural numbers). The program provides methods to check if a number is valid, convert between different base representations, and perform calculations like addition and multiplication.

Files

Ex1.java: Main logic of the program. It contains methods to convert, validate, and manipulate numbers.

Ex1Main.java: User interface where users input two numbers, specify a base, and perform calculations.

Ex1Test.java: Unit tests to ensure the correctness of the methods in Ex1.java.

Features

Support for Natural Numbers: Handles natural numbers (like 10, 20, etc.) in addition to numbers with base notation.

Base Conversion: Converts numbers to and from bases 2 to 16.

Validation: Checks if a given string is a valid number according to the format <number><b><base>.

Operations: Supports addition, multiplication, and maximum value determination among multiple numbers.

How to Run

Compile the files: Compile Ex1.java, Ex1Main.java, and Ex1Test.java.

Run the main program: Run Ex1Main.java to input and calculate results for two numbers.

Run the tests: Run Ex1Test.java to ensure all methods work correctly.

Usage

Input Numbers: Enter two numbers (either natural numbers or numbers in <number><b><base> format).

Specify Base: Enter a base for the output (2 to 16).

View Results: The program will output the sum, product, and the largest of the four numbers (num1, num2, sum, product).

Example Run

Ex1 class solution:
Enter a string as number#1 (or "quit" to end the program):
1
num1 = 1 is number: true, value: 1
Enter a string as number#2 (or "quit" to end the program):
0
num2 = 0 is number: true, value: 0
Enter a base for output: (a number [2,16]):
10
1 + 0 = 1
1 * 0 = 0
Max number over [1, 0, 1, 0] is: 1

Methods in Ex1.java

number2Int(String num): Converts a string to its integer equivalent. Handles both natural numbers and <number><b><base> format.

isNumber(String a): Checks if a given string is a valid number in natural or <number><b><base> format.

int2Number(int num, int base): Converts an integer to its representation in the specified base.

equals(String n1, String n2): Checks if two numbers have the same value.

maxIndex(String[] arr): Finds the index of the largest number in an array of numbers.

Testing

Run the unit tests in Ex1Test.java to verify all methods. Tests cover:

Valid and invalid number formats

Base conversions

Natural numbers

Arithmetic operations

Important Notes

Natural numbers (like 10, 20, etc.) are supported without the need for <number><b><base> format.

Base values can range from 2 to 16, with base 10 represented as 'A' and higher bases up to 16 represented as 'B', 'C', etc.

The largest number from a list of inputs is determined and displayed.

Example Numbers

Natural numbers: 12, 45, 100, etc.

Base format numbers: 101b2 (binary), FFb16 (hexadecimal), 123bA (base 10), etc.
