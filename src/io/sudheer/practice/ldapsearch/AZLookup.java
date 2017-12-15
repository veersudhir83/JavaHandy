/**
 * 
 */
package io.sudheer.practice.ldapsearch;

import java.util.ArrayList;
import java.util.List;

import com.unboundid.ldap.sdk.Attribute;
import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;

/** Utility class providing methods to query the AZ LDAP directory for
 *  user information.
 *  @author Sudheer Veeravalli
 *	Base DN for all: o=ae.ge.com
 *
 *  Default values for LDAP servers:
 *	PROD: dsep006a.ae.ge.com:3890
 *	QA: dseq006b.ae.ge.com:3890
 *	DEV: dsed006a.ae.ge.com:3890
 */
public class AZLookup {
	
	private final static String DEFAULT_SERVER_NAME = "dsep006a.ae.ge.com";
	private final static int DEFAULT_PORT_NUMBER = 3890;
	private LDAPConnection connection = null;
	private String serverName = DEFAULT_SERVER_NAME; //The default is the production server. This is safe because we only perform read operations.
	private int portNumber = DEFAULT_PORT_NUMBER;
	private final static String BASE_DN = "ou=people, o=ae.ge.com";
	private final static String BASE_COMPANY = "ou=companies, o=ae.ge.com";
	private final static String ATTR_ALT_UID = "altuid";
	private final static String ATTR_UID = "uid";
	private final static String ATTR_GE_PERSON = "geaeusperson";
	private final static String ATTR_FIRST_NAME = "givenName";
	private final static String ATTR_MIDDLE_NAME = "geaemiddlename";
	private final static String ATTR_LAST_NAME = "sn";
	//private final static String ATTR_COUNTRY_OF_RESIDENCY = "geaecountryofresidency";
	//private final static String ATTR_COUNTRY_OF_CITIZENSHIP = "geaecountryofcitizenship";
	private final static String ATTR_COMPANY = "geaecompanyname";
	private final static String ATTR_BUSINESS_LEGAL_NAME = "geaelegalentityname";
	private final static String ATTR_CN = "cn";

	/** Default constructor. Creates a new connection object. Actual connection
	 * to the LDAP server is not created until invoking of the connect() method.
	 * 
	 */
	public AZLookup() {
		super();
		connection = new LDAPConnection();
	}
	
	/** Constructor. Invokes the default constructor to create a connection object and
	 * sets the host name and port number for the LDAP server.
	 * @param LDAP server name or IP address
	 * @param LDAP port number
	 */
	public AZLookup(final String serverName, final int portNumber) {
		this();
		setServerName(serverName);
		setPortNumber(portNumber);
	}

	/** Returns the LDAP server name
	 * 
	 * @return LDAP host name
	 */
	public String getServerName() {
		return serverName;
	}

	/** Sets the LDAP server name
	 * 
	 * @param LDAP host name or IP address
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/** Returns the LDAP server port
	 * 
	 * @return LDAP server port
	 */
	public int getPortNumber() {
		return portNumber;
	}

	/** Sets the LDAP server port
	 * 
	 * @param LDAP server port
	 */
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	
	/** Establishes connection to the LDAP server using the host name and port number
	 * set prior to invoking this method. Throws exception if the connection fails.
	 * @throws AZLookupException
	 */
	public void connect() throws AZLookupException {
		
		if ((null == serverName) || (serverName.isEmpty())) {
			throw new AZLookupException("The LDAP host name (server name) is not configured!");
		}
		
		if (portNumber <= 0) {
			throw new AZLookupException("Invalid port number!");
		}
		
		try {
			connection.connect(serverName, portNumber);
		} catch (LDAPException ex) {
			AZLookupException nex = new AZLookupException("Cannot connect to the LDAP server", ex);
			nex.setStackTrace(ex.getStackTrace());
			throw nex;
		}
	}
	
	/** Returns true only if the connection object is not null and it is connected to the LDAP server
	 * 
	 * @return true if connected to the LDAP server
	 */
	public boolean isConnected() {
		return ((null != connection) && (connection.isConnected()));
	}
	
	/** Closes the connection to the LDAP server if it is connected. Otherwise simply exits.
	 * 
	 */
	public void close() {
		if (isConnected()) {
			connection.close();
		}
	}
	
	private AZPerson lookup(final Filter filter) throws AZLookupException {
		
		AZPerson person = null;
		SearchRequest request = new SearchRequest(BASE_DN, SearchScope.SUB, filter);
		
		try {
			SearchResult result = connection.search(request);
			if ((null != result) && (result.getEntryCount() > 0)) {
				SearchResultEntry entry = result.getSearchEntries().get(0);
				if (null != entry) {
					Attribute uidAttr = entry.getAttribute(ATTR_UID);
					Attribute ssoAttr = entry.getAttribute(ATTR_ALT_UID);
					Attribute ecStatusAttr = entry.getAttribute(ATTR_GE_PERSON);
					Attribute firstNameAttr = entry.getAttribute(ATTR_FIRST_NAME);
					Attribute middleNameAttr = entry.getAttribute(ATTR_MIDDLE_NAME);
					Attribute lastNameAttr = entry.getAttribute(ATTR_LAST_NAME);
					//Attribute countryAttr = entry.getAttribute(ATTR_COUNTRY_OF_RESIDENCY);
					//Attribute citizenshipAttr = entry.getAttribute(ATTR_COUNTRY_OF_CITIZENSHIP);
					Attribute companyAttr = entry.getAttribute(ATTR_COMPANY);
					Attribute gePersonAttr = entry.getAttribute(ATTR_GE_PERSON);
					Attribute companyLegalNameAttr = entry.getAttribute(ATTR_BUSINESS_LEGAL_NAME);
					
					String ecStatusStr = (null != ecStatusAttr) ? ecStatusAttr.getValue() : null;
					boolean ecStatus = (null != ecStatusStr) ? (ecStatusStr.equalsIgnoreCase("Y")) : false;
					String firstName = (null != firstNameAttr) ? firstNameAttr.getValue() : null;
					String middleName = (null != middleNameAttr) ? middleNameAttr.getValue() : null;
					String lastName = (null != lastNameAttr) ? lastNameAttr.getValue() : null;
					//String country = (null != countryAttr) ? countryAttr.getValue() : null;
					//String citizenship = (null != citizenshipAttr) ? citizenshipAttr.getValue() : null;
					String company = (null != companyAttr) ? companyAttr.getValue() : null;
					String uid = (null != uidAttr) ? uidAttr.getValue() : null;
					String sso = (null != ssoAttr) ? ssoAttr.getValue() : null;
					String gePersonStr = (null != gePersonAttr) ? gePersonAttr.getValue() : null;
					boolean gePerson = ((null != gePersonStr) && (gePersonStr.equalsIgnoreCase("Y")));
					String companyLegalName = (null != companyLegalNameAttr) ? companyLegalNameAttr.getValue() : null;
					
					person = new AZPerson();
					person.setSSO(sso);
					person.setUid(uid);
					person.setFirstName(firstName);
					person.setMiddleName(middleName);
					person.setLastName(lastName);
					person.setGePerson(gePerson);
					person.setCompany(company);
					person.setCompanyLegalName(companyLegalName);
					person.setExportControl(ecStatus);
				}
			}
		} catch (LDAPException ex) {
			AZLookupException nex = new AZLookupException("Error while quering LDAP!", ex);
			nex.setStackTrace(ex.getStackTrace());
			throw nex;
		}		
		
		return person;
	}
	
	public AZPerson lookupPersonBySSO(final String sso) throws AZLookupException {
		return lookup(Filter.createEqualityFilter(ATTR_ALT_UID, sso));
	}
	
	public AZPerson lookupPersonByUID(final String uid) throws AZLookupException {
		return lookup(Filter.createEqualityFilter(ATTR_UID, uid));
	}
	
	/**
	 * Method to return the list of company names from LDAP
	 * @param filter
	 * @return companiesList
	 * @throws AZLookupException
	 */
	private List<AZCompany> lookupCompanies(final Filter filter) throws AZLookupException {
		List<AZCompany> companiesList = new ArrayList<AZCompany>();
		SearchRequest request = new SearchRequest(BASE_COMPANY, SearchScope.SUB, filter);
		AZCompany company = null;
		try {
			SearchResult result = connection.search(request);
			if ((null != result) && (result.getEntryCount() > 0)) {
				SearchResultEntry entry = null;
				for(int i=0; i<result.getEntryCount(); i++) {
					entry = result.getSearchEntries().get(i);
					if (null != entry) {
						Attribute compNameAttr = entry.getAttribute(ATTR_CN);
						company = new AZCompany();
						company.setCompanyName((null != compNameAttr) ? compNameAttr.getValue() : null);
						companiesList.add(company);
					}
				}
			}
		} catch (LDAPException ex) {
			AZLookupException nex = new AZLookupException("Error while quering LDAP!", ex);
			nex.setStackTrace(ex.getStackTrace());
			throw nex;
		}
		return companiesList;
	}
	
	public List<AZCompany> lookupCompanyNames(final String searchPattern) throws AZLookupException {
		try {
			return lookupCompanies(Filter.create("(" + ATTR_CN + "=" + searchPattern + "*)"));
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
