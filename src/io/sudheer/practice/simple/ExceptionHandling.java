package io.sudheer.practice.simple;

import java.io.FileInputStream;
import java.io.IOException;

public class ExceptionHandling {
    public static void main(String[] args) {
        //method1();
        //System.out.println("Line after Exception - Main");

        try (FileInputStream input = new FileInputStream("file.txt")) {
            int data = input.read();
            while (data != -1) {
                System.out.print((char) data);
                data = input.read();
            }
        } catch (IOException ex) {
            System.out.println("Line after Multitype Exception - Main");
        }
    }

    private static void method1() {
        method2();
        System.out.println("Line after Exception - Method 1");
    }

    static class Connection {
        void open() {
            System.out.println("Connection Opened");
        }

        void close() {
            System.out.println("Connection Closed");
        }
    }

    private static void method2() {
        Connection connection = new Connection();
        connection.open();
        try {
            // LOGIC
            String str = null; // This should be fixed to avoid exception and execute addAmounts method.
            str.toString();
            System.out.println(addAmounts(10, 20));
            return;
        } catch (Exception e) {
            // NOT PRINTING EXCEPTION TRACE - BAD PRACTICE
            System.out.println("Exception Handled - Method 2");
            return;
        } finally {
            connection.close();
        }
    }

    static class UnmatchedException extends Exception {
        UnmatchedException(String s) {
            System.out.println("amounts donot match");
        }
    }

    static float addAmounts(float amount1, float amount2) throws UnmatchedException{
        if (amount1 != amount2) {
            throw new UnmatchedException("Currencies don't match");// COMPILER ERROR!
            // if we don't declare throws Exception in method definition
            // Unhandled exception type Exception
        }
        return amount1 + amount2;
    }

}
