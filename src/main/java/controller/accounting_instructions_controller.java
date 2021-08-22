package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.accounting_instructions;
import utils.Utils;

public class accounting_instructions_controller implements Initializable {

	@FXML private JFXTextField txtPreparer1,txtPreparer2,txtAgencyName;
	@FXML private JFXTextArea txtAdditionalInfo1,txtAdditionalInfo2;
	@FXML private JFXDatePicker dueDate1, dueDate2, dateCompleted1, dateCompleted2;
	@FXML private JFXCheckBox sent1,sent2,bookKeeping,payroll;
	@FXML private JFXButton btnCancel;
	@FXML private Label lblError;
	@FXML private JFXComboBox<String> cmbIntermediary;
	
	int accId = 0;
	ObservableList<String> options = 
	        FXCollections.observableArrayList(
	            "Palmetto",
	            "NGS",
	            "CGS"
	        );
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cmbIntermediary.getItems().addAll(options);
		txtAgencyName.setDisable(true);
		Utils.setLocalDateNow(dateCompleted1,dateCompleted2,dueDate1,dueDate2);
		if(accInstController.from == "1") {
			accId = accInstController.accountingInstructions.getAccounting_instructions_id();
			getData(accInstController.accountingInstructions);
		}
		
		if(client_profile_controller.from == "2") {
			txtAgencyName.setText(client_profile_controller.clientName);
			accId = 0;
		}
		
	}
	
	private void getData(accounting_instructions a) {
		
		txtPreparer1.setText(a.getPreparer_tax_return());
		txtPreparer2.setText(a.getPreparer_cost_report());
		txtAdditionalInfo1.setText(a.getAdditional_info_tax_return());
		txtAdditionalInfo2.setText(a.getAdditional_info_cost_report());
		dueDate1.setValue(a.getDue_date_tax_return().toLocalDate());
		dueDate2.setValue(a.getDue_date_cost_report().toLocalDate());
		dateCompleted1.setValue(a.getDate_completed_tax_return().toLocalDate());
		dateCompleted2.setValue(a.getDate_completed_cost_report().toLocalDate());
		txtAgencyName.setText(a.getAgency_name());
		cmbIntermediary.setValue(a.getIntermediary());
		
		if(a.getSent_tax_return() == 1)
			sent1.setSelected(true);
		if(a.getSent_cost_report() == 1)
			sent2.setSelected(true);
		if(a.getBook_keeping() == 1)
			bookKeeping.setSelected(true);
		if(a.getPayroll() == 1)
			payroll.setSelected(true);
	}
	
	@FXML
	private void save() throws SQLException {
		accounting_instructions a = new accounting_instructions();
		//cost report 2
		a.setAdditional_info_cost_report(txtAdditionalInfo2.getText());
		a.setAdditional_info_tax_return(txtAdditionalInfo1.getText());
		a.setBook_keeping(bookKeeping.isSelected() ? 1 : 0);
		a.setPayroll(payroll.isSelected() ? 1 : 0);
		a.setPreparer_tax_return(txtPreparer1.getText());
		a.setPreparer_cost_report(txtPreparer2.getText());
		a.setDue_date_tax_return(Date.valueOf(dueDate1.getValue()));
		a.setDue_date_cost_report(Date.valueOf(dueDate2.getValue()));
		a.setDate_completed_cost_report(Date.valueOf(dateCompleted2.getValue()));
		a.setDate_completed_tax_return(Date.valueOf(dateCompleted1.getValue()));
		int client_id = ControlDAO.getControlDao().getContactPersonDao().getIdFromAgencyName(dashboard.last_clicked);
		a.setAgency_id(client_id);
		a.setSent_cost_report(sent2.isSelected() ? 1 : 0);
		a.setSent_tax_return(sent1.isSelected() ? 1 : 0);
		a.setAccounting_instructions_id(accId);
		a.setIntermediary(cmbIntermediary.getValue());
		
		if(accId == 0)
			ControlDAO.getControlDao().getAccountingDao().addAccountingInstruction(a);
		else 
			ControlDAO.getControlDao().getAccountingDao().updateAccountingInstruction(a);
		
		
		Utils.close_stage(btnCancel);
	}
	
	@FXML
	private void cancel() {
		Utils.close_stage(btnCancel);
	}
	

}
