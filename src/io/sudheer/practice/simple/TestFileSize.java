package io.sudheer.practice.simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class TestFileSize {

	public static void main(String... args) throws IOException {
	    for (int mb : new int[]{200})
	        testFileSize(mb);
	}

	private static void testFileSize(int mb) throws IOException {
	    File file = new File ("D:/gegdc/SV40298/ActiveProcess/ActiveProcessWorkSpace/sample/src/sample.txt");
	    //file.deleteOnExit();
	    char[] chars = new char[1024];
	    Arrays.fill(chars, 'A');
	    String longLine = new String("13050553,DEV,NOMODEL-G,123-001/1,NPSS,P2,9880,1,1,9999-12-31 00:00:00,NONE,-999,NONE,NONE      ,2012-10-05 05:32:10,TESTSITE,1,ENGINE,SS,0,1,NONE,NONE,NONE,NONE,1768.39794899999993,1222.40905799999996,3669.56933600000002,14.69599915,22.88507843,529.61602779999998,600.99493410000002,1768.39794899999993,1222.40905799999996,1.557231903");
	    long start1 = System.nanoTime();
	    PrintWriter pw = new PrintWriter(new FileWriter(file));
	    for (int i = 0; i < mb * 1024; i++)
	        pw.println(longLine);
	    pw.close();
	    long time1 = System.nanoTime() - start1;
	    System.out.printf("Took %.3f seconds to write to a %d MB, file rate: %.1f MB/s%n",
	            time1 / 1e9, file.length() >> 20, file.length() * 1000.0 / time1);

	    long start2 = System.nanoTime();
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    for (String line; (line = br.readLine()) != null; ) {
	    }
	    br.close();
	    long time2 = System.nanoTime() - start2;
	    System.out.printf("Took %.3f seconds to read to a %d MB file, rate: %.1f MB/s%n",
	            time2 / 1e9, file.length() >> 20, file.length() * 1000.0 / time2);
	    //file.delete();
	}
}
