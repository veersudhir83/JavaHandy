package io.sudheer.practice.simple;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 */

/**
 * @author Sudheer Veeravalli (SSO 502242973)
 *
 */
public class BackgroundJob {

	/**
	 * @param args
	 */
	private static int MAX_ALLOWED_THREADS = 1;
	private static final ExecutorService executor = Executors.newFixedThreadPool(MAX_ALLOWED_THREADS);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(convertmillistoHrFormat() + "Starting Thread");
		sendMail();
		System.out.println(convertmillistoHrFormat() + "Came back");
	}
	
	private static void sendMail() {
	   /* executor.submit(new Runnable() {
	         public void run() {
	          
	        	 System.out.println("Started and Finished Thread");
	         }
	    });*/
		System.out.println(convertmillistoHrFormat() + "Wait for 10 seconds start");
		new Thread(new Runnable() {
		    public void run() {
		    	System.out.println(convertmillistoHrFormat() + "Started Thread");
		    	
		    	try {
		    		Thread.sleep(10000);
		    	} catch(InterruptedException ex) {
		    	    Thread.currentThread().interrupt();
		    	} finally {
		    		System.out.println(convertmillistoHrFormat() + "Wait for 10 seconds over");
		    	}
		    }
		}).start();
		
	}
	
	public static String convertmillistoHrFormat() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String formatted = df.format(new Date());
		System.out.println("Added contents to test PMD Code Review Tool");
		return formatted;
	}

}
