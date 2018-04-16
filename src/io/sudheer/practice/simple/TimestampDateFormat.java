package io.sudheer.practice.simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampDateFormat {
	public static void main(String[] args) {
		String inputDatabaseStr = "30-01-18 02:34:59.123456789 PM";
		String inputDateStr = inputDatabaseStr.replace(inputDatabaseStr.substring(inputDatabaseStr.indexOf("."), inputDatabaseStr.lastIndexOf(" ")), "");
		//System.out.println(inputDateStr);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy hh:mm:ss a");
		SimpleDateFormat outputformat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		Date inputDate = null;
		String output = null;
		try {			
			System.out.println("Input Date String: " + inputDateStr);
			// Converting the input String to Date
			inputDate = formatter.parse(inputDateStr);
			// Changing the format of date and storing it in String
			output = outputformat.format(inputDate);
			// Displaying the date
			System.out.println("Output Format: " + output);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
}
