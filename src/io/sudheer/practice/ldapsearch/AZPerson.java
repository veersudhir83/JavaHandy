package io.sudheer.practice.ldapsearch;

/** Data object representing a single person details from the AZ directory.
 * 
 * @author Sudheer Veeravalli
 *
 */
public class AZPerson {
	
	private String firstName = null;
	private String middleName = null;
	private String lastName = null;
	private String SSO = null;
	private String uid = null;
	private boolean gePerson = false;
	private String company = null;
	private String companyLegalName = null;
	private boolean ecStatus = false;
	

	/** Default constructor
	 * 
	 */
	public AZPerson() {
		super();
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getSSO() {
		return SSO;
	}


	public void setSSO(String sSO) {
		SSO = sSO;
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	/** Returns true if the person is considered GE person.
	 * 
	 * @return true if GE person
	 */
	public boolean isGePerson() {
		return gePerson;
	}

	/** Sets the GE person flag
	 * 
	 * @param set to true if GE person
	 */
	public void setGePerson(boolean gePerson) {
		this.gePerson = gePerson;
	}

	public String getCompanyLegalName() {
		return companyLegalName;
	}

	public void setCompanyLegalName(String companyLegalName) {
		this.companyLegalName = companyLegalName;
	}

	public boolean isExportControl() {
		return ecStatus;
	}

	public void setExportControl(boolean exportControl) {
		this.ecStatus = exportControl;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getName() + ": ");
		
		sb.append("sso=\"" + getSSO() + "\", ");
		sb.append("uid=\"" + getUid() + "\", " );
		sb.append("firstName=\"" + getFirstName() + "\", ");
		sb.append("middleName=\"" + getMiddleName() + "\", ");
		sb.append("lastName=\"" + getLastName() + "\", ");
		sb.append("gePerson=" + isGePerson() + ", ");
		sb.append("company=\"" + getCompany() + "\", ");
		sb.append("companyLegalName=\"" + getCompanyLegalName() + "\", ");
		sb.append("exportControl=" + isExportControl());
		
		return sb.toString();
	}
	
	

}
