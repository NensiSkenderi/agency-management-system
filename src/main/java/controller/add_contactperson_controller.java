package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.contact_person;
import model.insurances;
import utils.Utils;

public class add_contactperson_controller implements Initializable {

	@FXML private JFXTextField txtContactPersonName,txtContactPersonNumber;
	@FXML private JFXButton btnCancel;
	@FXML private Label lblError;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Utils.make_jfxtextfield_integer(txtContactPersonNumber);
	}
	
	@FXML 
	public void save() throws Exception {
		if(Utils.check_empty_text(txtContactPersonName.getText())) {
			lblError.setText("*Please, Fill out all the fields!");
			return;
		}
		
		int client_id = ControlDAO.getControlDao().getContactPersonDao().getIdFromAgencyName(dashboard.last_clicked);
		contact_person c = new contact_person(txtContactPersonName.getText(), txtContactPersonNumber.getText(), client_id, 0);
		ControlDAO.getControlDao().getContactPersonDao().addContactPerson(c);
		Utils.close_stage(btnCancel);
	}
	
	@FXML
	public void cancel() {
		Utils.close_stage(btnCancel);
	}
	
}
