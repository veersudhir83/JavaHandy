package io.sudheer.jenkins;

import io.sudheer.jenkins.utils.JobDetailsDAO;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class TestReadBuildAndStageInfo {

	static final Logger LOGGER = Logger.getLogger(TestReadBuildAndStageInfo.class);

	public static void main(String[] args) throws Exception {
		String jenkinsURL = "http://localhost:9999";
		String projectName = "pipeline-devops-web-maven";
		List<String> projectBranches = Arrays.asList("master");
		
		boolean isMultiBranchPipeline = false;
		
		JobDetailsDAO jobDetailsObj = new JobDetailsDAO();
		jobDetailsObj.setJenkinsURL(jenkinsURL);
		jobDetailsObj.setProjectName(projectName);
		jobDetailsObj.setIsMultiBranchPipeline(isMultiBranchPipeline);

		projectBranches.forEach(branch -> {
			try {
				jobDetailsObj.setProjectBranch(branch);
				String jobData = ReadBuildAndStageInfo.getFullInfo(jobDetailsObj).toString();
				LOGGER.info(!jobData.isEmpty() ? jobData : "Job Details could not be found");
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
			}
		});
	}
}
