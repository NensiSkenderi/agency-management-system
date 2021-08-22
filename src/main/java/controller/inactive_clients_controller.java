package controller;

import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.clients;
import utils.Utils;
public class inactive_clients_controller extends VBox {

	@FXML private Label lblError;
	@FXML private TableView<clients> tblClients;
	@FXML private TableColumn<clients, String> tblcolClientId,tblcolAgencyName,tblcolAddress,tblcolPhone,tblcolFax,tblcolNpi,tblcolPtan,tblcolTax,tblcolComments,
	tblcolPrimaryBiller,tbolcolPrimaryAccountant,tblcolSecondaryBiller,tblcolSecondaryAccountant;
	@FXML private TableColumn<clients, String> tblcolDDELogin,tblcolDDEPassword,tblcolSoftwareLogin,tblcolSoftwarePassword;
	@FXML private VBox root;
	@FXML private JFXTextField txtSearch;

	public ObservableList<clients> data = FXCollections.observableArrayList();
	public static clients clients = new clients();

	public inactive_clients_controller() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/inactive_clients.fxml"));

		fxml.setRoot(this);
		fxml.setController(this);
		try {
			fxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void search_tableview() {
		txtSearch.textProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable o) {

	                if(txtSearch.textProperty().get().isEmpty()) {
	                	tblClients.setItems(data);
	                	return;
	                }

	                ObservableList<clients> tableItems = FXCollections.observableArrayList();
	                ObservableList<TableColumn<clients, ?>> cols = tblClients.getColumns();
	               
	                for(int i=0; i<data.size(); i++) {
	                	//heqim id qe nk shfaqet dhe e fillojm me j=1
	                	for(int j=1; j<6; j++) {
	                		TableColumn<clients, ?> col = cols.get(j);
	                		String cellValue = col.getCellData(data.get(i)).toString();
	                		cellValue = cellValue.toLowerCase();
	                			if(cellValue.contains(txtSearch.textProperty().get().toLowerCase())) {
	                					tableItems.add(data.get(i));
	                					break;
	                				}                        
	                			}
	                		}

	                tblClients.setItems(tableItems);
				}
	     });
	}

	@FXML
	public void initialize() throws Exception {
		search_tableview();
		populate_table();
	}

	public void populate_data() throws SQLException {
		tblClients.getItems().clear();
		data.addAll(ControlDAO.getControlDao().getInactiveClientDao().getAllInactiveClientsInfo());
		tblClients.setItems(data);
	}

	public void populate_table() throws Exception {
		tblClients.getItems().clear();
		data.addAll(ControlDAO.getControlDao().getInactiveClientDao().getAllInactiveClientsInfo());
		tblcolClientId.setCellValueFactory(new PropertyValueFactory<>("client_id"));
		tblcolAgencyName.setCellValueFactory(new PropertyValueFactory<>("agency_name"));
		tblcolAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		tblcolAddress.setStyle("-fx-alignment:center;");
		tblcolPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tblcolFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
		tblcolNpi.setCellValueFactory(new PropertyValueFactory<>("npi"));
		tblcolPtan.setCellValueFactory(new PropertyValueFactory<>("ptan"));
		tblcolTax.setCellValueFactory(new PropertyValueFactory<>("tax_id"));
		tblcolComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
		tblcolPrimaryBiller.setCellValueFactory(new PropertyValueFactory<>("primary_biller"));
		tbolcolPrimaryAccountant.setCellValueFactory(new PropertyValueFactory<>("primary_accountant"));
		tblcolSecondaryBiller.setCellValueFactory(new PropertyValueFactory<>("secondary_biller"));
		tblcolSecondaryAccountant.setCellValueFactory(new PropertyValueFactory<>("secondary_accountant"));
		
		tblcolDDELogin.setCellValueFactory(new PropertyValueFactory<>("dde_login"));
		tblcolDDEPassword.setCellValueFactory(new PropertyValueFactory<>("dde_password"));
		tblcolSoftwareLogin.setCellValueFactory(new PropertyValueFactory<>("software_login"));
		tblcolSoftwarePassword.setCellValueFactory(new PropertyValueFactory<>("software_password"));
		
		tblClients.setItems(data);
	}
	
	@FXML
	private void activateClient() throws IOException, SQLException {
		clients c = tblClients.getSelectionModel().getSelectedItem();
		if(c == null) {
			Utils.alerti("Warning!", "Choose a record from table.", AlertType.WARNING);
			return;
		}
		ControlDAO.getControlDao().getInactiveClientDao().activateClient(c.getClient_id());
		populate_data();
	}
	
	@FXML
	private void viewClientData() throws IOException, SQLException {
		if (tblClients.getSelectionModel().getSelectedItem() != null) 
			getData();
		else
			Utils.alerti("Warning!", "Choose a record from table.", AlertType.WARNING);
	}
	
	private void getData() throws IOException, SQLException {
		clients c = tblClients.getSelectionModel().getSelectedItem();
		clients.setClient_id(c.getClient_id());
		clients.setAddress(c.getAddress());
		clients.setAgency_name(c.getAgency_name());
		clients.setPhone(c.getPhone());
		clients.setFax(c.getFax());
		clients.setComments(c.getComments());
		clients.setPrimary_biller(c.getPrimary_biller());
		clients.setPrimary_accountant(c.getPrimary_accountant());
		clients.setSecondary_biller(c.getSecondary_biller());
		clients.setSecondary_accountant(c.getSecondary_accountant());
		clients.setNpi(c.getNpi());
		clients.setPtan(c.getPtan());
		clients.setTax_id(c.getTax_id());
		
		clients.setDde_login(c.getDde_login());
		clients.setDde_password(c.getDde_password());
		clients.setSoftware_login(c.getSoftware_login());
		clients.setSoftware_password(c.getSoftware_password());

		new Utils().open_edit_scene_view_data(root, "edit_agency_view_data", "edit_icon");
		populate_data();
	}

	public void clear_search_field() {
		txtSearch.setText("");
	}

}
