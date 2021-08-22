package model;

public class client_communication_notes {

	private int client_communication_id;
	private String client_comm_notes_desc,agency_name;
	private int client_id;
	
	public client_communication_notes(int client_communication_id, String client_comm_notes_desc, int client_id) {
		super();
		this.client_communication_id = client_communication_id;
		this.client_comm_notes_desc = client_comm_notes_desc;
		this.client_id = client_id;
	}

	public client_communication_notes() {
		
	}
	
	public int getClient_communication_id() {
		return client_communication_id;
	}

	public void setClient_communication_id(int client_communication_id) {
		this.client_communication_id = client_communication_id;
	}

	public String getClient_comm_notes_desc() {
		return client_comm_notes_desc;
	}

	public void setClient_comm_notes_desc(String client_comm_notes_desc) {
		this.client_comm_notes_desc = client_comm_notes_desc;
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
	
}
