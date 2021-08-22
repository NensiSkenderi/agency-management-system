package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.agency_list;
import model.agency_quick_info;
import model.clients;
import utils.Utils;
public class quickInfoController extends VBox {

	@FXML private Label lblChoose;
	@FXML private TableView<clients> tblClients;
	@FXML private TableColumn<clients, String> tblcolClientId,tblcolAgencyName,tblcolAddress;
	@FXML private TableColumn<clients, clients> tblcolDelete, tblcolAddToQucikInfo;
	@FXML private VBox root;

	public ObservableList<clients> data = FXCollections.observableArrayList();
	public static clients clients = new clients();

	public quickInfoController() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/quickInfo.fxml"));

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
		populate_table();
	}

	public void populate_data() throws SQLException {
		tblClients.getItems().clear();
		data.addAll(ControlDAO.getControlDao().getClientDao().getAllClientsInfo());
		tblClients.setItems(data);
	}

	public void populate_table() throws Exception {
		tblClients.getItems().clear();
		data.addAll(ControlDAO.getControlDao().getClientDao().getAllClientsInfo());

		tblcolClientId.setCellValueFactory(new PropertyValueFactory<>("client_id"));
		tblcolAgencyName.setCellValueFactory(new PropertyValueFactory<>("agency_name"));
		tblcolAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		tblcolAddress.setStyle("-fx-alignment:center;");
		tblcolAddToQucikInfo.setStyle("-fx-alignment:center;");
		tblcolAddToQucikInfo.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tblcolAddToQucikInfo.setCellFactory(param -> new TableCell<clients, clients>() {

			JFXButton add = new JFXButton("");
			protected void updateItem(clients person, boolean empty) {
				super.updateItem(person, empty);

				if (person == null) {
					setGraphic(null);
					return;
				}

				agency_quick_info agencyQuickInfo = new agency_quick_info();
				agencyQuickInfo.setUser_id(Utils.idP);
				agencyQuickInfo.setClient_id(person.getClient_id());

				setGraphic(add);
				Utils.styleAddButton(add);
				add.setOnMouseClicked(event -> {
					JFXAlert alert = new JFXAlert((Stage) add.getScene().getWindow());
					JFXButton anullo = new JFXButton("Cancel");
					JFXButton konfirmo = new JFXButton("Confirm");
					
					try {
						if(!ControlDAO.getControlDao().getQuickInfoDao().checkIfExists(person.getClient_id()))
							Utils.alert_add_to_list(alert,"client : "+person.getAgency_name()+" to quick list?", konfirmo,anullo);
						else
							Utils.alerti("Already added!", "You have already added : "+person.getAgency_name()+ " to your list", AlertType.INFORMATION); 
					} catch (SQLException e3) {
						e3.printStackTrace();
					}
					konfirmo.getStylesheets().add(getClass().getResource("/css/jfoenix_css.css").toExternalForm());
					anullo.getStylesheets().add(getClass().getResource("/css/jfoenix_css.css").toExternalForm());

					konfirmo.getStyleClass().addAll("btnConfirm","btn","btnBlurred");
					anullo.getStyleClass().addAll("btnLogout" , "btn", "btnBlurred");
					konfirmo.setOnAction(e-> {
						add_to_quick_info(alert, agencyQuickInfo);
						try {
							new Utils().open_viewLabel("dashboard", lblChoose, "/images/login_logo.png", "Dashboard");
						} catch (IOException e2) {
							e2.printStackTrace();
						}
					}); 
					anullo.setOnAction( e1 -> {
						alert.close();
					});
					Utils.refresh_focus_table(tblClients);
				});
			}
		});
		
		tblClients.setItems(data);
	}
	
	
	@FXML
	private void removeFromList() throws IOException, SQLException {
		remove_from_list();
	}

	public void remove_from_list() throws SQLException {
		clients c = tblClients.getSelectionModel().getSelectedItem();
		if(c == null) {
			Utils.alerti("Warning!", "Choose a record from table.", AlertType.WARNING);
			return;
		}
			
		else {
			if (ControlDAO.getControlDao().getQuickInfoDao().checkIfExists(c.getClient_id())) {
				try {
					ControlDAO.getControlDao().getQuickInfoDao().remove_from_quickInfo(c.getClient_id(), Utils.idP);
					populate_data();
					new Utils().open_viewLabel("dashboard", lblChoose, "/images/login_logo.png", "Dashboard");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			else {
				Utils.alerti("Warning!", "You can't remove "+ c.getAgency_name() + ""
						+ " because it is not on the quick list", AlertType.WARNING);
				return;
			}
		}
		
	}
	
	public void add_to_quick_info(JFXAlert alert, agency_quick_info a) {
		try {
			alert.hideWithAnimation();
			root.setEffect(null);
			ControlDAO.getControlDao().getQuickInfoDao().addToQuickInfo(a);
			populate_data();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
