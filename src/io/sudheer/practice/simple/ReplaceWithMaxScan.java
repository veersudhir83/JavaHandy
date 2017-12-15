package io.sudheer.practice.simple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 */

/**
 * @author Sudheer Veeravalli (SSO 502242973)
 *
 */
public class ReplaceWithMaxScan {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String temp1 = replaceWithMaxScanNumber("(1-100000,2)",0);
		String temp2 = null;
		try {
			temp2 = getScanCount(temp1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("final:::"+ temp2);
	}
	
	public static String replaceWithMaxScanNumber(String scanValue, int maximumScan) {
		StringBuffer finalString = new StringBuffer();
		int maxValue = maximumScan;
		Integer prevValue = null;
		Integer iVal = null;
		StringTokenizer st = new StringTokenizer(scanValue, "(),-!", true);
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
		return finalString.toString();
	}
	
	public static String getScanCount(String value) throws Exception {
		List<Integer> reading = new ArrayList<Integer>();
		List<Integer> notInLst = new ArrayList<Integer>();
		List<Integer> notIndLst = new ArrayList<Integer>();
		try {
			StringTokenizer st = new StringTokenizer(value, "(),-!", true);
			boolean rangeStart = false,notRange = false;
			Integer prevValue = null;
			Integer iVal = null;
			while (st.hasMoreTokens()) {
				String token = st.nextToken().trim();
				if (token.length() == 0) {
					continue;
				}
				boolean isNumber = false;
				try {
					int ival = (int) Double.parseDouble(token);
					iVal = Integer.valueOf(ival);
					prevValue = iVal;
					if(!notRange)
						reading.add(iVal);
					isNumber = true;
				} catch (Exception ignore) {
					String strEr = ignore.getMessage().toString();
					//DataAccessUtil.traceLogInfo(LOG,"Exception in getIntRange:" + ignore.getMessage());
				}

				if (isNumber) {
					if (rangeStart || notRange) {
						// List to hold range values
						ArrayList<Integer> rageData = new ArrayList<Integer>();
						rageData.add(prevValue);

						while (!")".equals(token) && st.hasMoreTokens()) {
							token = st.nextToken().trim();
							if (",".equals(token) || ")".equals(token) || "-".equals(token)) {
								prevValue = null;
								continue;
							} else {
								try {
									int val = (int) Double.parseDouble(token);
									rageData.add(Integer.valueOf(val));
								} catch (Exception ex) {
									throw new Exception(value);
								}
							}
						}

						// check for valid range data
						if (!notRange && rageData.size() < 2 || rageData.size() > 3) {
							throw new Exception(value);
						}
						int init = rageData.get(0).intValue();
						int last = 999999999;
						if(rageData.size() >= 2)
							last = rageData.get(1).intValue();

						int step = 1;
						if (rageData.size() == 3) {
							step = rageData.get(2).intValue();
						}
						if (init > last) {
							throw new Exception(
									"Range start value is greater than range end value in string: "
											+ value);
						}
						if(notRange) {
							if(rageData.size() >= 1){
								notIndLst.add( Integer.valueOf(init));
							}
							if(rageData.size() == 2){
								notInLst.add(Integer.valueOf(init));
								notInLst.add(Integer.valueOf(last));
							}
						}else {
							for (int i = init + step; i <= last; i = i + step) {
								Integer intr = Integer.valueOf(i);
								if (!reading.contains(intr)) {
									reading.add(intr);
								}
							}
						}
						rangeStart = false;
						notRange = false;
					}
				} else {
					if ("-".equals(token)) {
						if (prevValue == null) {
							throw new Exception(
									"Range start not specified in string: "
											+ value);
						}
						if (!st.hasMoreTokens()) {
							throw new Exception(
									"Range end not specified in string: "
											+ value);
						}

						// make series
						int init = (int) prevValue.intValue();
						int last = (int) Double.parseDouble(st.nextToken()
								.trim());
						if (init > last) {
							throw new Exception(
									"Range start value is greater then range end value string: "
											+ value);
						}

						for (int i = init + 1; i <= last; ++i) {
							Integer val = Integer.valueOf(i);
							if (!reading.contains(val)) {
								reading.add(val);
							}
						}
					}

					else if ("(".equals(token)) {
						rangeStart = true;
					} else if (",".equals(token) || ")".equals(token)) {
						prevValue = null;
						continue;
					}else if("!".equals(token) && "(".equals(st.nextToken().trim()) ){
						notRange = true;
						rangeStart = true;
					} else {
						throw new Exception(value);
					}
				}
			}

		} catch (Exception ex) {
			throw new Exception( "Unsupported/Invalid range...........: " + value);
		}

		Set<Integer> uniq = new LinkedHashSet<Integer>(reading);
		for(Integer val : notIndLst) {
			uniq.remove(val);
		}
		for(int i = 0; i <  notInLst.size() ; i++) {
			List<Integer> temp = new ArrayList<Integer>();
			int limit = ++i;
			for (int j = notInLst.get(i); j <= notInLst.get(limit); ++j) {
				temp.add(j);
			}
			uniq.removeAll(temp);
		}
		StringBuilder ret = new StringBuilder();
		Iterator<Integer> iterator = uniq.iterator();
		int i =0;
		while(iterator.hasNext()){
			ret.append(iterator.next());
			if(iterator.hasNext())
				ret.append(",");
			++i;
		}
		return ret.toString();
	}
	

}
