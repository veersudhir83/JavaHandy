package io.sudheer.practice.simple;

public class TimeLog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long delta_ms = 885;
		String temp = (((delta_ms/1000.0) > 0.000) ? (","+ delta_ms/1000.0) :"");
		//String temp = "," + delta_ms/1000.0;
		//double rem = (double) (delta_ms/1000.0);
		System.out.println("temp:" + temp);
	}

}
