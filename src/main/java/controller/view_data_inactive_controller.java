package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dao.DAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import model.clients;

public class view_data_inactive_controller extends DAO implements Initializable {

	@FXML private JFXTextField txtAgencyName,txtPhone,txtFax,txtNpi,txtPtan,txtTaxID,txtPrimaryBiller,
	txtPrimaryAccountant,txtSecondaryBiller,txtSecondaryAccountant,
	txtDDELogin,txtDDEPassword,txtSoftwareLogin,txtSoftwarePassword;
	@FXML private JFXTextArea txtAddress,txtComments;
	@FXML public Pane upperPane;
	int clientId = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clientId = inactive_clients_controller.clients.getClient_id();
		getEditedData(inactive_clients_controller.clients);

	}

	public void getEditedData(clients c) {
		txtAgencyName.setText(c.getAgency_name());
		txtAddress.setText(c.getAddress());
		txtPhone.setText(c.getPhone());
		txtFax.setText(c.getFax());
		txtComments.setText(c.getComments());
		txtNpi.setText(c.getNpi() + "");
		txtPtan.setText(c.getPtan() + "");
		txtTaxID.setText(c.getPhone());
		txtPrimaryBiller.setText(c.getPrimary_biller());
		txtPrimaryAccountant.setText(c.getPrimary_accountant());
		txtSecondaryBiller.setText(c.getSecondary_biller());
		txtSecondaryAccountant.setText(c.getSecondary_accountant());
		txtDDELogin.setText(c.getDde_login());
		txtDDEPassword.setText(c.getDde_password());
		txtSoftwareLogin.setText(c.getSoftware_login());
		txtSoftwarePassword.setText(c.getSoftware_password());
	}

}
