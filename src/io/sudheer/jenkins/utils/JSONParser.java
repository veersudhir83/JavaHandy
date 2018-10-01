package io.sudheer.jenkins.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	static final Logger LOGGER = Logger.getLogger(JSONParser.class);

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	
	// constructor
	public JSONParser() {
	
	}
	
	public JSONObject getJSONFromUrl(String url) {
	
	    // Making HTTP request
	    try {
	        // defaultHttpClient
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpGet httpPost = new HttpGet(url);
	
	        HttpResponse httpResponse = httpClient.execute(httpPost);
	        HttpEntity httpEntity = httpResponse.getEntity();
	        is = httpEntity.getContent();
	
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	
	    try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                is, "iso-8859-1"), 8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	            //LOGGER.info("Full JSON = " + line);
	        }
	        is.close();
	        json = sb.toString();
	
	    } catch (Exception e) {
			LOGGER.debug("Buffer Error: Error converting result " + e.toString());
	    }
	
	    // try parse the string to a JSON object
	    try {
	        jObj = new JSONObject(json);
	    } catch (JSONException e) {
			LOGGER.debug("JSON Parser: Error parsing data " + e.toString());
			LOGGER.debug("error on parse data in jsonparser.java");
	    }
	
	    // return JSON String
	    return jObj;
	}
}