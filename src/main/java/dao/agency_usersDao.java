package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class agency_usersDao extends DAO {

	public agency_usersDao() {
		super();
	}
	
	public List<String> getAgencyByClients(int currentUser) throws SQLException{
		List<String> data = new ArrayList<String>();
		
		String query = "select c.agency_name from liberty.agency_emp ae join liberty.clients c where ae.user_id = '"+currentUser+"' and c.deleted = 0 " + 
				"group by c.agency_name";
		
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query);
		
		while(rs.next()) {
			String p = rs.getString(1);
			data.add(p);
		}
		
		return data;
	}
	
	public List<String> getAgencyByClientsForList(int currentUser) throws SQLException{
		List<String> data = new ArrayList<String>();
		
		String query = "SELECT c.agency_name FROM liberty.agency_list l join liberty.users u on l.user_id = u.user_id " + 
				"join liberty.clients c on l.client_id = c.client_id where l.user_id = '"+currentUser+"' and c.deleted = 0 ";
		
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query);
		
		while(rs.next()) {
			String p = rs.getString(1);
			data.add(p);
		}
		
		return data;
	}
}
