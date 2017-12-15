package io.sudheer.practice.simple;

import java.util.StringTokenizer;

/**
 * 
 */

/**
 * @author Sudheer Veeravalli (SSO 502242973)
 *
 */
public class ScanNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			StringBuffer finalString = new StringBuffer();
			int maxValue = 2339;
			Integer prevValue = null;
			Integer iVal = null;
			StringTokenizer st = new StringTokenizer("2551763", "(),-!", true);
			while (st.hasMoreTokens()) {
				String token = st.nextToken().trim();
				System.out.println("token::" + token);
				if (token.length() == 0) {
					continue;
				}
				boolean isNumber = false;
				try {
					int ival = (int) Double.parseDouble(token);
					iVal = Integer.valueOf(ival);
					isNumber = true;
					if(Integer.valueOf(iVal) > maxValue) {
						token = ""+maxValue;
					}
				} catch (Exception ignore) {
					//System.out.println("Exception in getIntRange:" + ignore.getMessage());
				}
				finalString.append(token);
			}
			System.out.println("Final String:::" + finalString.toString());
	}

}
