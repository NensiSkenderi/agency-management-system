package dao;

import java.sql.SQLException;

import model.contact_person;

public class contactPersonDao extends DAO{

	public contactPersonDao() {
		super();
	}

	public void addContactPerson(contact_person contactPerson) throws SQLException {

		String insert = "insert into liberty.contact_persons(contact_name,phone_number,client_id,primary_contact) values(?,?,?,?)";
		stm = connector.prepareStatement(insert);

		stm.setString(1,contactPerson.getContact_name());
		stm.setString(2,contactPerson.getPhone_number());
		stm.setInt(3,contactPerson.getClient_id());
		stm.setInt(4,contactPerson.isPrimary());
		
		stm.executeUpdate();
		stm.close();
	}
	
	
	public int getIdFromAgencyName(String agencyName) throws SQLException {
		int count = 0;
		String query = "SELECT client_id FROM liberty.clients where agency_name = '"+agencyName+"';";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 
		while(rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}

}
