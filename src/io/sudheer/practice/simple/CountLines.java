package io.sudheer.practice.simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class CountLines {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String OUTPUT_FOLDER = "D:/gegdc/SV40298/jboss4.0.3/server/default/log/opt/shared/data/tdmdataaccess/INSERT/.inprog/502242973_INS_98727735000_794a98_1_1/";
		String[] filenames = new String[]{"out_header.txt","out_params.txt"};
		StringBuilder tempManifest = null;
		tempManifest = new StringBuilder();	
		for (int i=0; i<filenames.length; i++) {
			//tempManifest.append("|").append(Integer.toString(countLines(OUTPUT_FOLDER+filenames[i], filenames[i])));
			tempManifest.append("|").append(Integer.toString(countLinesUsingProcess(OUTPUT_FOLDER+filenames[i])));
		}
		System.out.println(tempManifest);
	}
	
	private static int countLines(String filename, String actualname) throws IOException {
		LineNumberReader reader  = new LineNumberReader(new FileReader(filename));
		int cnt = 0;
		String lineRead = "";
		while ((lineRead = reader.readLine()) != null) {}

		cnt = reader.getLineNumber(); 
		reader.close();
		System.out.println("Lines in file::"+actualname+" is:::"+Integer.toString(cnt));
		return cnt;
	}
	
	private static int countLinesUsingProcess (String filename) throws IOException {
		LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(filename)));
		lnr.skip(Long.MAX_VALUE);
		int i = lnr.getLineNumber();
		lnr.close();
		return i;
	}

}
