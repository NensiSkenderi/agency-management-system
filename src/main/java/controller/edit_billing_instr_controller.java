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
import model.insurances;
import utils.Combo;
import utils.Utils;

public class edit_billing_instr_controller implements Initializable {

	@FXML private JFXTextField txtInsuranceName, txtInstructions;
	@FXML private JFXButton btnCancel;
	@FXML private Label lblError;
	int billingId = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			billingId = billing_instructions_controller.insuranc.getInsurance_id();
			getData(billing_instructions_controller.insuranc);
	}

	private void getData(insurances i) {
		txtInsuranceName.setText(i.getInsurance_name());
		txtInstructions.setText(i.getInstructions());
		
	}
	
	@FXML 
	public void save() throws Exception {
		insurances i = new insurances();
		i.setInsurance_id(billingId);
		i.setInstructions(txtInstructions.getText());
		i.setInsurance_name(txtInsuranceName.getText());
		
		ControlDAO.getControlDao().getNewPayerDao().updateInsurance(i);
		
		Utils.close_stage(btnCancel);
	}

	@FXML
	public void cancel() {
		Utils.close_stage(btnCancel);
	}

}
