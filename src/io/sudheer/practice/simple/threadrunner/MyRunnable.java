package io.sudheer.practice.simple.threadrunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class MyRunnable implements Runnable {

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

				System.out.println(curlcommand + "\n" + threadName + result);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

    
}