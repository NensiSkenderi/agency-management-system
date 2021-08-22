package model;

public class report_tax_cost {

	private String agency_name;
	private String cost_report;
	private String cr_prepared_by;
	private boolean cr_sent_to_client;
	private String cr_notes;
	
	private String tax_return;
	private String tr_prepared_by;
	private boolean tr_sent_to_client;
	private String tr_notes;
	
	public report_tax_cost() {
		
	}

	public report_tax_cost(String agency_name, String cost_report, String cr_prepared_by, boolean cr_sent_to_client,
			String cr_notes, String tax_return, String tr_prepared_by, boolean tr_sent_to_client, String tr_notes) {
		super();
		this.agency_name = agency_name;
		this.cost_report = cost_report;
		this.cr_prepared_by = cr_prepared_by;
		this.cr_sent_to_client = cr_sent_to_client;
		this.cr_notes = cr_notes;
		this.tax_return = tax_return;
		this.tr_prepared_by = tr_prepared_by;
		this.tr_sent_to_client = tr_sent_to_client;
		this.tr_notes = tr_notes;
	}



	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public String getCost_report() {
		return cost_report;
	}

	public void setCost_report(String cost_report) {
		this.cost_report = cost_report;
	}

	public String getCr_prepared_by() {
		return cr_prepared_by;
	}

	public void setCr_prepared_by(String cr_prepared_by) {
		this.cr_prepared_by = cr_prepared_by;
	}

	public boolean isCr_sent_to_client() {
		return cr_sent_to_client;
	}

	public void setCr_sent_to_client(boolean cr_sent_to_client) {
		this.cr_sent_to_client = cr_sent_to_client;
	}

	public String getCr_notes() {
		return cr_notes;
	}

	public void setCr_notes(String cr_notes) {
		this.cr_notes = cr_notes;
	}

	public String getTax_return() {
		return tax_return;
	}

	public void setTax_return(String tax_return) {
		this.tax_return = tax_return;
	}

	public String getTr_prepared_by() {
		return tr_prepared_by;
	}

	public void setTr_prepared_by(String tr_prepared_by) {
		this.tr_prepared_by = tr_prepared_by;
	}

	public boolean isTr_sent_to_client() {
		return tr_sent_to_client;
	}

	public void setTr_sent_to_client(boolean tr_sent_to_client) {
		this.tr_sent_to_client = tr_sent_to_client;
	}

	public String getTr_notes() {
		return tr_notes;
	}

	public void setTr_notes(String tr_notes) {
		this.tr_notes = tr_notes;
	}
	
	
}
