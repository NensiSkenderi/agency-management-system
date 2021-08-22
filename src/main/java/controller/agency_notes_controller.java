package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import dao.ControlDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.agency_notes;
import model.clients;
import utils.Utils;
public class agency_notes_controller extends VBox {

	@FXML private JFXTextArea txtAreaNotes;
	@FXML private Label clientDate, clientName;
	@FXML private JFXButton btnEdit, btnAddNote;
	
	private final static String OUTPUT_NAME = "Notes for client : ";
	private final static String OUTPUT_DATE = "Current Date : ";
	int agency_notes_id, client_id;
	
	public agency_notes_controller() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/agency_notes.fxml"));

		fxml.setRoot(this);
		fxml.setController(this);
		try {
			fxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() throws Exception {
		clientName.setText(OUTPUT_NAME + dashboard.last_clicked_updates);
		clientDate.setText(OUTPUT_DATE + LocalDate.now());
		clients c = ControlDAO.getControlDao().getClientDao().getClientsInfoFromClientName(dashboard.last_clicked_updates);
		client_id = c.getClient_id();
		agency_notes agency_notes  = ControlDAO.getControlDao().getAgencyNotesDao().getAgencyNote(dashboard.last_clicked_updates);
		
		if (agency_notes == null) 
			agency_notes_id = 0;
		
		else {
			agency_notes_id = agency_notes.getAgency_notes_id();
			txtAreaNotes.setText(agency_notes.getNotes());
		}
	}
	
	@FXML
	private void save() throws IOException, SQLException {
		agency_notes a = new agency_notes(agency_notes_id, txtAreaNotes.getText(), Utils.idP, client_id);
		
		if(agency_notes_id == 0) 
			ControlDAO.getControlDao().getAgencyNotesDao().insert(a);
		
		else
			ControlDAO.getControlDao().getAgencyNotesDao().update(a);
			
	}

}
