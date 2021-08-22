package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import dao.DAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.clients;
import utils.Utils;

public class edit_client_controller extends DAO implements Initializable {

	@FXML private JFXTextField txtAgencyName,txtPhone,txtFax,txtNpi,txtPtan,txtTaxID,txtPrimaryBiller,
	txtPrimaryAccountant,txtSecondaryBiller,txtSecondaryAccountant,
	txtDDELogin,txtDDEPassword,txtSoftwareLogin,txtSoftwarePassword;
	@FXML private JFXTextArea txtAddress,txtComments;
	@FXML private JFXButton btnCancel;
	@FXML private Label lblError;
	@FXML public Pane upperPane;
	int clientId = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clientId = clients_controller.clients.getClient_id();
		getEditedData(clients_controller.clients);

	}

	public void getEditedData(clients c) {
		txtAgencyName.setText(c.getAgency_name());
		txtAddress.setText(c.getAddress());
		txtPhone.setText(c.getPhone());
		txtFax.setText(c.getFax());
		txtComments.setText(c.getComments());
		txtNpi.setText(c.getNpi() + "");
		txtPtan.setText(c.getPtan() + "");
		txtTaxID.setText(c.getTax_id());
		txtPrimaryBiller.setText(c.getPrimary_biller());
		txtPrimaryAccountant.setText(c.getPrimary_accountant());
		txtSecondaryBiller.setText(c.getSecondary_biller());
		txtSecondaryAccountant.setText(c.getSecondary_accountant());
		txtDDELogin.setText(c.getDde_login());
		txtDDEPassword.setText(c.getDde_password());
		txtSoftwareLogin.setText(c.getSoftware_login());
		txtSoftwarePassword.setText(c.getSoftware_password());
	}

	@FXML 
	public void add() throws Exception {
		if(Utils.check_empty_text(txtAgencyName.getText())) {
			lblError.setText("*Please, Fill out all the fields!");
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
		c.setClient_id(clientId);
		c.setDde_login(txtDDELogin.getText());
		c.setDde_password(txtDDEPassword.getText());
		c.setSoftware_login(txtSoftwareLogin.getText());
		c.setSoftware_password(txtSoftwarePassword.getText());
		ControlDAO.getControlDao().getClientDao().updateClient(c);


		Utils.close_stage(btnCancel);
	}

	@FXML
	public void cancel() {
		Utils.close_stage(btnCancel);
	}



}
