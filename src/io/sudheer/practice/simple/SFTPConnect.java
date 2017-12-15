package io.sudheer.practice.simple;

import com.oroinc.net.ftp.FTPClient;

public class SFTPConnect {

	public static void main(String[] args) {
		
		try {
			System.out.println("Starting FTP Connection");
			FTPClient ftpclnt = new FTPClient();
			ftpclnt.connect("sftp.corporate.com");
			ftpclnt.setDefaultPort(10022);
			ftpclnt.login("ccinteg_user", "xxxx");
			if(ftpclnt.isConnected()) {
				//ftpclnt.changeWorkingDirectory("cca_xml_export");
				System.out.println("Connected to ftp location");
			}
			System.out.println("Before Closing Connection::::::::::;");
			ftpclnt.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
