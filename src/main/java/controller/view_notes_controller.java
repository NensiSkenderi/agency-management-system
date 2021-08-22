package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import model.insurances;
import utils.Utils;

public class view_notes_controller implements Initializable {

	@FXML private JFXTextArea txtAreaInstructions;
	@FXML private JFXTextField txtInsuranceName,txtHidden;
	@FXML private JFXButton btnCancel;
	@FXML private AnchorPane mainContainer;
	int clientId = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clientId = client_profile_controller.clientId;
		mainContainer.requestFocus();
		insurances i = null;
		try {
			i = ControlDAO.getControlDao().getNewPayerDao().getNoteFromClient(clientId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getData(i);
		System.out.println(txtHidden.getText() + " ins id");

	}
	
	private void getData(insurances i) {
		txtInsuranceName.setText(i.getInsurance_name());
		txtAreaInstructions.setText(i.getInstructions());
		txtHidden.setText(i.getInsurance_id()+"");
	}
	
	@FXML 
	public void save() throws Exception {
		insurances i = new insurances();
		i.setAgency_id(clientId);
		i.setInstructions(txtAreaInstructions.getText());
		i.setInsurance_name(txtInsuranceName.getText());
		i.setInsurance_id(Integer.parseInt(txtHidden.getText()));
		
		
		if(ControlDAO.getControlDao().getNewPayerDao().checkIfExists(clientId))
			ControlDAO.getControlDao().getNewPayerDao().updateInsuranceFromNotes(i);
		else 
			ControlDAO.getControlDao().getNewPayerDao().addInsurance(i);
		
		Utils.close_stage(btnCancel);
	}

	@FXML
	public void cancel() {
		Utils.close_stage(btnCancel);
	}
	
}
