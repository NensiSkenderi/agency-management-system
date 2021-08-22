package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.client_communication_notes;
import utils.Combo;
import utils.Utils;

public class add_client_comm_notes_controller implements Initializable {

	@FXML private JFXTextField txtClientCommNotes;
	@FXML private JFXButton btnCancel;
	@FXML private Label lblError;
	@FXML private JFXComboBox<String> cmbAgency;
	int clientCommId = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			fill_combobox();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(clients_comm_notes_controller.edit == true) {
			clientCommId = clients_comm_notes_controller.clientNotes.getClient_communication_id();
			getData(clients_comm_notes_controller.clientNotes);
		}
		else 
			clientCommId = 0;
		
	}

	private void getData(client_communication_notes c) {
		txtClientCommNotes.setText(c.getClient_comm_notes_desc());
		cmbAgency.setValue(c.getAgency_name());
	}

	public void fill_combobox() throws SQLException {
		Combo.populate_combo(cmbAgency, ControlDAO.getControlDao().getClientDao().getAgencies());
	}
	
	@FXML 
	public void save() throws Exception {
		if(Utils.check_empty_combobox(cmbAgency)) {
			lblError.setText("*Please, Fill out all the fields!");
			return;
		}

		int client_id = ControlDAO.getControlDao().getContactPersonDao().getIdFromAgencyName(cmbAgency.getValue());
		client_communication_notes c = new client_communication_notes(clientCommId, txtClientCommNotes.getText(), client_id);

		if(clientCommId == 0)
			ControlDAO.getControlDao().getClientCommNotesDao().addClientCommNotes(c);
		else
			ControlDAO.getControlDao().getClientCommNotesDao().updateClientCommNotes(c);

		Utils.close_stage(btnCancel);
	}

	@FXML
	public void cancel() {
		Utils.close_stage(btnCancel);
	}

}
