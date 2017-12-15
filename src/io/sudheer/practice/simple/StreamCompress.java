package io.sudheer.practice.simple;

import java.util.zip.*;
import java.io.*;

public class StreamCompress {
	
	public static void compressString (String input) throws IOException, DataFormatException {
		Deflater compress = new Deflater();
		byte[] compressedData = new byte[8192];
		compress.setInput(input.getBytes());
        compress.finish();
        int compressLength = compress.deflate(compressedData, 0, compressedData.length);
        out.write(compressedData, 0, compressLength);
        System.out.println("Wrote 1 line");
	}
    
    public static void compressStringToFile(String input)
    throws IOException
	{
    	InputStream in = new ByteArrayInputStream(input.getBytes());    	
	    shovelInToOut(in, out);
	    in.close();
	    //out.close();
	}

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
        
    private static void shovelInToOut(InputStream in, OutputStream out)
        throws IOException
    {
        byte[] buffer = new byte[8192];
        int len;
        while((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }    	
    }
    public static FileOutputStream out = null; 
        
    public static void main(String[] args) throws IOException, DataFormatException {
    	String input = "A";
    	String input2 = "B";
    	out = new FileOutputStream("D:/gegdc/SV40298/ActiveProcess/ActiveProcessWorkSpace/sample/src/compressed.tzf", true);
    	
    	compressString(input);
    	compressString(input2);
        
        out.close();
        File compressed = new File("D:/gegdc/SV40298/ActiveProcess/ActiveProcessWorkSpace/sample/src/compressed.tzf");
        System.out.println("compressed length::" + compressed.length());
        decompressFile(compressed, new File("D:/gegdc/SV40298/ActiveProcess/ActiveProcessWorkSpace/sample/src/decompressed.txt"));
    }
}