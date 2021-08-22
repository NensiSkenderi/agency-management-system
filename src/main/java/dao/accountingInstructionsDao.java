package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.accounting_instructions;

public class accountingInstructionsDao extends DAO{

	public accountingInstructionsDao() {
		super();
	}


	public void addAccountingInstruction(accounting_instructions a) throws SQLException {
		String insert = "insert into liberty.accounting_instructions(agency_id,sent_tax_return,sent_cost_report,book_keeping,payroll,"
				+ "preparer_tax_return,preparer_cost_report,additional_info_tax_return,additional_info_cost_report,due_date_tax_return,"
				+ "date_completed_tax_return,due_date_cost_report,date_completed_cost_report,intermediary) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		stm = connector.prepareStatement(insert);

		stm.setInt(1, a.getAgency_id());
		stm.setInt(2, a.getSent_tax_return());
		stm.setInt(3, a.getSent_cost_report());
		stm.setInt(4, a.getBook_keeping());
		stm.setInt(5, a.getPayroll());
		stm.setString(6, a.getPreparer_tax_return());
		stm.setString(7, a.getPreparer_cost_report());
		stm.setString(8, a.getAdditional_info_tax_return());
		stm.setString(9, a.getAdditional_info_cost_report());
		stm.setDate(10, a.getDue_date_tax_return());
		stm.setDate(11, a.getDate_completed_tax_return());
		stm.setDate(12, a.getDue_date_cost_report());
		stm.setDate(13, a.getDate_completed_cost_report());
		stm.setString(14, a.getIntermediary());
		
		stm.executeUpdate();
		stm.close();
	}
	
	public void updateAccountingInstruction(accounting_instructions a) throws SQLException {
		String update = "update liberty.accounting_instructions set sent_tax_return = ?, sent_cost_report = ?,book_keeping = ?, payroll = ?,"
				+ "preparer_tax_return = ?, preparer_cost_report = ?, additional_info_tax_return = ? ,additional_info_cost_report = ?,due_date_tax_return = ?,"
				+ "date_completed_tax_return = ?,due_date_cost_report = ?,date_completed_cost_report = ? , intermediary = ? where accounting_instructions_id = ?";
		stm = connector.prepareStatement(update);
		
		stm.setInt(1, a.getSent_tax_return());
		stm.setInt(2, a.getSent_cost_report());
		stm.setInt(3, a.getBook_keeping());
		stm.setInt(4, a.getPayroll());
		stm.setString(5, a.getPreparer_tax_return());
		stm.setString(6, a.getPreparer_cost_report());
		stm.setString(7, a.getAdditional_info_tax_return());
		stm.setString(8, a.getAdditional_info_cost_report());
		stm.setDate(9, a.getDue_date_tax_return());
		stm.setDate(10, a.getDate_completed_tax_return());
		stm.setDate(11, a.getDue_date_cost_report());
		stm.setDate(12, a.getDate_completed_cost_report());
		stm.setString(13, a.getIntermediary());
		
		stm.setInt(14, a.getAccounting_instructions_id());
		
		stm.executeUpdate();
		stm.close();
	}
	
	public List<accounting_instructions> getAllAccountingInstructions() throws SQLException{
		List<accounting_instructions> data = new ArrayList<accounting_instructions>();
		String query = "Select acc.accounting_instructions_id,acc.agency_id,acc.sent_tax_return,acc.sent_cost_report,acc.book_keeping,acc.payroll, " + 
				"acc.preparer_tax_return,acc.preparer_cost_report,acc.additional_info_tax_return,acc.additional_info_cost_report, " + 
				"acc.due_date_tax_return, acc.date_completed_tax_return,acc.due_date_cost_report,acc.date_completed_cost_report, c.agency_name, acc.intermediary " + 
				"from liberty.accounting_instructions acc join liberty.clients c on acc.agency_id = c.client_id";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			accounting_instructions a = new accounting_instructions();
			a.setAccounting_instructions_id(rs.getInt(1));
			a.setAgency_id(rs.getInt(2));
			a.setSent_tax_return(rs.getInt(3));
			a.setSent_cost_report(rs.getInt(4));
			a.setBook_keeping(rs.getInt(5));
			a.setPayroll(rs.getInt(6));
			
			a.setPreparer_tax_return(rs.getString(7));
			a.setPreparer_cost_report(rs.getString(8));
			a.setAdditional_info_tax_return(rs.getString(9));
			a.setAdditional_info_cost_report(rs.getString(10));
			
			a.setDue_date_tax_return(rs.getDate(11));
			a.setDate_completed_tax_return(rs.getDate(12));
			a.setDue_date_cost_report(rs.getDate(13));
			a.setDate_completed_cost_report(rs.getDate(14));
			a.setAgency_name(rs.getString(15));
			a.setIntermediary(rs.getString(16));
			
			data.add(a);
		}
		return data;
	}
}
