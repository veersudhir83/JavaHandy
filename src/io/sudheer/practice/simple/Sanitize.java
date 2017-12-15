package io.sudheer.practice.simple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Sanitize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "sm_first_name=-?;";
		String patternExpr = "^[0-9A-Za-z._=;?-]+$";
		Pattern pattern = Pattern.compile(patternExpr);   
		Matcher matcher = pattern.matcher(test);   
		System.out.println("matches::" + matcher.matches());
	}

}
