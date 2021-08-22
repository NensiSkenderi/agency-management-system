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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.client_communication_notes;
import utils.Utils;
public class clients_comm_notes_controller extends VBox {

	@FXML private TableView<client_communication_notes> tblClientCommNotes;
	@FXML private TableColumn<client_communication_notes, String> tblcolClientCommid,tblcolCommNotes,tblcolAgencyName;
	@FXML private TableColumn<client_communication_notes, client_communication_notes> tblcolDelete;
	@FXML private VBox root;
	@FXML private JFXButton btnExcel;
	@FXML private JFXTextField txtSearch;

	public static boolean edit = false;
	public ObservableList<client_communication_notes> data = FXCollections.observableArrayList();
	public static client_communication_notes clientNotes = new client_communication_notes();


	public clients_comm_notes_controller() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/client_comm_notes.fxml"));

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
		search_tableview();
		populate_table();
	}

	public void search_tableview() {
		txtSearch.textProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable o) {

	                if(txtSearch.textProperty().get().isEmpty()) {
	                	tblClientCommNotes.setItems(data);
	                	return;
	                }

	                ObservableList<client_communication_notes> tableItems = FXCollections.observableArrayList();
	                ObservableList<TableColumn<client_communication_notes, ?>> cols = tblClientCommNotes.getColumns();
	               
	                for(int i=0; i<data.size(); i++) {
	                	//heqim id qe nk shfaqet dhe e fillojm me j=1
	                	for(int j=1; j<cols.size()-1; j++) {
	                		TableColumn<client_communication_notes, ?> col = cols.get(j);
	                		String cellValue = col.getCellData(data.get(i)).toString();
	                		cellValue = cellValue.toLowerCase();
	                			if(cellValue.contains(txtSearch.textProperty().get().toLowerCase())) {
	                					tableItems.add(data.get(i));
	                					break;
	                				}                        
	                			}
	                		}

	                tblClientCommNotes.setItems(tableItems);
				}
	     });
	}
	
	public void populate_data() throws SQLException {
		tblClientCommNotes.getItems().clear();
		data.addAll(ControlDAO.getControlDao().getClientCommNotesDao().getAllClientNotes());
		tblClientCommNotes.setItems(data);
	}

	public void populate_table() throws Exception {
		tblClientCommNotes.getItems().clear();
		data.addAll(ControlDAO.getControlDao().getClientCommNotesDao().getAllClientNotes());
		tblcolClientCommid.setCellValueFactory(new PropertyValueFactory<>("client_communication_id"));
		tblcolCommNotes.setCellValueFactory(new PropertyValueFactory<>("client_comm_notes_desc"));
		tblcolAgencyName.setCellValueFactory(new PropertyValueFactory<>("agency_name"));
		tblcolDelete.setStyle("-fx-alignment:center;");
		tblcolDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tblcolDelete.setCellFactory(param -> new TableCell<client_communication_notes, client_communication_notes>() {

			JFXButton delete = new JFXButton("");
			protected void updateItem(client_communication_notes person, boolean empty) {
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
					Utils.alert_fshirje(alert," this note "+ "" + "", konfirmo,anullo);
					konfirmo.getStylesheets().add(getClass().getResource("/css/jfoenix_css.css").toExternalForm());
					anullo.getStylesheets().add(getClass().getResource("/css/jfoenix_css.css").toExternalForm());

					konfirmo.getStyleClass().addAll("btnConfirm","btn","btnBlurred");
					anullo.getStyleClass().addAll("btnLogout" , "btn", "btnBlurred");
					konfirmo.setOnKeyPressed(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent ke) {
							if (ke.getCode().equals(KeyCode.ENTER))
								confirm_delete(alert, person.getClient_communication_id());
						}
					});
					konfirmo.setOnAction(e-> {
						confirm_delete(alert, person.getClient_communication_id());
					}); 
					anullo.setOnAction( e1 -> {
						alert.close();
					});
					Utils.refresh_focus_table(tblClientCommNotes);
				});
			}
		});
		tblClientCommNotes.setItems(data);
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

	private void getData() throws IOException, SQLException {
		client_communication_notes notes = tblClientCommNotes.getSelectionModel().getSelectedItem();
		clientNotes.setAgency_name(notes.getAgency_name());
		clientNotes.setClient_communication_id(notes.getClient_communication_id());
		clientNotes.setClient_comm_notes_desc(notes.getClient_comm_notes_desc());

		new Utils().open_edit_scene(root, "add_new_clientcommNotes", "edit_icon");
		populate_data();
	}

	@FXML
	private void edit() throws IOException, SQLException {
		edit = true;
		if (tblClientCommNotes.getSelectionModel().getSelectedItem() != null) 
			getData();
		else
			Utils.alerti("Warning!", "Choose a record from table.", AlertType.WARNING);

	}

	@FXML
	private void add() throws IOException, SQLException {
		edit = false;
		new Utils().open_edit_scene(root, "add_new_clientcommNotes", "edit_icon");
		populate_data();
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

			Paragraph p3 = new Paragraph("Client Communication Notes",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0)));
			p3.setAlignment(Paragraph.ALIGN_CENTER);
			p3.setSpacingBefore(25);		
			document.add(p3);

			PdfPTable t = new PdfPTable(2);
			t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			t.setSpacingBefore(30);
			t.setWidthPercentage(95);
			t.setSpacingAfter(5);
			Font bold = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
			
			Phrase phrase1 = new Phrase("Client Communication Notes", bold);
			PdfPCell c1 = new PdfPCell(phrase1);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c1);
			Phrase phrase2 = new Phrase("Agency Name", bold);
			PdfPCell c2 = new PdfPCell(phrase2);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c2);
			

			for(client_communication_notes table_pdf : data){
				t.addCell(table_pdf.getClient_comm_notes_desc());
				t.addCell(table_pdf.getAgency_name());
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
			String header = "Nr" + "," + "Client Communication Notes" + "," + "Agency Name" +"\n" ;

			fileWriter.write(header);
			for(int i=0; i<data.size(); i++){

				text = i+1 + "," + data.get(i).getClient_comm_notes_desc() + "," + data.get(i).getAgency_name() + "\n";
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
