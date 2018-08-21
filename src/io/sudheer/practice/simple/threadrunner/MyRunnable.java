package io.sudheer.practice.simple.threadrunner;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class MyRunnable implements Runnable {

	final static Logger LOGGER = Logger.getLogger(MyRunnable.class);

    String curlcommand = "";
    String threadName = "";

    public void addCurlURL(String url) {
    	this.curlcommand = url;
    }
	public void addThreadName(String threadName) {
		this.threadName = threadName;
	}

    @Override
	public void run() {
		try {
			URL url = new URL(curlcommand);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
				String result;
				result = br.lines().collect(Collectors.joining("\n"));

				LOGGER.info("\nthreadName=" + threadName + "\ncurlCommand=" + curlcommand + "\nresult=" + result + "\n");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

    
}