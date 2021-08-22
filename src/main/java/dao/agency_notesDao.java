package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import model.agency_notes;
import utils.Utils;

public class agency_notesDao extends DAO {

	public agency_notesDao() {
		super();
	}
	
	public agency_notes getAgencyNote(String clientName) throws SQLException{
		String query = "select n.agency_notes_id, n.notes, c.client_id from liberty.clients c join liberty.agency_notes n on c.client_id = n.agency_id where c.agency_name = '"+clientName+"'";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 
		agency_notes notes = null;
		
		while(rs.next()) {
			 notes = new agency_notes(rs.getInt(1), rs.getString(2), Utils.idP, rs.getInt(3));
		}
		return notes;
	}

	public void insert(agency_notes a) throws SQLException {
		String insert = "insert into liberty.agency_notes(notes,user_id,agency_id) values(?,?,?)";
		stm = connector.prepareStatement(insert);
		
		stm.setString(1,a.getNotes());
		stm.setInt(2,a.getUser_id());
		stm.setInt(3, a.getAgency_id());
		
		stm.executeUpdate();
		stm.close();
		
	}

	public void update(agency_notes a) throws SQLException {
		String update = "update liberty.agency_notes set notes = ?, inserted_date = ?  where agency_notes_id = ?";
		stm = connector.prepareStatement(update);
		
		stm.setString(1,a.getNotes());
		stm.setDate(2, Date.valueOf(LocalDate.now()));

		stm.setInt(3,a.getAgency_notes_id());
		
		stm.executeUpdate();
		stm.close();
		
	}
}
