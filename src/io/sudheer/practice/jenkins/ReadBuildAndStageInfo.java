package io.sudheer.practice.jenkins;

import org.json.JSONArray;
import org.json.JSONObject;

import io.sudheer.practice.jsonParsing.JSONParser;
import io.sudheer.practice.simple.CalendarUtils;

public class ReadBuildAndStageInfo {
	
	public static void main(String args[]) throws Exception {
				
		StringBuffer csvLine = new StringBuffer();
		
		String mainURL = "http://10.1.151.88:9999/job/MediConnekt-DEV/295";
		//String mainURL = "http://10.1.151.88:9999/job/test-pipeline/7";
		
		String buildAPIURL = mainURL + "/api/json";
		JSONParser buildJSONParser = new JSONParser();
		// get full Json Object from URL
		JSONObject buildJSONObject = buildJSONParser.getJSONFromUrl(buildAPIURL);

		// Fetch this from the build api url: actions[0].causes[0].userName 
		// could be any of the actions[] element having _class: "hudson.model.CauseAction" 
		String causedByUserName = "Not Resolved";
		
		causedByUserName = ((JSONObject) ((JSONObject) buildJSONObject.getJSONArray(JenkinsJSONConstants.BUILD_KEY_ACTIONS).get(0)).getJSONArray(JenkinsJSONConstants.BUILD_KEY_ACTIONS_CAUSES).get(0)).getString(JenkinsJSONConstants.BUILD_KEY_ACTIONS_CAUSES_STARTED_BY);		
				
		String pipelineAPIURL = mainURL + "/wfapi";
		JSONParser pipelineJSONParser = new JSONParser();
		// get full Json Object from URL
		JSONObject pipelineJSONObject = pipelineJSONParser.getJSONFromUrl(pipelineAPIURL);
		
		// fetch & append build name and number to csv
		csvLine.append(buildJSONObject.getString(JenkinsJSONConstants.BUILD_KEY_FULL_DISPLAY_NAME));
		csvLine.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
		
		// fetch and append build overall status
		csvLine.append(buildJSONObject.getString(JenkinsJSONConstants.BUILD_RESULT));
		csvLine.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
		
		// fetch, convert and append date and time to csv
		csvLine.append(CalendarUtils.ConvertMilliSecondsToFormattedDate(pipelineJSONObject.getLong(JenkinsJSONConstants.PIPELINE_START_TIME_MILLIS)));
		csvLine.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
		
		csvLine.append(CalendarUtils.ConvertMilliSecondsToFormattedTime(pipelineJSONObject.getLong(JenkinsJSONConstants.PIPELINE_START_TIME_MILLIS)));
		csvLine.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
		
		// append caused by user name
		csvLine.append(causedByUserName);
		csvLine.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
		
		// append branch information
		csvLine.append("<Some_Branch>");
				
		// fetch stages information 
		JSONArray stagesArray=pipelineJSONObject.getJSONArray(JenkinsJSONConstants.KEY_STAGES);
		System.out.println("Number of Stages = " + stagesArray.length());
		
		int overallPipelineDurationInSecs = 0;
		int overallStagePauseInSecs = 0;
		StringBuffer stagesTimes = new StringBuffer();
		
		if(stagesArray.length() > 0) {
			JSONObject stage = null;
			for (int i = 0; i < stagesArray.length(); i++) {
				stage = stagesArray.getJSONObject(i);
				int stageDurationInSecs = (int) stage.getLong(JenkinsJSONConstants.STAGE_DURATION_MILLIS)/1000;
				//System.out.println(stageDurationInSecs);
				overallPipelineDurationInSecs += stageDurationInSecs;
				overallStagePauseInSecs += (int) stage.getLong(JenkinsJSONConstants.STAGE_PAUSE_MILLIS); 
				stagesTimes.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
				stagesTimes.append(stageDurationInSecs/60).append(".").append(stageDurationInSecs%60);				
			}
		}		
		
		// If build is Success.. then only append do the following stage information
		if(buildJSONObject.getString(JenkinsJSONConstants.BUILD_RESULT).equalsIgnoreCase(JenkinsJSONConstants.STATUS_SUCCESS)) {
		
			// Total Build duration from Jenkins
			int buildDurationFromJenkinsInSecs = (int) buildJSONObject.getLong(JenkinsJSONConstants.BUILD_DURATION)/1000;
			csvLine.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
			csvLine.append(buildDurationFromJenkinsInSecs/60).append(".").append(buildDurationFromJenkinsInSecs%60);
			
			
			// Append Build Delay
			int buildDelayInSecs = buildDurationFromJenkinsInSecs - overallPipelineDurationInSecs;			
			csvLine.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
			csvLine.append(buildDelayInSecs/60).append(".").append(buildDelayInSecs%60);
				
		
			// Append Pipeline Duration
			csvLine.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
			csvLine.append(overallPipelineDurationInSecs/60).append(".").append(overallPipelineDurationInSecs%60);
						
			// Append Pipeline Delay
			csvLine.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
			int pipelineDelayInSecs = (int) pipelineJSONObject.getLong(JenkinsJSONConstants.PIPELINE_QUEUE_DURATION)/1000;
			csvLine.append(pipelineDelayInSecs/60).append(".").append(pipelineDelayInSecs%60);
			
			// Append Pipeline Pauses Info
			csvLine.append(JenkinsJSONConstants.DELIMITER_COMMA_SPACE);
			csvLine.append(overallStagePauseInSecs/60).append(".").append(overallStagePauseInSecs%60);
						
			// Append Stage Timings
			csvLine.append(stagesTimes);
		}
				
		System.out.println("CSV Line=" + csvLine.toString());
	}
    
}