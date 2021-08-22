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
import model.clients;
import utils.Utils;
public class clients_controller extends VBox {

	@FXML private Label lblError;
	@FXML private TableView<clients> tblClients;
	@FXML private TableColumn<clients, String> tblcolClientId,tblcolAgencyName,tblcolAddress,tblcolPhone,tblcolFax,tblcolNpi,tblcolPtan,tblcolTax,tblcolComments,
	tblcolPrimaryBiller,tbolcolPrimaryAccountant,tblcolSecondaryBiller,tblcolSecondaryAccountant;
	@FXML private TableColumn<clients, String> tblcolDDELogin,tblcolDDEPassword,tblcolSoftwareLogin,tblcolSoftwarePassword;
	@FXML private TableColumn<clients, clients> tblcolDelete, tblcolAddToList;
	@FXML private VBox root;
	@FXML private JFXButton btnExcel,btnEdit;
	@FXML private JFXTextField txtSearch;

	public ObservableList<clients> data = FXCollections.observableArrayList();
	public static clients clients = new clients();

	public clients_controller() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/clients.fxml"));

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
		
		tblcolDelete.setStyle("-fx-alignment:center;");
		tblcolDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tblcolDelete.setCellFactory(param -> new TableCell<clients, clients>() {

			JFXButton delete = new JFXButton("");
			protected void updateItem(clients person, boolean empty) {
				super.updateItem(person, empty);

				if (person == null) {
					setGraphic(null);
					return;
				}


				setGraphic(delete);
				Utils.style_delete_button(delete);
				delete.setOnMouseClicked(event -> {
					JFXAlert alert = new JFXAlert((Stage) delete.getScene().getWindow());
					JFXButton anullo = new JFXButton("Cancel");
					JFXButton konfirmo = new JFXButton("Confirm");
					Utils.alert_fshirje(alert,"client : "+person.getAgency_name()+" ?", konfirmo,anullo);
					konfirmo.getStylesheets().add(getClass().getResource("/css/jfoenix_css.css").toExternalForm());
					anullo.getStylesheets().add(getClass().getResource("/css/jfoenix_css.css").toExternalForm());

					konfirmo.getStyleClass().addAll("btnConfirm","btn","btnBlurred");
					anullo.getStyleClass().addAll("btnLogout" , "btn", "btnBlurred");
					konfirmo.setOnKeyPressed(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent ke) {
							if (ke.getCode().equals(KeyCode.ENTER))
								confirm_delete(alert, person.getClient_id());
						}
					});
					konfirmo.setOnAction(e-> {
						confirm_delete(alert, person.getClient_id());
						try {
							new Utils().open_viewButton("dashboard", btnEdit, "/images/login_logo.png", "Dashboard");
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
		
		
		tblcolAddToList.setStyle("-fx-alignment:center;");
		tblcolAddToList.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tblcolAddToList.setCellFactory(param -> new TableCell<clients, clients>() {

			JFXButton add = new JFXButton("");
			protected void updateItem(clients person, boolean empty) {
				super.updateItem(person, empty);

				if (person == null) {
					setGraphic(null);
					return;
				}

				agency_list agency = new agency_list();
				agency.setUser_id(Utils.idP);
				agency.setClient_id(person.getClient_id());

				setGraphic(add);
				Utils.styleAddButton(add);
				add.setOnMouseClicked(event -> {
					JFXAlert alert = new JFXAlert((Stage) add.getScene().getWindow());
					JFXButton anullo = new JFXButton("Cancel");
					JFXButton konfirmo = new JFXButton("Confirm");
					
					try {
						if(!ControlDAO.getControlDao().getAddToListDao().checkIfExists(person.getClient_id()))
							Utils.alert_add_to_list(alert,"client : "+person.getAgency_name()+" to your list?", konfirmo,anullo);
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
						add_to_list(alert, agency);
						try {
							new Utils().open_viewButton("dashboard", btnEdit, "/images/login_logo.png", "Dashboard");
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
			if (ControlDAO.getControlDao().getAddToListDao().checkIfExists(c.getClient_id())) {
				try {
					ControlDAO.getControlDao().getAddToListDao().remove_from_list(c.getClient_id(), Utils.idP);
					populate_data();
					new Utils().open_viewButton("dashboard", btnEdit, "/images/login_logo.png", "Dashboard");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			else {
				Utils.alerti("Warning!", "You can't remove "+ c.getAgency_name() + ""
						+ " because it is not on your list", AlertType.WARNING);
				return;
			}
		}
		
	}
	
	public void confirm_delete(JFXAlert alert, int id) {
		try {
			alert.hideWithAnimation();
			root.setEffect(null);
			ControlDAO.getControlDao().getClientDao().deleteClient(id);
			populate_data();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void add_to_list(JFXAlert alert, agency_list a) {
		try {
			alert.hideWithAnimation();
			root.setEffect(null);
			ControlDAO.getControlDao().getAddToListDao().addToList(a);
			populate_data();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
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

		new Utils().open_edit_scene(root, "edit_agency", "edit_icon");
		populate_data();
	}
	
	@FXML
	private void edit() throws IOException, SQLException {
		if (tblClients.getSelectionModel().getSelectedItem() != null) 
			getData();
		else
			Utils.alerti("Warning!", "Choose a record from table.", AlertType.WARNING);

	}

	public void click_item() {
		tblClients.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent event) {
				if((event.getClickCount() == 1) && (tblClients.getSelectionModel().getSelectedItem() != null)) 
					btnEdit.setDisable(false);

				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					try {
						if (tblClients.getSelectionModel().getSelectedItem() == null) 
							return;
						else
							getData();
					} catch (IOException | SQLException e) {
						e.printStackTrace();
					}               
				}
			}
		});
	}

	@FXML
	private void pdf() {
		try {

			Stage stage = (Stage)btnExcel.getScene().getWindow();

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF (*.PDF, *.pdf)", "*.pdf","*.PDF");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(stage);

			Document document = new Document(PageSize.A4.rotate(), 5f, 5f, 5f, 5f);
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(file.getAbsolutePath()));

			document.open();

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

			Anchor anchorTarget = new Anchor("Date "+dateFormat.format(date) + " Time " + sdf.format(cal.getTime()));

			Paragraph paragraph1 = new Paragraph();
			paragraph1.setAlignment(Paragraph.ALIGN_RIGHT);
			paragraph1.setSpacingBefore(15);
			paragraph1.setSpacingAfter(15);

			paragraph1.add(anchorTarget);
			document.add(paragraph1);

			Paragraph p2 = new Paragraph("Liberty",FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLD, new CMYKColor(169,169,169,0)));
			p2.setAlignment(Paragraph.ALIGN_CENTER);
			p2.setSpacingBefore(20);		
			document.add(p2);

			Paragraph p3 = new Paragraph("Client Data",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0)));
			p3.setAlignment(Paragraph.ALIGN_CENTER);
			p3.setSpacingBefore(25);		
			document.add(p3);

			PdfPTable t = new PdfPTable(6);
			t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			t.setSpacingBefore(30);
			t.setWidthPercentage(95);
			t.setSpacingAfter(5);
			Font bold = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
			
			Phrase phrase1 = new Phrase("Agency Name", bold);
			PdfPCell c1 = new PdfPCell(phrase1);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c1);
			Phrase phrase2 = new Phrase("Address", bold);
			PdfPCell c2 = new PdfPCell(phrase2);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c2);
			Phrase phrase3 = new Phrase("Phone", bold);
			PdfPCell c3 = new PdfPCell(phrase3);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c3);
			Phrase phrase4 = new Phrase("Fax", bold);
			PdfPCell c4 = new PdfPCell(phrase4);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c4);
			Phrase phrase5 = new Phrase("Npi", bold);
			PdfPCell c5 = new PdfPCell(phrase5);
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c5);
			Phrase phrase6 = new Phrase("Ptan", bold);
			PdfPCell c6 = new PdfPCell(phrase6);
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c6);
			

			for(clients table_pdf : data){
				t.addCell(table_pdf.getAgency_name());
				t.addCell(table_pdf.getAddress());
				t.addCell(table_pdf.getPhone());
				t.addCell(table_pdf.getFax());
				t.addCell(table_pdf.getNpi());
				t.addCell(table_pdf.getPtan());
			}
			document.add(t);
			
			PdfPTable table = new PdfPTable(1);
			table.setWidthPercentage(95);
			table.setSpacingBefore(50);
			table.getDefaultCell().setUseAscender(true);
			table.getDefaultCell().setUseDescender(true);
			Font f = new Font(FontFamily.TIMES_ROMAN, 17.0f, Font.ITALIC, GrayColor.BLACK);
			PdfPCell cell = new PdfPCell(new Phrase("Thank you!", f));
			BaseColor color = new BaseColor(230,232,233);
			cell.setBackgroundColor(color);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingTop(3);
			cell.setPaddingBottom(8);
			cell.setColspan(3);
			table.addCell(cell);
			document.add(table);
			document.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@FXML
	private void excel() {
		try {

			Stage stage = (Stage)btnExcel.getScene().getWindow();

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Comma Delimited (*.csv)", "*.csv");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(stage);
			FileWriter fileWriter = new FileWriter(file);

			String text = "";
			String header = "Nr" + "," + "Agency Name" + "," + "Address" + "," + "Phone"+ "," + "Fox"+ ", "+ "Npi"+ ", "+ "Ptan"+"\n" ;

			fileWriter.write(header);
			for(int i=0; i<data.size(); i++){

				text = i+1 + "," + data.get(i).getAgency_name() + "," + data.get(i).getAddress() + "," 
						+ data.get(i).getPhone()+ ","+ data.get(i).getFax() +  ","+ data.get(i).getNpi() +
						","+ data.get(i).getPtan() + "\n";
				fileWriter.write(text);
			}

			fileWriter.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void clear_search_field() {
		txtSearch.setText("");
	}

}
