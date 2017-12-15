package io.sudheer.practice.simple;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegexPattern {
	public static void main (String[] args) {
		boolean flag = false;
		Pattern pattern;
		Matcher matcher;
		String regEx = "";
		String fvalue = "#M1";
		//Satya: To allow % * ? and () special characters in ESN value
		
		//regEx = "^[\da-zA-Z-/_,\s]{1,}$";
		
		if (regEx != null && (!regEx.equals(""))) {
			pattern = Pattern.compile(regEx);
			matcher = pattern.matcher(fvalue.toUpperCase(Locale.getDefault()));
			if (matcher.find()) {
				flag = true;
			}
		}
		System.out.println("Valid:::" + flag);
	}
}