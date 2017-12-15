package io.sudheer.practice.simple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExcelCSVtoJSON {

	public static void main(String[] args) {
		String csvFile = "D:\\gegdc\\SV40298\\GIT-Repositories\\samplecodes\\src\\com\\ge\\sample\\SAMPLE.CSV";
        BufferedReader br = null;
        String line = "";
        StringBuffer sbf = new StringBuffer();
        int i = 0;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
            	if(i == 0)
            		sbf.append("uri," + line + "\n");
            	else 
            		sbf.append("/builds/" + i + "," + line+"\n");
            	i++;
            }
            //System.out.println("Final String=" + sbf.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String outputString = CSVtoJSON(sbf.toString());
        System.out.println(outputString);
	}
	
	public static String CSVtoJSON(String output) {

	    String[] lines = output.split("\n");

	    StringBuilder builder = new StringBuilder();
	    builder.append('[');
	    String[] headers = new String[16];

	    //CSV TO JSON
	    for (int i = 0; i < lines.length; i++) {
	        String[] values = lines[i].replaceAll("\"", "").split(",");

	        if (i == 0) //INDEX LIST
	        {
	            headers = values;
	        } else {
	            builder.append('{');
	            for (int j = 0; j < values.length && j < headers.length; j++) {

	                String jsonvalue = "\"" + headers[j] + "\":\"" + values[j] + "\"";
	                if (j != values.length - 1) { //if not last value of values...
	                    jsonvalue += ',';
	                }
	                builder.append(jsonvalue);
	            }
	            builder.append('}');
	            if (i != lines.length - 1) {
	                builder.append(',');
	            }
	        }
	    }
	    builder.append(']');
	    output = builder.toString();

	    return output;
	}

}
