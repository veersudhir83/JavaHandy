package io.sudheer.practice.jenkins;

import org.json.JSONArray;
import org.json.JSONObject;

import io.sudheer.practice.jsonParsing.JSONParser;

public class ReadBuildAndStageInfo {
	
	public static void main(String args[]) throws Exception {
		StringBuffer csvLine = new StringBuffer();
		String stringUrl = "http://10.1.151.88:9999/job/test-pipeline/7/wfapi/describe";
		JSONParser jsonParser = new JSONParser();
		// get full Json Object from URL
		JSONObject jsonFullObject = jsonParser.getJSONFromUrl(stringUrl);
		
		// fetch & append build number to csv
		csvLine.append(jsonFullObject.getString(JenkinsJSONConstants.KEY_NAME)).append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
		
		// fetch & append stages information to csv
		JSONArray stagesArray=jsonFullObject.getJSONArray("stages");
		System.out.println(stagesArray.get(0).toString());
	}
    
}