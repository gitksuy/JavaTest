package com.hlb;

import java.text.SimpleDateFormat;
import java.util.*;

class JavaTest {
    // E.g. Input: Automation, Output: noitamotuA
    // Please don't use method such as s.reverse()
    public static String reverseStringWithoutUsingStringMethod(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        
        return new String(chars);
    }
    
    // Sort the integer in ASC order without using the built-in method such as ArrayUtils.sort
    public static Integer[] sortIntegers(Integer[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    Integer temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
    
    // Check if the given date is within the date range
    public static boolean isInDateRange(Date givenDate, Date startDate, Date endDate) {
        if (givenDate == null || startDate == null || endDate == null) {
            return false;
        }
        return !givenDate.before(startDate) && !givenDate.after(endDate);
    }
    
    // sort the given String in ASC order without using method like Arrays.sort
    public static char[] sortStringInAscOrder(String input) {
        if (input == null || input.length() <= 1) {
            return input != null ? input.toCharArray() : new char[0];
        }
        
        char[] chars = input.toCharArray();
        int n = chars.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (chars[j] > chars[j + 1]) {
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
        
        return chars;
    }
    
    // Given a Alphanumeric, please return a character with the lowest occurrence
    // E.g. AbcdAbc123123, the answer is d as it only occurs once
    // If there is more than 1 char with the same lowest occurrence, return the first char in ASC order
    public static char lowestOccurrence(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }
        
        int[] frequency = new int[128]; // ASCII characters
        for (char c : input.toCharArray()) {
            frequency[c]++;
        }
        
        int minFreq = Integer.MAX_VALUE;
        char result = ' ';
        
        for (char c : input.toCharArray()) {
            if (frequency[c] < minFreq || (frequency[c] == minFreq && c < result)) {
                minFreq = frequency[c];
                result = c;
            }
        }
        
        return result;
    }
    
    // Given an input, please apply the provided equations (+, -, x, /)
    // E.g. input: 1.5, equations: x*2, x+10/2, x*1.5-6
    // Answer: 1st equation: x*2 = 1.5*2 = 3
    //         2nd equation: x+10/2 = 3+10/2 = 8
    //         3rd equation: x*1.5-6 = 8*1.5-6 = 6
    //         return 6.0
    public static double solveEquations(double input, String[] equations) {
        if (equations == null || equations.length == 0) {
            return input;
        }
        
        double result = input;
        
        for (String equation : equations) {
            equation = equation.trim();
            if (equation.isEmpty()) continue;
            
            // Replace 'x' with the current result
            equation = equation.replace("x", String.valueOf(result));
            
            // Split the equation into parts
            String[] parts = equation.split("(?<=[-+*/])|(?=[-+*/])");
            
            // First pass: handle multiplication and division
            for (int i = 1; i < parts.length - 1; i += 2) {
                if (parts[i].equals("*") || parts[i].equals("/")) {
                    double left = Double.parseDouble(parts[i-1]);
                    double right = Double.parseDouble(parts[i+1]);
                    double tempResult;
                    
                    if (parts[i].equals("*")) {
                        tempResult = left * right;
                    } else {
                        if (right == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        tempResult = left / right;
                    }
                    
                    parts[i-1] = String.valueOf(tempResult);
                    parts[i] = "";
                    parts[i+1] = "";
                }
            }
            
            // Second pass: handle addition and subtraction
            result = Double.parseDouble(parts[0]);
            for (int i = 1; i < parts.length; i += 2) {
                if (parts[i].equals("+")) {
                    result += Double.parseDouble(parts[i+1]);
                } else if (parts[i].equals("-")) {
                    result -= Double.parseDouble(parts[i+1]);
                }
            }
        }
        
        return result;
    }
    
    
    public static void main(String[] args) {
        System.out.println("Test 1: " + reverseStringWithoutUsingStringMethod("Automation"));
        Integer[] intArray = new Integer[] { 10, 12, 54, 1, 2, -9, 8 };
        System.out.print("Test 2: ");
        for (Integer i : intArray) {
            System.out.print(i + ", ");
        }
        
        System.out.println();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            Date startDate = sdf.parse("2024-12-01 13:09:22");
            Date endDate = sdf.parse("2025-01-09 20:10:12");
            Date givenDate = sdf.parse("2025-02-02 00:11:22");
            System.out.println("Test 3: " + isInDateRange(givenDate, startDate, endDate));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        char[] sorted = sortStringInAscOrder("testingNG311");
        System.out.print("Test 4 :");
        for (char c: sorted) {
            System.out.print(c + ", ");
        }
        System.out.println();
        System.out.println("Test 5: " + lowestOccurrence("Abc1dd23affbc1ee23u3278"));
        System.out.print("Test 6: ");
        double calculated = solveEquations(3.4, new String[] { "x*x", "x-10/2.2", "x+4-10", "x+5*8" });
        System.out.print("calculated = " + calculated);
    }
}

