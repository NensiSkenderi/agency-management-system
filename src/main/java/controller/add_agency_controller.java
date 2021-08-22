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
import javafx.scene.control.Label;
import model.clients;
import model.contact_person;
import utils.Utils;

public class add_agency_controller implements Initializable {

	@FXML private JFXTextField txtAgencyName,txtPhone,txtFax,txtNpi,txtPtan,txtTaxID,txtPrimaryBiller,
	txtPrimaryAccountant,txtSecondaryBiller,txtSecondaryAccountant,txtContactPersonName,txtContactPersonPhone;
	@FXML private JFXTextArea txtAddress,txtComments;
	@FXML private JFXButton btnCancel;
	@FXML private Label lblError;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Utils.make_textfield_decimal(txtNpi);
		Utils.make_textfield_decimal(txtPtan);
		Utils.make_textfield_decimal(txtTaxID);
	}
	
	
	@FXML
	private void add() throws SQLException {
		if(Utils.check_empty_text(txtAgencyName.getText())) {
			lblError.setText("*Please, Fill out Client name!");
			return;
		}
		
		if(Utils.check_empty_text(txtContactPersonName.getText())) {
			lblError.setText("*Please, Fill out Contact Person name!");
			return;
		}
		
		if(txtNpi.getText().isEmpty())
			txtNpi.setText("0.0");
		if(txtPtan.getText().isEmpty())
			txtPtan.setText("0.0");
		if(txtTaxID.getText().isEmpty())
			txtTaxID.setText("0.0");
		
		clients c = new clients(txtAgencyName.getText(), txtAddress.getText(), txtPhone.getText(), txtFax.getText(), 
				txtComments.getText(), txtPrimaryBiller.getText(), txtPrimaryAccountant.getText(), txtSecondaryBiller.getText(),
				txtSecondaryAccountant.getText(), txtNpi.getText(), txtPtan.getText(), txtTaxID.getText());
		
		
		ControlDAO.getControlDao().getClientDao().addClient(c);
		ControlDAO.getControlDao().getClientDao().insertAgencyClient();
		
		contact_person contactPerson = new contact_person(txtContactPersonName.getText(), txtContactPersonPhone.getText(), Utils.lastAgencyId, 1);
		ControlDAO.getControlDao().getContactPersonDao().addContactPerson(contactPerson);
		
		Utils.close_stage(btnCancel);
	}
	
	@FXML
	private void cancel() {
		Utils.close_stage(btnCancel);
	}
	

}
