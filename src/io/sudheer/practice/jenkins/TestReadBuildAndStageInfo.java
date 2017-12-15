package io.sudheer.practice.jenkins;

import io.sudheer.practice.jenkins.ReadBuildAndStageInfo;

public class TestReadBuildAndStageInfo {

	public static void main(String[] args) throws Exception {
		String jenkinsURL = "http://192.168.43.115:8080";
		String projectName = "Pipeline-devops-web-maven";
		String projectBranch = "master";
		int buildNumber = 26;
		boolean multiBranchPipeline = false;
		//URL for MultiBranch Configuration Pipelines
		//String mainURL = "http://192.168.43.115:8080/job/devops-web-hackathon/job/master/4";
		
		//URL for Simple Pipelines
		//String mainURL = "http://192.168.43.115:8080/job/Pipeline-devops-web-maven/26";
		
		System.out.println(ReadBuildAndStageInfo.getFullInfo(jenkinsURL, projectName, projectBranch, buildNumber, multiBranchPipeline));

	}

}
