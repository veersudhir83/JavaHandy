package io.sudheer.practice.simple;

import java.util.zip.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Compress2 {
	public static void main(String[] args) {
	    String input = "13050553,DEV,NOMODEL-G,123-001/1,NPSS,P2,9880,1,1,9999-12-31 00:00:00,NONE,-999,NONE,NONE      ,2012-10-05 05:32:10,TESTSITE,1,ENGINE,SS,0,1,NONE,NONE,NONE,NONE,1768.39794899999993,1222.40905799999996,3669.56933600000002,14.69599915,22.88507843,529.61602779999998,600.99493410000002,1768.39794899999993,1222.40905799999996,1.557231903\n"
	    			+ "13050580,DEV,NOMODEL-G,123-001/1,NPSS,P2,9880,1,1,9999-12-31 00:00:00,NONE,-999,NONE,NONE      ,2012-02-22 08:51:57,TESTSITE,2,ENGINE,SS,0,1,NONE,NONE,NONE,NONE,1768.4285890000001,1222.43640099999993,3669.65893599999981,14.69599915,22.8849411,529.61584470000003,600.99371340000005,1768.4285890000001,1222.43640099999993,1.557222605\n"
	    			+ "13050603,DEV,NOMODEL-G,123-001/1,NPSS,P2,9880,1,1,9999-12-31 00:00:00,NONE,-999,NONE,NONE      ,2012-02-22 08:51:57,TESTSITE,3,ENGINE,SS,0,1,NONE,NONE,NONE,NONE,1768.4285890000001,1222.43627900000001,3669.65869100000009,14.6960001,22.88494301,529.61590579999995,600.99371340000005,1768.4285890000001,1222.43627900000001,1.557222605";
	    /*byte[] data = new byte[Short.MAX_VALUE];

	    int len = compressStringToBytes(input, data, data.length);
	    //System.out.println("compressed len::" + len);

	    String output = decompressString(data, len);

	    if (!input.equals(output)) {
	        System.out.println("Test failed");
	    }

	    System.out.println("Input:::" + input + "\nOutput:::" + output);
	    System.out.println("compressed data:::" + compressString(input));*/
	    byte[] b = compress(input.getBytes());
	}

	public static int compressStringToBytes(String data, byte[] output, int len) {
		System.out.println("len heere::" + len);
	    Deflater deflater = new Deflater();
	    deflater.setInput(data.getBytes(Charset.forName("utf-8")));
	    deflater.finish();
	    return deflater.deflate(output, 0, len);
	}
	
	public static byte[] compress(byte[] bytesToCompress)
	{		
		Deflater deflater = new Deflater();
		deflater.setInput(bytesToCompress);
		deflater.finish();

		byte[] bytesCompressed = new byte[Short.MAX_VALUE];

		int numberOfBytesAfterCompression = deflater.deflate(bytesCompressed);

		byte[] returnValues = new byte[numberOfBytesAfterCompression];

		System.arraycopy
		(
			bytesCompressed,
			0,
			returnValues,
			0,
			numberOfBytesAfterCompression
		);
		System.out.println("numberOfBytesAfterCompression:::" + numberOfBytesAfterCompression);
		System.out.println("bytesCompressed:::" + bytesCompressed);
		System.out.println("Decompressed String:::\n" + decompressString(bytesCompressed, numberOfBytesAfterCompression));

		return returnValues;
	}
	
	public static byte[] compressString(String stringToCompress)
	{		
		byte[] returnValues = null;

		try
		{

			returnValues = compress
			(
				stringToCompress.getBytes("UTF-8")
			);
		}
		catch (UnsupportedEncodingException uee)
		{
			uee.printStackTrace();
		}

		return returnValues;
	}

	public static String decompressString(byte[] input, int len) {

	    String result = null;
	    try {
	        Inflater inflater = new Inflater();
	        inflater.setInput(input, 0, len);

	        byte[] output = new byte[Short.MAX_VALUE]; //todo may oveflow, find better solution
	        int resultLength = inflater.inflate(output);
	        inflater.end();

	        result = new String(output, 0, resultLength, Charset.forName("utf-8"));
	    } catch (DataFormatException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    return result;
	}
}
