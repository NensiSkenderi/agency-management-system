package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import model.clients;
import utils.Utils;

public class clientDao extends DAO{

	public clientDao() {
		super();
	}

	public List<clients> getAllClientsInfo() throws SQLException{
		List<clients> data = new ArrayList<clients>();
		String query = "select client_id,agency_name,address,phone,fax,npi,ptan,tax_id,comments,primary_biller,primary_accountant,secondary_biller,"
				+ "secondary_accountant,dde_login,dde_password,software_login,software_password from liberty.clients where deleted = 0 " + 
				"				group by agency_name;";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			clients c = new clients(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9));
			c.setPrimary_biller(rs.getString(10));
			c.setPrimary_accountant(rs.getString(11));
			c.setSecondary_biller(rs.getString(12));
			c.setSecondary_accountant(rs.getString(13));
			c.setDde_login(rs.getString(14));
			c.setDde_password(rs.getString(15));
			c.setSoftware_login(rs.getString(16));
			c.setSoftware_password(rs.getString(17));
			data.add(c);
		}
		return data;
	}
	
	public List<String> getAgencies() throws SQLException{
		List<String> data = new ArrayList<>();
		String perd_query = "Select agency_name from liberty.clients where deleted = 0 ORDER BY agency_name;";
		
		stm = connector.prepareStatement(perd_query);
		rs = stm.executeQuery(perd_query);
		
		while(rs.next()) {
			String p = rs.getString(1);
			data.add(p);
		}
		
		return data;
	}

	//client profile
	public clients getClientsInfoFromClientName(String clientName) throws SQLException{
		String query = "select agency_name, address, phone, fax, comments, primary_biller, primary_accountant, secondary_biller, secondary_accountant, npi, ptan, tax_id, client_id from liberty.clients where agency_name = '"+clientName+"'";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 
		clients c = null;
		while(rs.next()) {
			c = new clients(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
			c.setClient_id(rs.getInt(13));
		}
		return c;
	}

	public void addClient(clients client) throws SQLException {

		if(this.checkAgency(client.getAgency_name())) {
			Utils.alerti("Warning!", "Agency exists!", AlertType.WARNING);
			return;
		}
		String insert = "insert into liberty.clients(agency_name, address, phone, fax, comments, primary_biller, primary_accountant, secondary_biller,"
				+ " secondary_accountant, npi, ptan, tax_id,dde_login,dde_password,software_login,software_password) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		stm = connector.prepareStatement(insert);

		stm.setString(1,client.getAgency_name());
		stm.setString(2,client.getAddress());
		stm.setString(3,client.getPhone());
		stm.setString(4,client.getFax());
		stm.setString(5,client.getComments());
		stm.setString(6,client.getPrimary_biller());
		stm.setString(7,client.getPrimary_accountant());
		stm.setString(8,client.getSecondary_biller());
		stm.setString(9,client.getSecondary_accountant());
		stm.setString(10,client.getNpi());
		stm.setString(11,client.getPtan());
		stm.setString(12,client.getTax_id());
		
		stm.setString(13, client.getDde_login());
		stm.setString(14, client.getDde_password());
		stm.setString(15, client.getSoftware_login());
		stm.setString(16, client.getSoftware_password());
		
		stm.executeUpdate();
		stm.close();
	}
	
	
	public void insertAgencyClient() throws SQLException {
		String insert = "insert into liberty.agency_emp(agency_id, user_id) values(?,?)";
		//should use a new prepared statement here when 2 inserts together
		PreparedStatement stm1 = connector.prepareStatement(insert);
		
		Utils.lastAgencyId = getLastIdFromAgences();
		stm1.setInt(1, Utils.lastAgencyId);
		stm1.setInt(2, Utils.idP);
		
		stm1.executeUpdate();
		stm1.close();
	}
	
	public boolean checkAgency(String empFullName) throws SQLException {
		String query = "select * from liberty.users where full_name = '"+empFullName+"'";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query);
		if(rs.next() == true)
			return true;
		else 
			return false;
	}
	
	public int getLastIdFromAgences() throws SQLException {
		int count = 0;
		String query = "SELECT * FROM liberty.clients ORDER BY client_id DESC LIMIT 1;";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 
		while(rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}
	
	public void deleteClient(int clientId) throws Exception {
		String delete_user = "update liberty.clients set deleted = 1 where client_id = ?";
		stm = connector.prepareStatement(delete_user);

        stm.setInt(1, clientId);
        stm.execute();
	}
	
	public void updateClient(clients client) {
		try {
			String update = "update liberty.clients set agency_name = ?, address = ?, phone = ?, fax = ?, comments = ?, primary_biller = ?, primary_accountant = ?, "
					+ "secondary_biller = ?, secondary_accountant = ?, npi = ?, ptan = ?, tax_id = ? , dde_login = ? , dde_password = ? , "
					+ "software_login = ? , software_password = ? where client_id = ?";
			stm = connector.prepareStatement(update);
			
			stm.setString(1,client.getAgency_name());
			stm.setString(2,client.getAddress());
			stm.setString(3,client.getPhone());
			stm.setString(4,client.getFax());
			stm.setString(5,client.getComments());
			stm.setString(6,client.getPrimary_biller());
			stm.setString(7,client.getPrimary_accountant());
			stm.setString(8,client.getSecondary_biller());
			stm.setString(9,client.getSecondary_accountant());
			stm.setString(10,client.getNpi());
			stm.setString(11,client.getPtan());
			stm.setString(12,client.getTax_id());
			stm.setInt(17, client.getClient_id());
			
			stm.setString(13, client.getDde_login());
			stm.setString(14, client.getDde_password());
			stm.setString(15, client.getSoftware_login());
			stm.setString(16, client.getSoftware_password());
			
			stm.executeUpdate();
			stm.close();
			
		} catch (SQLException e) {
			Utils.alerti("Warning!", "Agency exists!", AlertType.WARNING);
			return;
		}
	}

}
