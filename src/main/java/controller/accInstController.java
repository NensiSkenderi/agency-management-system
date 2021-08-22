package controller;

import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.accounting_instructions;
import utils.Utils;
public class accInstController extends VBox {

	@FXML private TableView<accounting_instructions> tblAccounting;
	@FXML private TableColumn<accounting_instructions, String> tblcolAccountingId,tblcolAgencyName,tblcolPreparerTaxReturn,tblcolPreparerCostReport,tblcolIntermediary;
	@FXML private TableColumn<accounting_instructions, String> tblcolDueDateTaxReturn,tblcolDateCompletedTaxReturn,tblcolDueDateCostReport,tblcolDateCompletedCostReport,tblcolAddInfoTR,tblcolAddInfoCR;
	@FXML private TableColumn<accounting_instructions, accounting_instructions> tblcolDelete;
	@FXML private TableColumn<accounting_instructions, JFXCheckBox> tblcolSentTaxReport,tblcolSentCostReport,tblcolBookKeeping,tblcolPayroll;
	@FXML private VBox root;
	@FXML private JFXButton btnExcel;
	@FXML private JFXTextField txtSearch;

	public static String from = "";
	public ObservableList<accounting_instructions> data = FXCollections.observableArrayList();
	public static accounting_instructions accountingInstructions = new accounting_instructions();


	public accInstController() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/accountInstructions.fxml"));

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
		from = "1";
		client_profile_controller.from = "1";
		search_tableview();
		populate_table();
	}

	public void search_tableview() {
		txtSearch.textProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable o) {

	                if(txtSearch.textProperty().get().isEmpty()) {
	                	tblAccounting.setItems(data);
	                	return;
	                }

	                ObservableList<accounting_instructions> tableItems = FXCollections.observableArrayList();
	                ObservableList<TableColumn<accounting_instructions, ?>> cols = tblAccounting.getColumns();
	               
	                for(int i=0; i<data.size(); i++) {
	                	//heqim id qe nk shfaqet dhe e fillojm me j=1
	                	for(int j=1; j<cols.size()-1; j++) {
	                		TableColumn<accounting_instructions, ?> col = cols.get(j);
	                		String cellValue = col.getCellData(data.get(i)).toString();
	                		cellValue = cellValue.toLowerCase();
	                			if(cellValue.contains(txtSearch.textProperty().get().toLowerCase())) {
	                					tableItems.add(data.get(i));
	                					break;
	                				}                        
	                			}
	                		}

	                tblAccounting.setItems(tableItems);
				}
	     });
	}
	
	public void populate_data() throws SQLException {
		tblAccounting.getItems().clear();
		data.addAll(ControlDAO.getControlDao().getAccountingDao().getAllAccountingInstructions());
		tblAccounting.setItems(data);
	}

	final ObservableList<Long> checkedMessages = FXCollections.observableArrayList(new Long(1));
	
	public void populate_table() throws Exception {
		tblAccounting.getItems().clear();
		data.addAll(ControlDAO.getControlDao().getAccountingDao().getAllAccountingInstructions());

		tblcolAccountingId.setCellValueFactory(new PropertyValueFactory<>("accounting_instructions_id"));
		tblcolBookKeeping.setCellValueFactory( new Callback<CellDataFeatures<accounting_instructions, JFXCheckBox>, ObservableValue<JFXCheckBox>>() {
            @Override
            public ObservableValue<JFXCheckBox> call(
                    CellDataFeatures<accounting_instructions, JFXCheckBox> arg0) {
            	accounting_instructions user = arg0.getValue();
            	JFXCheckBox checkBox = new JFXCheckBox();
                checkBox.setDisable(true);
                for (Long value : checkedMessages) {
                   if(value.intValue()==user.getBook_keeping())
                       checkBox.selectedProperty().setValue(Boolean.TRUE);
               }   
                return new SimpleObjectProperty<JFXCheckBox>(checkBox);
        }});
		tblcolAgencyName.setCellValueFactory(new PropertyValueFactory<>("agency_name"));
		tblcolPayroll.setCellValueFactory( new Callback<CellDataFeatures<accounting_instructions, JFXCheckBox>, ObservableValue<JFXCheckBox>>() {
            @Override
            public ObservableValue<JFXCheckBox> call(
                    CellDataFeatures<accounting_instructions, JFXCheckBox> arg0) {
            	accounting_instructions user = arg0.getValue();
            	JFXCheckBox checkBox = new JFXCheckBox();
                checkBox.setDisable(true);
                for (Long value : checkedMessages) {
                   if(value.intValue()==user.getPayroll())
                       checkBox.selectedProperty().setValue(Boolean.TRUE);
               }   
                return new SimpleObjectProperty<JFXCheckBox>(checkBox);
        }});
		tblcolPreparerTaxReturn.setCellValueFactory(new PropertyValueFactory<>("preparer_tax_return"));
		tblcolPreparerCostReport.setCellValueFactory(new PropertyValueFactory<>("preparer_cost_report"));
		tblcolSentTaxReport.setCellValueFactory( new Callback<CellDataFeatures<accounting_instructions, JFXCheckBox>, ObservableValue<JFXCheckBox>>() {
            @Override
            public ObservableValue<JFXCheckBox> call(
                    CellDataFeatures<accounting_instructions, JFXCheckBox> arg0) {
            	accounting_instructions user = arg0.getValue();
            	JFXCheckBox checkBox = new JFXCheckBox();
                checkBox.setDisable(true);
                for (Long value : checkedMessages) {
                   if(value.intValue()==user.getSent_tax_return() )
                       checkBox.selectedProperty().setValue(Boolean.TRUE);
               }   
                return new SimpleObjectProperty<JFXCheckBox>(checkBox);
        }});
		tblcolSentCostReport.setCellValueFactory( new Callback<CellDataFeatures<accounting_instructions, JFXCheckBox>, ObservableValue<JFXCheckBox>>() {
            @Override
            public ObservableValue<JFXCheckBox> call(
                    CellDataFeatures<accounting_instructions, JFXCheckBox> arg0) {
            	accounting_instructions user = arg0.getValue();
            	JFXCheckBox checkBox = new JFXCheckBox();
                checkBox.setDisable(true);
                for (Long value : checkedMessages) {
                   if(value.intValue()==user.getSent_cost_report() )
                       checkBox.selectedProperty().setValue(Boolean.TRUE);
               }   
                return new SimpleObjectProperty<JFXCheckBox>(checkBox);
        }});
		
		tblcolDueDateTaxReturn.setCellValueFactory(new PropertyValueFactory<>("due_date_tax_return"));
		tblcolDateCompletedTaxReturn.setCellValueFactory(new PropertyValueFactory<>("date_completed_tax_return"));
		tblcolDueDateCostReport.setCellValueFactory(new PropertyValueFactory<>("due_date_cost_report"));
		tblcolDateCompletedCostReport.setCellValueFactory(new PropertyValueFactory<>("date_completed_cost_report"));
		tblcolAddInfoTR.setCellValueFactory(new PropertyValueFactory<>("additional_info_tax_return"));
		tblcolAddInfoCR.setCellValueFactory(new PropertyValueFactory<>("additional_info_cost_report"));
		tblcolIntermediary.setCellValueFactory(new PropertyValueFactory<>("intermediary"));
		
		tblcolDelete.setStyle("-fx-alignment:center;");
		tblcolDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tblcolDelete.setCellFactory(param -> new TableCell<accounting_instructions, accounting_instructions>() {

			JFXButton delete = new JFXButton("");
			protected void updateItem(accounting_instructions person, boolean empty) {
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
					Utils.alert_fshirje(alert," this instruction "+ "" + "", konfirmo,anullo);
					konfirmo.getStylesheets().add(getClass().getResource("/css/jfoenix_css.css").toExternalForm());
					anullo.getStylesheets().add(getClass().getResource("/css/jfoenix_css.css").toExternalForm());

					konfirmo.getStyleClass().addAll("btnConfirm","btn","btnBlurred");
					anullo.getStyleClass().addAll("btnLogout" , "btn", "btnBlurred");
					konfirmo.setOnKeyPressed(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent ke) {
							//if (ke.getCode().equals(KeyCode.ENTER))
								//confirm_delete(alert, person.getClient_communication_id());
						}
					});
					konfirmo.setOnAction(e-> {
						//confirm_delete(alert, person.getClient_communication_id());
					}); 
					anullo.setOnAction( e1 -> {
						alert.close();
					});
					Utils.refresh_focus_table(tblAccounting);
				});
			}
		});
		tblAccounting.setItems(data);
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
		accounting_instructions notes = tblAccounting.getSelectionModel().getSelectedItem();
		accountingInstructions.setAccounting_instructions_id(notes.getAccounting_instructions_id());
		accountingInstructions.setAgency_id(notes.getAgency_id());
		accountingInstructions.setSent_tax_return(notes.getSent_tax_return());
		accountingInstructions.setSent_cost_report(notes.getSent_cost_report());
		accountingInstructions.setBook_keeping(notes.getBook_keeping());
		accountingInstructions.setPayroll(notes.getPayroll());
		
		accountingInstructions.setPreparer_tax_return(notes.getPreparer_tax_return());
		accountingInstructions.setPreparer_cost_report(notes.getPreparer_cost_report());
		accountingInstructions.setAdditional_info_tax_return(notes.getAdditional_info_tax_return());
		accountingInstructions.setAdditional_info_cost_report(notes.getAdditional_info_cost_report());
		
		accountingInstructions.setDue_date_tax_return(notes.getDue_date_tax_return());
		accountingInstructions.setDate_completed_tax_return(notes.getDate_completed_tax_return());
		accountingInstructions.setDue_date_cost_report(notes.getDue_date_cost_report());
		accountingInstructions.setDate_completed_cost_report(notes.getDate_completed_cost_report());
		accountingInstructions.setAgency_name(notes.getAgency_name());
		
		accountingInstructions.setIntermediary(notes.getIntermediary());
		
		new Utils().openSceneNoResizable("add_accounting" , "Edit Account Instructions", "/images/add_red.png");
		populate_data();
	}

	@FXML
	private void edit() throws IOException, SQLException {
		if (tblAccounting.getSelectionModel().getSelectedItem() != null) 
			getData();
		else
			Utils.alerti("Warning!", "Choose a record from table.", AlertType.WARNING);

	}


	public void clear_search_field() {
		txtSearch.setText("");
	}

}
