package io.sudheer.practice.simple;

import java.io.*;

public class Serialization {

    public static void main(String args[]) {
        try {
            FileOutputStream fileStream = new FileOutputStream("Rectangle.ser");
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(new Rectangle(5, 6));
            objectStream.close();
            System.out.println("Object is serialized");

            FileInputStream fileInputStream = new FileInputStream("Rectangle.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    fileInputStream);
            Rectangle rectangle = (Rectangle) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(rectangle.length);// 5
            System.out.println(rectangle.breadth);// 6
            System.out.println(rectangle.area);// 30

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}


class Rectangle implements Serializable {
    public Rectangle(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
        area = length * breadth;
    }

    int length;
    int breadth;
    transient int area; // transient means it's not serialized and so not saved in the internal object representation
}