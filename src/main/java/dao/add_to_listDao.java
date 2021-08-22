package dao;

import java.sql.SQLException;

import model.agency_list;
import utils.Utils;

public class add_to_listDao extends DAO{

	public add_to_listDao() {
		super();
	}

	public void addToList(agency_list a) throws SQLException {

		String insert = "insert into liberty.agency_list(client_id, user_id) values(?,?)";
		stm = connector.prepareStatement(insert);

		stm.setInt(1, a.getClient_id());
		stm.setInt(2, a.getUser_id());
		
		stm.executeUpdate();
		stm.close();

	}
	
	public void deleteFromList(agency_list a) throws SQLException {

		String update = "update liberty.agency_list set client_id = ?, user_id = ? where agency_list_id = ?";
		stm = connector.prepareStatement(update);

		stm.setInt(1, a.getClient_id());
		stm.setInt(2, a.getUser_id());
		
		stm.setInt(3, a.getAgency_list_id());
		
		stm.executeUpdate();
		stm.close();

	}
	
	public boolean checkIfExists(int client_id) throws SQLException {
		String count = "SELECT * FROM liberty.agency_list WHERE client_id='"+client_id+"' and user_id = '"+Utils.idP+"' ";
		stm = connector.prepareStatement(count);
		rs = stm.executeQuery(count);
		
		if(rs.next() == true)
			return true;
		
		return false;
	}
	
	public void remove_from_list(int client_id, int user_id) throws SQLException {
		String insert = "delete from liberty.agency_list where client_id = ? and user_id = ?";
		stm = connector.prepareStatement(insert);

		stm.setInt(1, client_id);
		stm.setInt(2, user_id);
		
		stm.execute();
		stm.close();
	}
	
}
