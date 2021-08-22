package dao;

import java.sql.SQLException;

import model.agency_quick_info;
import utils.Utils;

public class quickInfoDao extends DAO{

	public quickInfoDao() {
		super();
	}

	public void addToQuickInfo(agency_quick_info a) throws SQLException {

		String insert = "insert into liberty.agency_quick_info(client_id, user_id) values(?,?)";
		stm = connector.prepareStatement(insert);

		stm.setInt(1, a.getClient_id());
		stm.setInt(2, a.getUser_id());
		
		stm.executeUpdate();
		stm.close();

	}
	
	public void deleteFromQuickiNFO(agency_quick_info a) throws SQLException {

		String update = "update liberty.agency_quick_info set client_id = ?, user_id = ? where agency_quick_info_id = ?";
		stm = connector.prepareStatement(update);

		stm.setInt(1, a.getClient_id());
		stm.setInt(2, a.getUser_id());
		
		stm.setInt(3, a.getAgency_quick_info_id());
		
		stm.executeUpdate();
		stm.close();

	}
	
	public boolean checkIfExists(int client_id) throws SQLException {
		String count = "SELECT * FROM liberty.agency_quick_info WHERE client_id='"+client_id+"' and user_id = '"+Utils.idP+"' ";
		stm = connector.prepareStatement(count);
		rs = stm.executeQuery(count);
		
		if(rs.next() == true)
			return true;
		
		return false;
	}
	
	public void remove_from_quickInfo(int client_id, int user_id) throws SQLException {
		String insert = "delete from liberty.agency_quick_info where client_id = ? and user_id = ?";
		stm = connector.prepareStatement(insert);

		stm.setInt(1, client_id);
		stm.setInt(2, user_id);
		
		stm.execute();
		stm.close();
	}
	
}
