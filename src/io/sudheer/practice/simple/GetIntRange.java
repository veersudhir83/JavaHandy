package io.sudheer.practice.simple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 
 */

/**
 * @author Sudheer Veeravalli (SSO 502242973)
 *
 */
public class GetIntRange {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(getIntRange("Analynum", "6,-999"));
	}
	
	public static boolean proceedForTokens (String value) {
		boolean proceed= true;
		try {
			proceed =false;
		} catch (Exception ignore) {

			//System.out.println("Exception in getIntRange proceedForTokens:" + ignore.getMessage());
		}
		System.out.println("proceed value::" + proceed);
		return proceed;
	}
	
	public static String getIntRange(String fieldName, String value) throws Exception {
		List<String> reading = new ArrayList<String>();
		StringBuilder rangeLs = new StringBuilder();
		try {
			System.out.println("value in getIntRange::" + value);
			// modified by rajeswari to check -999 values
			// if value is -999 no need of tokenizing as it is the single value.
			//StringTokenizer st = new StringTokenizer(value, "(),-!", true);
			StringTokenizer st =null;
			if(proceedForTokens(value)) 
				 st = new StringTokenizer(value, "(),!-", true);
			else
				 st = new StringTokenizer(value, "", true);
			// end by Rajeswari.
			boolean rangeStart = false,notInRange = false;
			Integer prevValue = null;
			Integer iVal = null;
			int init =0,last=0,n1=0,n2 =0;
			while (st.hasMoreTokens()) {
				String token = st.nextToken().trim();
				System.out.println("Token::" + token);
				if (token.length() == 0) {
					continue;
				}
				boolean isNumber = false;
				try {
					int ival = (int) Double.parseDouble(token);
					iVal = Integer.valueOf(ival);
					prevValue = iVal;
					reading.add(iVal.toString());
					isNumber = true;
				} catch (Exception ignore) {
					//DataAccessUtil.traceLogInfo(LOG,"Exception in getIntRange:" + ignore.getMessage());
				}
				System.out.println("isNumber::" + isNumber);
				if (isNumber )  {
					if (rangeStart || notInRange) {
						ArrayList<Integer> rageData = new ArrayList<Integer>();
						rageData.add(prevValue);
						while (!")".equals(token) && st.hasMoreTokens()) {
							token = st.nextToken().trim();
							if ("-".equals(token) || ",".equals(token) || ")".equals(token)) {
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

						if (!notInRange && rageData.size() < 2 || rageData.size() > 3) {
							throw new Exception(value);
						}
						if(notInRange){
							n1 = rageData.get(0).intValue();
							 n2 = 999999999;
							 if(rageData.size() >= 2)
							  	n2 = rageData.get(1).intValue();
						} else  {
						 init = rageData.get(0).intValue();
						last = rageData.get(1).intValue();
						}
						int step = 1;
						if (rageData.size() == 3) {
							step = rageData.get(2).intValue();
						}
						if (init > last) {
							throw new Exception("Range start value is greater then range end value in string: ");
						}
						if(notInRange){
							if(rageData.size() == 1 && (init <= n1 && n1 <= last)){
								reading.remove(reading.size()-1);
								if(rangeLs.length() > 0) 
									rangeLs.append(" AND ");
								rangeLs.append( " " + fieldName + " NOT IN ( " + Integer.valueOf(n1).toString()+" ) ");
							}else if(rageData.size() == 2 && (init <= n1 && last >= n2 )) {
								reading.remove(reading.size()-1);
								if(rangeLs.length() > 0) 
									rangeLs.append(" AND ");
								rangeLs.append(" " + fieldName + " NOT  IN ( " + Integer.valueOf(n1).toString() + " to " + Integer.valueOf(n2).toString() + " ) ");
							}else {
								throw new Exception("NOT IN Value(s) should be in the range of followed value : ");
							}
						}else if(step==1) {
							reading.remove(reading.size()-1);
							if(rangeLs.length() > 0) 
								rangeLs.append(" OR ");
							// added by rajeswari to support RDG =(1-10)
							/* commented by rajeswari
							if(fieldName!=null && (fieldName.equalsIgnoreCase(TdmDataConstants.KEY_READING_NUM) ||  fieldName.equalsIgnoreCase(TdmDataConstants.PL_KEY_TDM_READING_NUM) ))
								 rangeLs.append( " " + fieldName + " IN ( " + expandRanges(init, last)  + " ) " );
							else */
							rangeLs.append( " " + fieldName + " IN ( " + Integer.valueOf(init).toString() + " to " + Integer.valueOf(last).toString() + " ) ");
						// end by rajeswari.
						//	rangeLs.append( " " + fieldName + " IN ( " + Integer.valueOf(init).toString() + " to " + Integer.valueOf(last).toString() + " ) ");
						} else {
							reading.remove(reading.size()-1);
							if(rangeLs.length() > 0) 
								rangeLs.append(" OR ");
							rangeLs.append( " (" + fieldName + " IN ( " + Integer.valueOf(init).toString() + " to " + Integer.valueOf(last).toString()
									+ " ) AND ( " + fieldName + " MOD " + step + " ) = ( " + init + " MOD " + step + " ))");
						}
						rangeStart = false;
						notInRange= false;
					}
				} else {
					if ("-".equals(token)) {
						if (prevValue == null) {
							throw new Exception("Unsupported/Invalid range is specified in the key 1:");
						}
						if (!st.hasMoreTokens()) {
							throw new Exception("Unsupported/Invalid range is specified in the key 2:");
						}
						init = last = 0;
						 init = (int) prevValue.intValue();
						 last = (int) Double.parseDouble(st.nextToken().trim());
						if (init > last) {
							throw new Exception( "Range start value is greater then range end value string: " );
						}
						reading.remove(reading.size()-1);
						if(rangeLs.length() > 0) 
							rangeLs.append(" OR ");
						//  modified by rajeswari as part of expand ranges, we need to expand ranges only for rdg
					/*
						if(fieldName!=null && (fieldName.equalsIgnoreCase(TdmDataConstants.KEY_READING_NUM) ||  fieldName.equalsIgnoreCase(TdmDataConstants.PL_KEY_TDM_READING_NUM) ))
							 rangeLs.append( " " + fieldName + " IN ( " + expandRanges(init, last)  + " ) " );
						else */
						rangeLs.append( " " + fieldName + " IN ( " + Integer.valueOf(init).toString() + " to " + Integer.valueOf(last).toString() + " ) ");
					// end by rajeswari.
					} else if ("(".equals(token)) {
						rangeStart = true;
					} else if (",".equals(token) || ")".equals(token))	{
						prevValue = null;
						continue;
					}else if("!".equals(token) && "(".equals(st.nextToken().trim()) ){
						notInRange = true;
						rangeStart = true;
					} else {
						throw new Exception("Unsupported/Invalid range is specified in the key 3:");
					}
				}
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		StringBuilder ret = new StringBuilder();
		if(reading.size() > 0) {
			LinkedHashSet<String> uniq = new LinkedHashSet<String>(reading);
			if(null != uniq &&! uniq.isEmpty()){
				ret.append(fieldName).append(" IN (");
				Iterator<String> itr = uniq.iterator();
				while(itr.hasNext()){
					ret.append((String)itr.next());
					if(itr.hasNext())
						ret.append(",");
				}
				ret.append(")");
			}
		  if(rangeLs.length() > 0)
	    	   ret.append(" OR ").append(rangeLs);
		}
		else 
			ret.append(rangeLs);
		
		return  "(" + ret.toString() + ")";
	

	}

}
