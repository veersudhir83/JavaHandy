package io.sudheer.practice.jsonParsing;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReadFromJSON {
	
	public static void main(String args[]) throws Exception {
		String stringUrl = "http://10.1.151.88:9999/job/test-pipeline/7/wfapi/describe";
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonFullObject = jsonParser.getJSONFromUrl(stringUrl);
		JSONArray stagesArray=jsonFullObject.getJSONArray("stages");
		System.out.println(stagesArray.get(0).toString());
	}
    
}
