package io.sudheer.practice.simple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IntelliJTest {
    public static void main(String[] args) throws IOException {
        StringBuffer jsonStr = new StringBuffer();
        jsonStr.append("{\"name\": \"Sudheer\"}");

        //BufferedReader reader = new BufferedReader(new FileReader("abc.txt"));

        //String read = reader.readLine().toUpperCase();

        System.out.println("jsonStr = " + jsonStr);
    }
}

