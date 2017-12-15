package io.sudheer.practice.simple;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
 
public class Compress {
 
    public static void main(String a[]){
     
        FileOutputStream fos = null;
        GZIPOutputStream gos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("D:/gegdc/SV40298/tmp/1.gzip");
            gos = new GZIPOutputStream(fos);
            oos = new ObjectOutputStream(gos);
            String str = "13049642,DEV,NOMODEL-G,123-001/1,NPSS,EU,9880,1,1,9999-12-31 00:00:00,NONE,-999,NONE,NONE      ,2011-11-25 05:16:56,TESTSITE,0,ENGINE,SS,0,1,NONE,NONE,NONE,NONE,10014.06055000000015,-999,3669.47119100000009,-999";
            oos.writeObject(str);
            oos.flush();
            System.out.println("Done... Objects are compressed and stored");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(oos != null) oos.close();
                if(fos != null) fos.close();
            } catch(Exception ex){
                 
            }
        }
    }
}
 