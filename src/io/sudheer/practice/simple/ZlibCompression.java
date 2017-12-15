package io.sudheer.practice.simple;

import java.util.zip.*;
import java.io.*;

/**
 * Example program to demonstrate how to use zlib compression with
 * Java.
 * Inspired by http://stackoverflow.com/q/6173920/600500.
 */
public class ZlibCompression {

    /**
     * Compresses a file with zlib compression.
     */
    public static void compressFile(File raw, File compressed)
        throws IOException
    {
        InputStream in = new FileInputStream(raw);
        OutputStream out =
            new DeflaterOutputStream(new FileOutputStream(compressed));
        shovelInToOut(in, out);
        in.close();
        out.close();
    }
    
    public static void compressStringToStream(String input, OutputStream outStr)
    throws IOException
	{
    	InputStream in = new ByteArrayInputStream(input.getBytes());
	    OutputStream out =
	        new DeflaterOutputStream(outStr);
	    shovelInToOut(in, out);
	    in.close();
	    out.close();
	}
    
    public static void compressStringToFile(String input, File compressed)
    throws IOException
	{
    	InputStream in = new ByteArrayInputStream(input.getBytes());
    	OutputStream out =
            new DeflaterOutputStream(new FileOutputStream(compressed));
	    shovelInToOut(in, out);
	    in.close();
	    out.close();
	}

    /**
     * Decompresses a zlib compressed file.
     */
    public static void decompressFile(File compressed, File raw)
        throws IOException
    {
        InputStream in =
            new InflaterInputStream(new FileInputStream(compressed));
        OutputStream out = new FileOutputStream(raw);
        shovelInToOut(in, out);
        in.close();
        out.close();
    }
        
    /**
     * Shovels all data from an input stream to an output stream.
     */
    private static void shovelInToOut(InputStream in, OutputStream out)
        throws IOException
    {
        byte[] buffer = new byte[Short.MAX_VALUE];
        int len;
        while((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }    	
    }


    /**
     * Main method to test it all.
     */
    public static void main(String[] args) throws IOException, DataFormatException {
    	/*String input = "13050553,DEV,NOMODEL-G,123-001/1,NPSS,P2,9880,1,1,9999-12-31 00:00:00,NONE,-999,NONE,NONE      ,2012-10-05 05:32:10,TESTSITE,1,ENGINE,SS,0,1,NONE,NONE,NONE,NONE,1768.39794899999993,1222.40905799999996,3669.56933600000002,14.69599915,22.88507843,529.61602779999998,600.99493410000002,1768.39794899999993,1222.40905799999996,1.557231903\n"
			+ "13050580,DEV,NOMODEL-G,123-001/1,NPSS,P2,9880,1,1,9999-12-31 00:00:00,NONE,-999,NONE,NONE      ,2012-02-22 08:51:57,TESTSITE,2,ENGINE,SS,0,1,NONE,NONE,NONE,NONE,1768.4285890000001,1222.43640099999993,3669.65893599999981,14.69599915,22.8849411,529.61584470000003,600.99371340000005,1768.4285890000001,1222.43640099999993,1.557222605\n"
			+ "13050603,DEV,NOMODEL-G,123-001/1,NPSS,P2,9880,1,1,9999-12-31 00:00:00,NONE,-999,NONE,NONE      ,2012-02-22 08:51:57,TESTSITE,3,ENGINE,SS,0,1,NONE,NONE,NONE,NONE,1768.4285890000001,1222.43627900000001,3669.65869100000009,14.6960001,22.88494301,529.61590579999995,600.99371340000005,1768.4285890000001,1222.43627900000001,1.557222605";
        */
    	File compressed = new File("D:/gegdc/SV40298/jboss4.0.3/server/default/log/opt/shared/data/tdmdataaccess/temp/tdm-tmp3-1393577808.tzf");
    	System.out.println("compressed length::" + compressed.length());
        //compressStringToFile(input, compressed);
        
        //compressFile(new File("D:/gegdc/SV40298/ActiveProcess/ActiveProcessWorkSpace/sample/src/input.csv"), compressed);
        decompressFile(compressed, new File("D:/gegdc/SV40298/ActiveProcess/ActiveProcessWorkSpace/sample/src/decompressedC++.txt"));
    }
}