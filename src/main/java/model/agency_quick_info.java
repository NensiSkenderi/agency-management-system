package model;

public class agency_quick_info {

	private int agency_quick_info_id;
	private int client_id;
	private int user_id;
	
	public agency_quick_info(int agency_quick_info_id, int client_id, int user_id) {
		super();
		this.agency_quick_info_id = agency_quick_info_id;
		this.client_id = client_id;
		this.user_id = user_id;
	}
	
	public agency_quick_info() {
		super();
	}

	
	public int getAgency_quick_info_id() {
		return agency_quick_info_id;
	}

	public void setAgency_quick_info_id(int agency_quick_info_id) {
		this.agency_quick_info_id = agency_quick_info_id;
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
