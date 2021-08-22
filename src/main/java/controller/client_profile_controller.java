package controller;

import java.io.IOException;

import dao.ControlDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.clients;
import utils.Utils;
public class client_profile_controller extends VBox {

	public static String from = "";

	@FXML private Label txtClientName,txtAddress,txtPhone,txtFax,
	txtNpi,txtPrimaryAccountant,txtPtan,txtPrimaryBiller,
	txtTax,txtSecondaryBiller,txtSecondaryAccountant,txtComments;
	
	public static String clientName = "";
	public static int clientId = 0;
	
	public client_profile_controller() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/clientProfile.fxml"));

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
		from = "2";
		accInstController.from = "2";
		clientName = dashboard.last_clicked;
		clients client  = ControlDAO.getControlDao().getClientDao().getClientsInfoFromClientName(clientName);
		clientId = client.getClient_id();
		txtAddress.setText(client.getAddress());
		txtClientName.setText(client.getAgency_name());
		txtPhone.setText(client.getPhone());
		txtFax.setText(client.getFax());
		txtNpi.setText(client.getNpi() + "");
		txtPrimaryAccountant.setText(client.getPrimary_accountant());
		txtPtan.setText(client.getPtan() + "");
		txtPrimaryBiller.setText(client.getPrimary_biller());
		txtTax.setText(client.getTax_id() + "");
		txtSecondaryBiller.setText(client.getSecondary_biller());
		txtSecondaryAccountant.setText(client.getSecondary_accountant());
		txtComments.setText(client.getComments());
		
	}

	@FXML
	private void viewNotes() throws IOException {
		new Utils().openSceneViewNotes("viewNotes" , "View Notes", "/images/notes.png");
	}
	

	@FXML
	private void addContactPerson() throws IOException {
		new Utils().openSceneNoResizable("add_new_contact_person" , "Add Contact Person", "/images/add_red.png");
	}
	
	@FXML
	private void addAccountInstructions() throws IOException {
		new Utils().openSceneNoResizable("add_accounting" , "Add Account Instructions", "/images/add_red.png");
	}
}
