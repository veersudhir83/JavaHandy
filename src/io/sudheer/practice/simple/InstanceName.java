package io.sudheer.practice.simple;

/**
 * 
 */
/**
 * @author Sudheer Veeravalli (SSO 502242973)
 *
 */
public class InstanceName {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String instanceName = "asep317a-3";
		if(instanceName != null && instanceName.indexOf('-') != -1) {
			int indexHyphen = instanceName.indexOf('-');
			System.out.println("indexHyphen::" + indexHyphen);
			System.out.println("instanceName.length()::" + instanceName.length());
			if(instanceName.length() >= indexHyphen + 2) {
				instanceName = instanceName.substring(indexHyphen + 1, indexHyphen + 2);
			}
		}
		System.out.println("final:" + instanceName);
	}

}
