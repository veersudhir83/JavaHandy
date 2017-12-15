package io.sudheer.practice.simple;

import java.io.IOException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

public class RunShellScript {
    int iExitValue;
    String sCommandString;

    public void runScript(String command){
        sCommandString = command;
        System.out.println("here11");
        CommandLine oCmdLine = CommandLine.parse(sCommandString);
        System.out.println("here22");
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
        System.out.println("here33");
        oDefaultExecutor.setExitValue(0);
        try {
            iExitValue = oDefaultExecutor.execute(oCmdLine);
            System.out.println("here44 iExitValue=" + iExitValue);
        } catch (ExecuteException e) {
            System.err.println("Execution failed.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("permission denied.");
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public static void main(String args[]){
    	RunShellScript testScript = new RunShellScript();
        testScript.runScript("sh D:/gegdc/SV40298/temp/jenkins_files/downloadPLMMetrics.sh");
    }
}