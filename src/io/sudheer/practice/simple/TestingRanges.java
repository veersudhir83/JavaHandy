package io.sudheer.practice.simple;

import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author Sudheer Veeravalli (SSO 502242973)
 *
 */
public class TestingRanges {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String value = "(1-2000),(1,3000000,2),100,!(259),!(10-20)";
		StringBuffer finalString = new StringBuffer();
		int maxValue = 3668;
		Integer prevValue = null;
		Integer iVal = null;
		StringTokenizer st = new StringTokenizer(value, "(),-!", true);
		while (st.hasMoreTokens()) {
			String token = st.nextToken().trim();
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
