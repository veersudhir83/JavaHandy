package io.sudheer.practice.simple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IntelliJTest {
    public static void main(String[] args) throws IOException {
        String jsonStr = "{\"name\": \"Sudheer\"}";

        BufferedReader reader = new BufferedReader(new FileReader("abc.txt"));

        String read = reader.readLine().toUpperCase();

    }
}

