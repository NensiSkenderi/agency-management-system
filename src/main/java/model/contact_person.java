package model;

public class contact_person {

	private int contact_id;
	private String contact_name,phone_number;
	private int client_id;
	private int primary;
	
	public contact_person(String contact_name, String phone_number, int client_id, int primary) {
		super();
		this.contact_name = contact_name;
		this.phone_number = phone_number;
		this.client_id = client_id;
		this.primary = primary;
	}
	public int getContact_id() {
		return contact_id;
	}
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int isPrimary() {
		return primary;
	}
	public void setPrimary(int primary) {
		this.primary = primary;
	}
	
	
}
