package io.sudheer.practice.simple;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class ServerUtil {
    

    public static void main(String args[]) {
    	
        try {
        	String jbossNodeName;
            jbossNodeName = System.getProperty("jboss.server.name");
            System.out.println("jbossNodeName 111:::" + jbossNodeName);
            jbossNodeName = jbossNodeName != null ? jbossNodeName : InetAddress.getLocalHost().getHostName();
            System.out.println("jbossNodeName 222:::" + jbossNodeName);
            if (jbossNodeName.indexOf("-1") > 0) {
            	// Invoke the process
            }
    
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}