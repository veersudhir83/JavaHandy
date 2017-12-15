package io.sudheer.practice.simple;

import java.lang.reflect.Method;

public class Reflection {

    public void method1(String message) {
        System.out.println("Method 1: " + message);
    }

    public void method3() {
        System.out.println("Method 3: Hello World !!");
    }
    
    public void method2(Object object, Method method, String message) throws Exception {
        Object[] parameters = new Object[1];
        parameters[0] = "Method 2: " + message;
        method.invoke(object, parameters);
    }

}
