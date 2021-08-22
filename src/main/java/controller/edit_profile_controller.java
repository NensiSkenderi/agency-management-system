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
import model.users;
import utils.Utils;

public class edit_profile_controller implements Initializable {

	@FXML private JFXTextField txtFullName,txtPassword;
	@FXML private JFXButton btnCancel;
	@FXML private Label lblError;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtFullName.setText(Utils.full_name.getValue());
		txtPassword.setText(Utils.passwordDecrypted);
	}
	
	@FXML 
	public void save() throws Exception {
		if(Utils.check_empty_text(txtFullName.getText(), txtPassword.getText())) {
			lblError.setText("*Please, Fill out all the fields!");
			return;
		}
		
		users u = new users();
		u.setUser_id(Utils.idP);
		u.setPassword(txtPassword.getText());
		u.setFull_name(txtFullName.getText());
		Utils.passwordDecrypted = u.getPassword();
		ControlDAO.getControlDao().getUserDao().updateUser(u);
		Utils.close_stage(btnCancel);
	}
	
	@FXML
	public void cancel() {
		Utils.close_stage(btnCancel);
	}
	
}
