package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.accounting_instructions;
import model.clients;
import model.report_agency;
import model.report_tax_cost;

public class reports_dao extends DAO{

	public reports_dao() {
		super();
	}

	//instructions is biller_notes
	public List<report_agency> getFirstReport() throws SQLException{
		List<report_agency> data = new ArrayList<report_agency>();
		String query = "SELECT c.agency_name, c.primary_biller,c.primary_accountant, i.instructions FROM liberty.clients c join "
				+ "liberty.insurances i on c.client_id = i.agency_id;";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			report_agency r = new report_agency();
			r.setAgency_name(rs.getString(1));
			r.setPrimary_biller(rs.getString(2));
			r.setPrimary_accountant(rs.getString(3));
			r.setBiller_notes(rs.getString(4));
			data.add(r);
		}
		return data;
	}
	
	
	public List<report_agency> getSecondReport() throws SQLException{
		List<report_agency> data = new ArrayList<report_agency>();
		String query = "SELECT c.agency_name, i.instructions, ai.book_keeping  FROM liberty.clients c join " + 
				"liberty.insurances i on c.client_id = i.agency_id join " + 
				"liberty.accounting_instructions ai on c.client_id = ai.agency_id; ";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			report_agency r = new report_agency();
			r.setAgency_name(rs.getString(1));
			r.setBilling_instructions(rs.getString(2));
			r.setAccounting_instructions(rs.getString(3));
			data.add(r);
		}
		return data;
	}
	
	public List<accounting_instructions> getThirdReport() throws SQLException{
		List<accounting_instructions> data = new ArrayList<accounting_instructions>();
		String query = "SELECT a.agency_name,ai.preparer_cost_report,ai.sent_cost_report,ai.additional_info_cost_report, " + 
				"ai.preparer_tax_return,ai.sent_tax_return,ai.additional_info_tax_return " + 
				"FROM liberty.accounting_instructions ai join liberty.clients a " + 
				"on a.client_id = ai.agency_id"; 
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			accounting_instructions a = new accounting_instructions();
			a.setAgency_name(rs.getString(1));
			a.setPreparer_cost_report(rs.getString(2));
			a.setSent_cost_report(rs.getInt(3));
			a.setAdditional_info_cost_report(rs.getString(4));
			
			a.setPreparer_tax_return(rs.getString(5));
			a.setSent_tax_return(rs.getInt(6));
			a.setAdditional_info_tax_return(rs.getString(7));
			
			data.add(a);
		}
		return data;
	}
	

}
