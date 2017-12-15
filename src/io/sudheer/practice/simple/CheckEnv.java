package io.sudheer.practice.simple;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Sudheer Veeravalli (SSO 502242973)
 *
 */
public class CheckEnv {
	private static final short SERVER_PROD  = 0;
	private static final short SERVER_LOCAL = 1;
	private static final short SERVER_OTHER_DEV = 2;
	private static final short SERVER_OTHER_QA = 3;
	
	private static short CURRENT_SERVER = SERVER_OTHER_DEV;
	
	public static void main(final String[] args) {
		try { 
			File fXmlFile = new File("D://gegdc//SV40298//WorkSpace//sample//src//environment.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize(); 
			NodeList nList = doc.getElementsByTagName("environments");
			String appEnvironment = "";
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
					Element eElement = (Element) nNode; 
					appEnvironment = eElement.getTextContent().trim().toUpperCase();
				}
			}
			if(null != appEnvironment) {
				if (appEnvironment.equals("SERVER_PROD")) {
					CURRENT_SERVER = 0;
				}
				if (appEnvironment.equals("SERVER_LOCAL")) {
					CURRENT_SERVER = 1;
				}
				if (appEnvironment.equals("SERVER_OTHER_DEV")) {
					CURRENT_SERVER = 2;
				}
				if (appEnvironment.equals("SERVER_OTHER_QA")) {
					CURRENT_SERVER = 3;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
