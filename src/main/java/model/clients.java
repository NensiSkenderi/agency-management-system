package model;

public class clients {

	private int client_id;
	private String agency_name,address,phone,fax,comments,
	primary_biller,primary_accountant,secondary_biller,secondary_accountant;
	private String npi,ptan,tax_id;
	
	private String dde_login,dde_password,software_login,software_password;
	
	public clients() {
		
	}
	
	public clients(int client_id, String agency_name, String address, String phone, String fax, String npi, String ptan,
			String tax_id, String comments) {
		super();
		this.client_id = client_id;
		this.agency_name = agency_name;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
		this.npi = npi;
		this.ptan = ptan;
		this.tax_id = tax_id;
		this.comments = comments;
	}
	
	public clients(String agency_name, String address, String phone, String fax, String comments, String primary_biller,
			String primary_accountant, String secondary_biller, String secondary_accountant, String npi, String ptan,
			String tax_id) {
		super();
		this.agency_name = agency_name;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
		this.comments = comments;
		this.primary_biller = primary_biller;
		this.primary_accountant = primary_accountant;
		this.secondary_biller = secondary_biller;
		this.secondary_accountant = secondary_accountant;
		this.npi = npi;
		this.ptan = ptan;
		this.tax_id = tax_id;
	}

	public clients(String agency_name, String address, String phone, String fax, String npi, String ptan,
			String tax_id, String comments) {
		super();
		this.agency_name = agency_name;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
		this.npi = npi;
		this.ptan = ptan;
		this.tax_id = tax_id;
		this.comments = comments;
	}

	public String getDde_login() {
		return dde_login;
	}

	public void setDde_login(String dde_login) {
		this.dde_login = dde_login;
	}

	public String getDde_password() {
		return dde_password;
	}

	public void setDde_password(String dde_password) {
		this.dde_password = dde_password;
	}

	public String getSoftware_login() {
		return software_login;
	}

	public void setSoftware_login(String software_login) {
		this.software_login = software_login;
	}

	public String getSoftware_password() {
		return software_password;
	}

	public void setSoftware_password(String software_password) {
		this.software_password = software_password;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}

	public String getPtan() {
		return ptan;
	}

	public void setPtan(String ptan) {
		this.ptan = ptan;
	}

	public String getTax_id() {
		return tax_id;
	}

	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPrimary_biller() {
		return primary_biller;
	}

	public void setPrimary_biller(String primary_biller) {
		this.primary_biller = primary_biller;
	}

	public String getPrimary_accountant() {
		return primary_accountant;
	}

	public void setPrimary_accountant(String primary_accountant) {
		this.primary_accountant = primary_accountant;
	}

	public String getSecondary_biller() {
		return secondary_biller;
	}

	public void setSecondary_biller(String secondary_biller) {
		this.secondary_biller = secondary_biller;
	}

	public String getSecondary_accountant() {
		return secondary_accountant;
	}

	public void setSecondary_accountant(String secondary_accountant) {
		this.secondary_accountant = secondary_accountant;
	}
	
}
