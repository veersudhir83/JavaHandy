package io.sudheer.practice.simple;

//import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.net.Proxy;
//import java.net.InetSocketAddress;
import java.io.OutputStreamWriter;

public class Curl {

    public static void main(String[] args) {

        approach2();

    }

    private static void approach1() {
        try {

            String url = "http://localhost:8080/xapi/homepage/v1/canvas";

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            conn.setRequestMethod("GET");

            /*String userpass = "user" + ":" + "pass";
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
            conn.setRequestProperty ("Authorization", basicAuth);*/

            String data =  "{\"format\":\"json\",\"pattern\":\"#\"}";
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(data);
            out.close();

            //new InputStreamReader(conn.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void approach2() {
        try {
            URL url = new URL("http://localhost:8080/xapi/homepage/v1/canvas");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String strTemp = "";
                while (null != (strTemp = br.readLine())) {
                    System.out.println(strTemp);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}