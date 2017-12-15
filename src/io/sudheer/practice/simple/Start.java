package io.sudheer.practice.simple;

import java.lang.reflect.Method;

public class Start {

	public static void main(String[] args) {
		Class[] parameterTypes = new Class[1];
        parameterTypes[0] = String.class;
        Method method1;
		try {
			method1 = Reflection.class.getMethod("method1", parameterTypes);
			Reflection demo = new Reflection();
	        demo.method1("Hello World");
	        demo.method2(demo, method1, "Hello World");
	        demo.method3();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
