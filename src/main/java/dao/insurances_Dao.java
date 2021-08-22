package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.insurances;
import utils.Utils;

public class insurances_Dao extends DAO{

	public insurances_Dao() {
		super();
	}

	public void addInsurance(insurances i) throws SQLException {

		String insert = "insert into liberty.insurances(insurance_name, instructions, user_id, agency_id) values(?,?,?,?)";
		PreparedStatement stm1 = connector.prepareStatement(insert);

		stm1.setString(1, i.getInsurance_name());
		stm1.setString(2, i.getInstructions());
		stm1.setInt(3, Utils.idP);
		stm1.setInt(4, i.getAgency_id());
		
		stm1.executeUpdate();
		stm1.close();

	}
	
	public void updateInsurance(insurances i) throws SQLException {

		String update = "update liberty.insurances set insurance_name = ?, instructions = ? where insurance_id = ?";
		stm = connector.prepareStatement(update);

		stm.setString(1, i.getInsurance_name());
		stm.setString(2, i.getInstructions());
		
		stm.setInt(3, i.getInsurance_id());
		
		stm.executeUpdate();
		stm.close();

	}
	
	public void updateInsuranceFromNotes(insurances i) throws SQLException {

		String update = "update liberty.insurances set insurance_name = ?, instructions = ? where agency_id = ? and insurance_id = ? ";
		stm = connector.prepareStatement(update);

		stm.setString(1, i.getInsurance_name());
		stm.setString(2, i.getInstructions());
		
		stm.setInt(3, i.getAgency_id());
		stm.setInt(4, i.getInsurance_id());
		
		stm.executeUpdate();
		stm.close();

	}
	
	public List<insurances> getAllInsurances() throws SQLException{
		List<insurances> data = new ArrayList<insurances>();
		String query = "SELECT i.insurance_id, i.insurance_name, i.instructions, c.agency_name from liberty.insurances i join liberty.clients c " + 
				"on i.agency_id = c.client_id group by c.agency_name ;";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			insurances i = new insurances();
			i.setInsurance_id(rs.getInt(1));
			i.setAgency_name(rs.getString(4));
			i.setInstructions(rs.getString(3));
			i.setInsurance_name(rs.getString(2));
			data.add(i);
		}
		return data;
	}
	
	public List<insurances> getAllInsurances(String agency_name) throws SQLException{
		List<insurances> data = new ArrayList<insurances>();
		String query = "SELECT i.insurance_id, i.insurance_name, i.instructions, c.agency_name from liberty.insurances i join liberty.clients c " + 
				"on i.agency_id = c.client_id where c.agency_name = '"+agency_name+"' group by c.agency_name ;";
		stm = connector.prepareStatement(query);
		ResultSet rs = stm.executeQuery(query); 

		while(rs.next()) {
			insurances i = new insurances();
			i.setInsurance_id(rs.getInt(1));
			i.setAgency_name(rs.getString(4));
			i.setInstructions(rs.getString(3));
			i.setInsurance_name(rs.getString(2));
			data.add(i);
		}
		return data;
	}
	
	public insurances getNoteFromClient(int client_id) throws SQLException {
		String query = "SELECT i.insurance_id, i.insurance_name, i.instructions, c.agency_name from liberty.insurances i join liberty.clients c " + 
				"on i.agency_id = c.client_id where i.agency_id = '"+client_id+"' and i.user_id = '"+Utils.idP+"' ";
		
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query);
		insurances i = new insurances();
		while(rs.next()) {
			i.setInsurance_id(rs.getInt(1));
			i.setAgency_name(rs.getString(4));
			i.setInstructions(rs.getString(3));
			i.setInsurance_name(rs.getString(2));
			i.setAgency_id(client_id);
		}
		
		return i;
	}

	public boolean checkIfExists(int client_id) throws SQLException {
		String count = "SELECT * FROM liberty.insurances WHERE agency_id='"+client_id+"' and user_id = '"+Utils.idP+"' ";
		stm = connector.prepareStatement(count);
		rs = stm.executeQuery(count);
		
		if(rs.next() == true)
			return true;
		
		return false;
	}
	
	public List<String> getQuickInfo() throws SQLException{
		List<String> data = new ArrayList<>();
		String query = "select c.client_id,agency_name from liberty.clients c join liberty.agency_quick_info a on c.client_id = a.client_id " + 
				" group by agency_name";
		
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query);
		
		while(rs.next()) {
			String agency_name = rs.getString(2);
			data.add(agency_name);
		}
		
		return data;
	}
	
}
