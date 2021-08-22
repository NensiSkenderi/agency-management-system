package model;

public class report_agency {

	private String agency_name,primary_biller,primary_accountant,
	invoiced_for_month,biller_notes, client_communication_notes;
	
	private String billing_instructions, accounting_instructions;
	
	public report_agency() {
		
	}

	public report_agency(String agency_name, String primary_biller, String primary_accountant, String invoiced_for_month,
			String biller_notes,String client_communication_notes) {
		super();
		this.agency_name = agency_name;
		this.primary_biller = primary_biller;
		this.primary_accountant = primary_accountant;
		this.invoiced_for_month = invoiced_for_month;
		this.biller_notes = biller_notes;
		this.client_communication_notes = client_communication_notes;
	}

	
	public report_agency(String agency_name, String billing_instructions, String accounting_instructions) {
		super();
		this.agency_name = agency_name;
		this.billing_instructions = billing_instructions;
		this.accounting_instructions = accounting_instructions;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
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

	public String getInvoiced_for_month() {
		return invoiced_for_month;
	}

	public void setInvoiced_for_month(String invoiced_for_month) {
		this.invoiced_for_month = invoiced_for_month;
	}

	public String getBiller_notes() {
		return biller_notes;
	}

	public void setBiller_notes(String biller_notes) {
		this.biller_notes = biller_notes;
	}

	public String getBilling_instructions() {
		return billing_instructions;
	}

	public void setBilling_instructions(String billing_instructions) {
		this.billing_instructions = billing_instructions;
	}

	public String getAccounting_instructions() {
		return accounting_instructions;
	}

	public void setAccounting_instructions(String accounting_instructions) {
		this.accounting_instructions = accounting_instructions;
	}

	public String getClient_communication_notes() {
		return client_communication_notes;
	}

	public void setClient_communication_notes(String client_communication_notes) {
		this.client_communication_notes = client_communication_notes;
	}
	
	
}
