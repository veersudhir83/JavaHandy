package io.sudheer.jenkins;

import io.sudheer.jenkins.ReadBuildAndStageInfo;
import io.sudheer.jenkins.utils.JobDetailsDAO;

public class TestReadBuildAndStageInfo {

	public static void main(String[] args) throws Exception {
		String jenkinsURL = "http://10.1.107.45:9999";
		String projectName = "MediConnekt-DEV";
		String projectBranch = "master";
		
		boolean isMultiBranchPipeline = false;
		
		JobDetailsDAO jobDetailsObj = new JobDetailsDAO();
		jobDetailsObj.setJenkinsURL(jenkinsURL);
		jobDetailsObj.setProjectName(projectName);
		jobDetailsObj.setProjectBranch(projectBranch);
		
		jobDetailsObj.setIsMultiBranchPipeline(isMultiBranchPipeline);
		
		//URL for MultiBranch Configuration Pipelines
		//String mainURL = "http://192.168.43.115:8080/job/devops-web-hackathon/job/master/4";
		
		//URL for Simple Pipelines
		//String mainURL = "http://192.168.43.115:8080/job/devops-web-maven/26";
		
		System.out.println(ReadBuildAndStageInfo.getFullInfo(jobDetailsObj));
		/*int buildNumber = 2;
		jobDetailsObj.setBuildNumber(buildNumber);
		String temp = ReadBuildAndStageInfo.getBuildInfo(jobDetailsObj);
		System.out.println(ReadBuildAndStageInfo.getHeaderInfo() + "\n" + temp);
		*/
	}

}
