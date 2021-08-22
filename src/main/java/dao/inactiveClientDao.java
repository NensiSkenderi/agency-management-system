package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.clients;

public class inactiveClientDao extends DAO{

	public inactiveClientDao() {
		super();
	}

	public List<clients> getAllInactiveClientsInfo() throws SQLException {
		List<clients> data = new ArrayList<clients>();
		String query = "select client_id,agency_name,address,phone,fax,npi,ptan,tax_id,comments,primary_biller,primary_accountant,secondary_biller,"
				+ "secondary_accountant,dde_login,dde_password,software_login,software_password from liberty.clients where deleted = 1 " + 
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

	public void activateClient(int client_id) throws SQLException {
		String insert = "update liberty.clients set deleted = 0 where client_id = ? ";
		stm = connector.prepareStatement(insert);

		stm.setInt(1, client_id);
		
		stm.execute();
		stm.close();
	}
	
	
}
