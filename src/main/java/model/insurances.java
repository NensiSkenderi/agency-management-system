package model;

public class insurances {

	private int insurance_id;
	private String insurance_name, instructions, agency_name;
	private users user_id;
	private int agency_id;
	
	public insurances(int insurance_id, String insurance_name, String instructions, users user_id, int agency_id) {
		super();
		this.insurance_id = insurance_id;
		this.insurance_name = insurance_name;
		this.instructions = instructions;
		this.user_id = user_id;
		this.agency_id = agency_id;
	}

	public insurances() {
		super();
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public insurances(String insurance_name, String instructions, int agency_id) {
		super();
		this.insurance_name = insurance_name;
		this.instructions = instructions;
		this.agency_id = agency_id;
	}

	public int getInsurance_id() {
		return insurance_id;
	}

	public void setInsurance_id(int insurance_id) {
		this.insurance_id = insurance_id;
	}

	public String getInsurance_name() {
		return insurance_name;
	}

	public void setInsurance_name(String insurance_name) {
		this.insurance_name = insurance_name;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public users getUser_id() {
		return user_id;
	}

	public void setUser_id(users user_id) {
		this.user_id = user_id;
	}

	public int getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(int agency_id) {
		this.agency_id = agency_id;
	}
	
}
