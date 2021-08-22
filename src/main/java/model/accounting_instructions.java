package model;

import java.sql.Date;

public class accounting_instructions {

	private int accounting_instructions_id,agency_id,sent_tax_return,sent_cost_report,book_keeping,payroll;
	private String preparer_tax_return,preparer_cost_report,additional_info_tax_return,additional_info_cost_report,intermediary;
	private Date due_date_tax_return,date_completed_tax_return,due_date_cost_report,date_completed_cost_report;
	
	private String agency_name;
	
	public accounting_instructions() {
		
	}
	
	public String getAgency_name() {
		return agency_name;
	}


	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public accounting_instructions(int agency_id, int sent_tax_return, int sent_cost_report, int book_keeping,
			int payroll, String preparer_tax_return, String preparer_cost_report, String additional_info_tax_return,
			String additional_info_cost_report, Date due_date_tax_return, Date date_completed_tax_return,
			Date due_date_cost_report, Date date_completed_cost_report) {
		super();
		this.agency_id = agency_id;
		this.sent_tax_return = sent_tax_return;
		this.sent_cost_report = sent_cost_report;
		this.book_keeping = book_keeping;
		this.payroll = payroll;
		this.preparer_tax_return = preparer_tax_return;
		this.preparer_cost_report = preparer_cost_report;
		this.additional_info_tax_return = additional_info_tax_return;
		this.additional_info_cost_report = additional_info_cost_report;
		this.due_date_tax_return = due_date_tax_return;
		this.date_completed_tax_return = date_completed_tax_return;
		this.due_date_cost_report = due_date_cost_report;
		this.date_completed_cost_report = date_completed_cost_report;
	}
	

	public accounting_instructions(int accounting_instructions_id, int agency_id, int sent_tax_return,
			int sent_cost_report, int book_keeping, int payroll, String preparer_tax_return,
			String preparer_cost_report, String additional_info_tax_return, String additional_info_cost_report,
			Date due_date_tax_return, Date date_completed_tax_return, Date due_date_cost_report,
			Date date_completed_cost_report, String agency_name) {
		this.accounting_instructions_id = accounting_instructions_id;
		this.agency_id = agency_id;
		this.sent_tax_return = sent_tax_return;
		this.sent_cost_report = sent_cost_report;
		this.book_keeping = book_keeping;
		this.payroll = payroll;
		this.preparer_tax_return = preparer_tax_return;
		this.preparer_cost_report = preparer_cost_report;
		this.additional_info_tax_return = additional_info_tax_return;
		this.additional_info_cost_report = additional_info_cost_report;
		this.due_date_tax_return = due_date_tax_return;
		this.date_completed_tax_return = date_completed_tax_return;
		this.due_date_cost_report = due_date_cost_report;
		this.date_completed_cost_report = date_completed_cost_report;
		this.agency_name = agency_name;
	}
	

	public String getIntermediary() {
		return intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	public int getAccounting_instructions_id() {
		return accounting_instructions_id;
	}

	public void setAccounting_instructions_id(int accounting_instructions_id) {
		this.accounting_instructions_id = accounting_instructions_id;
	}

	public int getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(int agency_id) {
		this.agency_id = agency_id;
	}

	public int getSent_tax_return() {
		return sent_tax_return;
	}

	public void setSent_tax_return(int sent_tax_return) {
		this.sent_tax_return = sent_tax_return;
	}

	public int getSent_cost_report() {
		return sent_cost_report;
	}

	public void setSent_cost_report(int sent_cost_report) {
		this.sent_cost_report = sent_cost_report;
	}

	public int getBook_keeping() {
		return book_keeping;
	}

	public void setBook_keeping(int book_keeping) {
		this.book_keeping = book_keeping;
	}

	public int getPayroll() {
		return payroll;
	}

	public void setPayroll(int payroll) {
		this.payroll = payroll;
	}

	public String getPreparer_tax_return() {
		return preparer_tax_return;
	}

	public void setPreparer_tax_return(String preparer_tax_return) {
		this.preparer_tax_return = preparer_tax_return;
	}

	public String getPreparer_cost_report() {
		return preparer_cost_report;
	}

	public void setPreparer_cost_report(String preparer_cost_report) {
		this.preparer_cost_report = preparer_cost_report;
	}

	public String getAdditional_info_tax_return() {
		return additional_info_tax_return;
	}

	public void setAdditional_info_tax_return(String additional_info_tax_return) {
		this.additional_info_tax_return = additional_info_tax_return;
	}

	public String getAdditional_info_cost_report() {
		return additional_info_cost_report;
	}

	public void setAdditional_info_cost_report(String additional_info_cost_report) {
		this.additional_info_cost_report = additional_info_cost_report;
	}

	public Date getDue_date_tax_return() {
		return due_date_tax_return;
	}

	public void setDue_date_tax_return(Date due_date_tax_return) {
		this.due_date_tax_return = due_date_tax_return;
	}

	public Date getDate_completed_tax_return() {
		return date_completed_tax_return;
	}

	public void setDate_completed_tax_return(Date date_completed_tax_return) {
		this.date_completed_tax_return = date_completed_tax_return;
	}

	public Date getDue_date_cost_report() {
		return due_date_cost_report;
	}

	public void setDue_date_cost_report(Date due_date_cost_report) {
		this.due_date_cost_report = due_date_cost_report;
	}

	public Date getDate_completed_cost_report() {
		return date_completed_cost_report;
	}

	public void setDate_completed_cost_report(Date date_completed_cost_report) {
		this.date_completed_cost_report = date_completed_cost_report;
	}
	
	
	
}
