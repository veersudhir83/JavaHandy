package io.sudheer.practice.simple.threadRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class MyRunnable implements Runnable {

    String curlcommand = "";

    public void addCurlURL(String url) {
    	this.curlcommand = url;
    }

    @Override
	public void run() {
		try {
			System.out.println("--->"+curlcommand);
			URL url = new URL(curlcommand);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
				String result;
				result = br.lines().collect(Collectors.joining("\n"));

				System.out.println(curlcommand + "\n thread" + result.toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

    
}