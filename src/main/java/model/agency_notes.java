package model;

public class agency_notes {

	private int agency_notes_id;
	private String notes;
	private int user_id;
	private int agency_id;
	
	public agency_notes(int agency_notes_id, String notes, int user_id, int agency_id) {
		super();
		this.agency_notes_id = agency_notes_id;
		this.notes = notes;
		this.user_id = user_id;
		this.agency_id = agency_id;
	}

	public agency_notes(String notes, int user_id, int agency_id) {
		super();
		this.notes = notes;
		this.user_id = user_id;
		this.agency_id = agency_id;
	}

	public int getAgency_notes_id() {
		return agency_notes_id;
	}

	public void setAgency_notes_id(int agency_notes_id) {
		this.agency_notes_id = agency_notes_id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(int agency_id) {
		this.agency_id = agency_id;
	}
	
}
