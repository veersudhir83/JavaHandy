package io.sudheer.practice.simple.threadrunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.net.URL;

public class ApiRunner {

	final static Logger LOGGER = Logger.getLogger(ApiRunner.class);

	public static void main(String[] args) {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("log4j.properties");
		PropertyConfigurator.configure(url);
	
		String[] userGuids = {"1e832262-c5a5-43be-a9dc-ceca7d8c8e3a",
        		"da98379a-f9b3-4b76-83fa-ac475366f1dd",
        		"13d926c7-adcb-4e22-ab7f-26fafb3e61be",
        		"54577688-952c-46f7-b470-c02fc4a72436",
        		"dc9cdc5b-5ae7-4a9e-b50f-d16cabd17b5e",
        		"d3292d23-0d01-4ea2-8b56-d4be8732f7b0",
        		"bb662180-a130-4b88-8647-6767b89b7e99",
        		"9401167f-40cf-4c04-8976-9389da09221b",
        		"a83bc3f7-9376-485f-922b-f88dc286b409",
        		"52ad3ec4-233b-48b7-87d6-d8f9d54605b5",
        		"4c31bc0e-1a97-4e60-97c6-27fb3341d1e5",
        		"8b652a85-ecf2-4807-8a41-bf20199fec6b",
        		"4a40de22-9552-4be3-a805-152099129a6d",
        		"8fbe2b15-9c17-4eda-95c6-996ccca08589",
        		"493e4419-142b-43ad-85af-05bf8f038af4",
        		"6f3fab67-69cc-46a5-bec4-d644258fe2d8",
        		"3a21d7c1-80ba-4860-b911-4282d57eeb2f",
        		"e9c487c6-9033-44db-a69b-c7134312ced4",
        		"aca9de06-0ca1-4adf-ac55-772c7b2c68aa",
        		"ba9ed9a9-86c7-4fcd-a3ab-2e09d35c7370"};
        Thread thread;
        int i=0;
        for (String guid : userGuids) {
        	MyRunnable mr3 = new MyRunnable();
            mr3.addCurlURL("http://localhost:8080/xapi/homepage/v1/preferences/"+guid+"?size=LARGE&htmlEncoding=true&currencyCode=USD&personalIdRowNumMap=23217-1%2C22211-3%2C22659-6%2C22705-7%2C23038-8%2C23088-9%2C22856-10&_application=SITE&_deviceType=DESKTOP&_regionCode=US");
            mr3.addThreadName("thread-"+i);
			thread = new Thread(mr3,"t"+i);
			i++;
			thread.start();
		}
        
        Date date=new Date();
		LOGGER.info(new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss").format(date));
       
	}

	 
}
