package io.sudheer.practice.ldapsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.sudheer.practice.ldapsearch.*;

/**
 * @author: Sudheer Veeravalli
 */
public class TestLDAP {
	
	private static AZLookup lookup = new AZLookup();

	public static void main(String[] args) {
		getLdapUserDetails("502075105",true);
		getOrganizationNames("mahindra");
	}
	
	/**
	 * Sudheer: Method to fetch list of all organization names from LDAP
	 * @param orgPattern
	 * @return list of all matching organization names
	 */
	public static void getOrganizationNames(String orgPattern) {
		// Added new implementation to fetch the company names from LDAP - removed the dependency on geaeEJB jar
		String orgValues = "";
		
		System.out.println("orgPattern from request ==>"+orgPattern);
		
		List<AZCompany> companiesList = new ArrayList<>();
		try {
			lookup.connect();
			companiesList = lookup.lookupCompanyNames(orgPattern);
			if (null != companiesList) {
				System.out.println("Located Company Names:: \nSize=" + companiesList.size());
				for(int i=0; i<companiesList.size(); i++) {
					orgValues = orgValues + "," + companiesList.get(i).getCompanyName();
				}
			} 
			lookup.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(orgValues.length()>0)
			System.out.println(orgValues.substring(1));
		else
			System.out.println(orgValues);
		
	}

	public static void getLdapUserDetails(String searchString, boolean searchBySSO) {
		//System.out.println("inside getLdapUserDetails method");
		Map<String,String> userDetails = new HashMap<String,String>();		
		//String sm_User = "502422184";
		String nationality = "";
		String company = "";
		String sso = "";
		String sso_nt_id = "";
		String firstName = "";
		String lastName = "";
		
		try {
			//checking with SSO_ID
			System.out.println("Checking with SSO ID" + searchString);
			
			AZPerson person = null;
			lookup.connect();
			if (searchBySSO) {
				person = lookup.lookupPersonBySSO(searchString);
				if (null != person) {
					System.out.println("Located Person Record using SSO");
				}
			} else {
				person = lookup.lookupPersonByUID(searchString);
				if(null != person) {
					System.out.println("Located Person Record using NT ID");
				}
			}
			
			lookup.close();
			if(null != person) {
				nationality = person.isGePerson()?"Y":"N";
				sso = person.getSSO();
				company = person.getCompany();
				firstName = person.getFirstName();
				lastName = person.getLastName();
				sso_nt_id = person.getUid();
				nationality = nationality != null ? nationality.trim() : "";
				sso = sso != null ? sso.trim() : "";
				company = company != null ? company.trim() : "";
				
			} else {
				System.out.println("Could not locate person record in LDAP");
			}
			userDetails.put(LDAPConstants.LDAP_US_NATIONAL, nationality);
			userDetails.put(LDAPConstants.LDAP_SSO, sso);
			userDetails.put(LDAPConstants.LDAP_COMPANY, company.toUpperCase());
			userDetails.put(LDAPConstants.SSO_FIRST_NAME, firstName.toUpperCase());
			userDetails.put(LDAPConstants.SSO_LAST_NAME, lastName.toUpperCase());
			userDetails.put(LDAPConstants.LDAP_USER_ID, sso_nt_id);

		} catch (Exception ex) {
			System.out.println("Error while fetching user details from LDAP server . "
					+ ex.toString());
		}

		System.out.println("DETAILS FROM LDAP: " + "Company:" + company + ", SSO:" + sso
				+ ", Nationality:" + nationality 
				+ ", SSO_NT_ID:" + sso_nt_id);
		
		//return userDetails;
	}
	
}

