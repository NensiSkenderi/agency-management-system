package model;

public class agency_users {

	private clients client_id;
	private users user_id;
	
	public clients getClient_id() {
		return client_id;
	}
	public void setClient_id(clients client_id) {
		this.client_id = client_id;
	}
	public users getUser_id() {
		return user_id;
	}
	public void setUser_id(users user_id) {
		this.user_id = user_id;
	}
	
	public agency_users(clients client_id, users user_id) {
		super();
		this.client_id = client_id;
		this.user_id = user_id;
	}
	
}
