package io.sudheer.practice.simple;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class GetScanRange2 {
	
	public static void main (String args[]) {
		try {
			System.out.println("Start Time:");
			convertmillistoHrFormat();
			/*int[] ranges = range(1,300000,2);
			StringBuilder s = new StringBuilder();
			for (int var : ranges) {
				s.append(var + ",");
			}
			String scan = (s.toString().subSequence(0, s.toString().length()-1)).toString();*/
			String scan = "(1-200000)";
			System.out.println("output from getScanCount::" + getScanCount(scan));
			System.out.println("End Time:");
			convertmillistoHrFormat();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int[] range(int start, int end, int step) {
	    int n = (int) Math.ceil((end-start)/(double)step);
	    int[] arange = new int[n];
	    for (int i = 0; i < n; i++)
	        arange[i] = i*step+start;
	    return arange;
	}
	
	public static void convertmillistoHrFormat() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String formatted = df.format(new Date());
		
		System.out.println(formatted);
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
					//System.out.println("Exception in getIntRange:" + ignore.getMessage());
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


