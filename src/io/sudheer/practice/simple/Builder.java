package io.sudheer.practice.simple;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Builder {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//sample();
		System.out.println("Started" + System.currentTimeMillis()/1000);
		File zipFile3 = new File ("D:/gegdc/SV40298/jboss4.0.3/server/default/log/opt/shared/data/tdmdataaccess/temp/javaOut2.tzf");
		File zipFile2 = new File ("D:/gegdc/SV40298/jboss4.0.3/server/default/log/opt/shared/data/tdmdataaccess/temp/javaOut.tzf");
		FileOutputStream out = new FileOutputStream(zipFile3);
		InputStream inStream = null;
		BufferedInputStream buffStream = null;
		inStream = new FileInputStream(zipFile2);
		buffStream = new BufferedInputStream(inStream);
		byte[] buffer = new byte[Short.MAX_VALUE];					
		int len;
        while((len = buffStream.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
		buffStream.close();
		inStream.close();
		out.close();
		System.out.println("End    " + System.currentTimeMillis()/1000);
	}

	private static void sample() {
		// TODO Auto-generated method stub
		String s = "13050553,DEV,NOMODEL-G,123-001/1,NPSS,P2,9880,1,1,9999-12-31 00:00:00,NONE,-999,NONE,NONE      ,2012-10-05 05:32:10,TESTSITE,1,ENGINE,SS,0,1,NONE,NONE,NONE,NONE,1768.39794899999993,1222.40905799999996,3669.56933600000002,14.69599915,22.88507843,529.61602779999998,600.99493410000002,1768.39794899999993,1222.40905799999996,1.557231903\n";
		StringBuilder sb = new StringBuilder();
		System.out.println("Started" + System.currentTimeMillis()/1000);
		for (int i=1; i<=2000; i++) {
			sb.append(s);
		}
		System.out.println("End    " + System.currentTimeMillis()/1000);
		System.out.println(sb.capacity());
		System.out.println(sb.toString());
	}
}
