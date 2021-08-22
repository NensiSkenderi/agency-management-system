package model;

public class agency_list {

	private int agency_list_id;
	private int client_id;
	private int user_id;
	
	public agency_list(int agency_list_id, int client_id, int user_id) {
		super();
		this.agency_list_id = agency_list_id;
		this.client_id = client_id;
		this.user_id = user_id;
	}
	
	public agency_list() {
		super();
	}

	public int getAgency_list_id() {
		return agency_list_id;
	}

	public void setAgency_list_id(int agency_list_id) {
		this.agency_list_id = agency_list_id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
}
