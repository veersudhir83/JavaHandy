package io.sudheer.practice.simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * 
 */

/**
 * @author Sudheer Veeravalli (SSO 502242973)
 *
 */
public class DateTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String dateInString = "2014/11/25";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", new Locale("English"));
			Date date = formatter.parse(dateInString);
			
			String fDateTime = formatter.format(date);
			System.out.println("fDateTime::" + fDateTime);
			
			fDateTime += " " + "16:11:11";
			SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", new Locale("English"));
			date = DATE_TIME_FORMAT.parse(fDateTime);
			System.out.println("date::" + date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
