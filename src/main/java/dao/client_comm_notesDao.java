package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import model.client_communication_notes;
import model.clients;
import model.users;
import utils.Utils;

public class client_comm_notesDao extends DAO{

	public client_comm_notesDao() {
		super();
	}

	public List<client_communication_notes> getAllClientNotes() throws SQLException{
		List<client_communication_notes> data = new ArrayList<client_communication_notes>();
		String query = "select cn.client_comm_notes_id, cn.client_comm_notes_desc, c.agency_name from liberty.client_comm_notes cn join "
				+ "liberty.clients c on c.client_id=cn.client_id  where c.deleted = 0 group by cn.client_comm_notes_id";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			client_communication_notes c = new client_communication_notes();
			c.setClient_communication_id(rs.getInt(1));
			c.setClient_comm_notes_desc(rs.getString(2));
			c.setAgency_name(rs.getString(3));
			data.add(c);
		}
		return data;
	}
	

	public void addClientCommNotes(client_communication_notes notes) throws SQLException {

		String insert = "insert into liberty.client_comm_notes(client_comm_notes_desc, client_id) values(?,?)";
		stm = connector.prepareStatement(insert);

		stm.setString(1,notes.getClient_comm_notes_desc());
		stm.setInt(2,notes.getClient_id());
		
		stm.executeUpdate();
		stm.close();
	}
	
	public void updateClientCommNotes(client_communication_notes notes) throws SQLException {

		String insert = "update liberty.client_comm_notes set client_comm_notes_desc = ?, client_id = ? where client_comm_notes_id = ?";
		stm = connector.prepareStatement(insert);

		stm.setString(1,notes.getClient_comm_notes_desc());
		stm.setInt(2,notes.getClient_id());
		stm.setInt(3, notes.getClient_communication_id());
		stm.executeUpdate();
		stm.close();
	}
	
	public void deleteClientCommNotes(int clientCommId) throws Exception {
		String delete = "delete from liberty.client_comm_notes where client_id = ?";
		stm = connector.prepareStatement(delete);

        stm.setInt(1, clientCommId);
        stm.execute();
	}

}
