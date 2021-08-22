package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.jfoenix.controls.JFXButton;

import dao.ControlDAO;
import dao.db_connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.accounting_instructions;
import model.insurances;
import model.report_agency;
public class reports_controller extends VBox {

	@FXML private Button btnExcel;

	public ObservableList<report_agency> data1 = FXCollections.observableArrayList();
	public ObservableList<insurances> data2 = FXCollections.observableArrayList();
	public ObservableList<accounting_instructions> data3 = FXCollections.observableArrayList();

	public reports_controller() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/reports.fxml"));

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
		data1.addAll(ControlDAO.getControlDao().getReportsDao().getFirstReport());
		data2.addAll(ControlDAO.getControlDao().getNewPayerDao().getAllInsurances());
		data3.addAll(ControlDAO.getControlDao().getReportsDao().getThirdReport());
	}

	@FXML
	private void report1excel() {
		try {

			Stage stage = (Stage)btnExcel.getScene().getWindow();

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Comma Delimited (*.csv)", "*.csv");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(stage);
			FileWriter fileWriter = new FileWriter(file);

			String text = "";
			String header = "Nr" + "," + "Agency Name" + "," + "Primary Biller" + "," + "Primary Accountant"+ "," + "Invoiced For Month"+ ", "+ "Biller Notes"+ ", "+ "Client Communication Notes"+"\n" ;

			fileWriter.write(header);
			for(int i=0; i<data1.size(); i++){

				text = i+1 + "," + data1.get(i).getAgency_name() + "," + data1.get(i).getPrimary_biller() + "," 
						+ data1.get(i).getPrimary_accountant()+ ","+ data1.get(i).getPrimary_accountant() +  ","+ data1.get(i).getPrimary_accountant() +
						","+ data1.get(i).getPrimary_accountant() + "\n";
				fileWriter.write(text);
			}

			fileWriter.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@FXML
	private void report2excel() {
		try {

			Stage stage = (Stage)btnExcel.getScene().getWindow();

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Comma Delimited (*.csv)", "*.csv");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(stage);
			FileWriter fileWriter = new FileWriter(file);

			String text = "";
			String header = "Nr" + "," + "Agency Name" + "," + "Billing Instructions" + "," + "Insurance Name"+ "\n" ;

			fileWriter.write(header);
			for(int i=0; i<data2.size(); i++){

				text = i+1 + "," + data2.get(i).getAgency_name() + "," + data2.get(i).getInstructions() + "," 
						+ data2.get(i).getInsurance_name()+ "\n";
				fileWriter.write(text);
			}

			fileWriter.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	String sentCR = "";
	String sentTR = "";

	@FXML
	private void report4excel() {
		try {

			Stage stage = (Stage)btnExcel.getScene().getWindow();

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Comma Delimited (*.csv)", "*.csv");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(stage);
			FileWriter fileWriter = new FileWriter(file);

			String text = "";
			String header = "Nr" + "," + "Agency Name" + "," +
					"Tax Return"+ ", "+ "TR Prepared By" + ", "+ "Sent to Client (Y/N)" + ", " + "TR Notes"  + "\n" ;


			fileWriter.write(header);
			for(int i=0; i<data3.size(); i++){

				if(data3.get(i).getSent_tax_return() == 0 )
					sentTR = "No";
				else
					sentTR = "Yes";

				text = i+1 + "," + data3.get(i).getAgency_name() +
						","+ "" + "," + data3.get(i).getPreparer_tax_return() +  ","+ sentTR +
						","+ data3.get(i).getAdditional_info_tax_return() +  "\n";
				fileWriter.write(text);
			}

			fileWriter.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@FXML
	private void report3excel() {
		try {

			Stage stage = (Stage)btnExcel.getScene().getWindow();

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Comma Delimited (*.csv)", "*.csv");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(stage);
			FileWriter fileWriter = new FileWriter(file);

			String text = "";
			String header = "Nr" + "," + "Agency Name" + "," + "Cost Report" + "," + "CR Prepared By"+ "," + "Sent to Client (Y/N)"+ ", "+ "CR Notes"+ "\n";


			fileWriter.write(header);
			for(int i=0; i<data3.size(); i++){

				if(data3.get(i).getSent_cost_report() == 0 )
					sentCR = "No";
				else
					sentCR = "Yes";

				text = i+1 + "," + data3.get(i).getAgency_name() + "," + " " + "," 
						+ data3.get(i).getPreparer_cost_report()+ ","+ sentCR +  ","+ data3.get(i).getAdditional_info_cost_report() + "\n";
				fileWriter.write(text);
			}

			fileWriter.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	int i = 0;
	
	@FXML
	private void report1pdf() {
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
	
			Paragraph p3 = new Paragraph("Invoice Report",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0)));
			p3.setAlignment(Paragraph.ALIGN_CENTER);
			p3.setSpacingBefore(25);		
			document.add(p3);
	
			PdfPTable t = new PdfPTable(7);
			t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			t.setSpacingBefore(30);
			t.setWidthPercentage(95);
			t.setSpacingAfter(5);
			Font bold = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
	
			Phrase phrase1 = new Phrase("Nr", bold);
			PdfPCell c1 = new PdfPCell(phrase1);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c1);
			Phrase phrase2 = new Phrase("Agency Name", bold);
			PdfPCell c2 = new PdfPCell(phrase2);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c2);
			Phrase phrase3 = new Phrase("Primary Biller", bold);
			PdfPCell c3 = new PdfPCell(phrase3);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c3);
			Phrase phrase4 = new Phrase("Primary Accountant", bold);
			PdfPCell c4 = new PdfPCell(phrase4);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c4);
			Phrase phrase5 = new Phrase("Invoiced For Month", bold);
			PdfPCell c5 = new PdfPCell(phrase5);
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c5);
			Phrase phrase6 = new Phrase("Biller Notes", bold);
			PdfPCell c6 = new PdfPCell(phrase6);
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c6);
			Phrase phrase7 = new Phrase("Client Communication Notes", bold);
			PdfPCell c7 = new PdfPCell(phrase7);
			c7.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c7);
	
			for(report_agency table_pdf : data1){
				i = i+1;
				t.addCell(Integer.toString(i));
				t.addCell(table_pdf.getAgency_name());
				t.addCell(table_pdf.getPrimary_biller());
				t.addCell(table_pdf.getPrimary_accountant());
				t.addCell(table_pdf.getInvoiced_for_month());
				t.addCell(table_pdf.getBiller_notes());
				t.addCell(table_pdf.getClient_communication_notes());
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
	
	int j = 0;
	
	@FXML
	private void report2pdf() throws Exception {
		openReport2Scene();
	}
	
	int k = 0;
	
	protected Connection connector = db_connection.instance().getConnection();
    protected ResultSet rs;
    protected PreparedStatement stm;
	
    private void report2PDF(String agency_name) {
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
	
			Paragraph p3 = new Paragraph("Instructions",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0)));
			p3.setAlignment(Paragraph.ALIGN_CENTER);
			p3.setSpacingBefore(25);		
			document.add(p3);
	
			PdfPTable t = new PdfPTable(4);
			t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			t.setSpacingBefore(30);
			t.setWidthPercentage(95);
			t.setSpacingAfter(5);
			Font bold = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
	
			Phrase phrase1 = new Phrase("Nr", bold);
			PdfPCell c1 = new PdfPCell(phrase1);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c1);
			Phrase phrase2 = new Phrase("Agency Name", bold);
			PdfPCell c2 = new PdfPCell(phrase2);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c2);
			Phrase phrase3 = new Phrase("Billing Instructions", bold);
			PdfPCell c3 = new PdfPCell(phrase3);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c3);
			Phrase phrase4 = new Phrase("Insurance Name", bold);
			PdfPCell c4 = new PdfPCell(phrase4);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c4);
			data2.clear();
			data2.addAll(ControlDAO.getControlDao().getNewPayerDao().getAllInsurances(agency_name));
			
			for(insurances table_pdf : data2){
				j = j+1;
				t.addCell(Integer.toString(j));
				t.addCell(table_pdf.getAgency_name());
				t.addCell(table_pdf.getInstructions());
				t.addCell(table_pdf.getInsurance_name());
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
			System.out.println(ex);
		}
    }
    
	private void openReport2Scene() throws Exception {
		Stage stage = new Stage();
		ScrollPane root = new ScrollPane();
		TilePane tilePane = new TilePane();
		VBox vbox = new VBox();
		
		List<String> buttons = ControlDAO.getControlDao().getNewPayerDao().getQuickInfo();

		for(String b1 : buttons) {
			VBox vb = new VBox();
			JFXButton b = new JFXButton("Download Report for : " + b1);
			b.setStyle("-fx-background-color: #1794B2;-fx-text-fill: white;-fx-cursor: hand;");
			
			b.setOnAction(e -> {
				report2PDF(b1);
			});
			
			vb.getChildren().add(b);

			vb.setStyle("-fx-border-color : #1794B2;-fx-padding : 15px");
			
			vbox.getChildren().add(vb);
			VBox.setMargin(vb, new Insets(20,20,20,20));
		}
		
		root.setStyle("-fx-background-color : white");
		tilePane.setStyle("-fx-background-color : white");
		tilePane.getChildren().add(vbox);
		root.setContent(tilePane);
		root.setFitToWidth(true);
		root.setMaxWidth(400);
		root.setMinWidth(400);
		root.setPrefWidth(400);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		stage.setResizable(true);
		stage.setTitle("Billing Based on Client");
		stage.getIcons().add(new Image("/images/billing.png"));
		stage.show();
	}
	
	@FXML
	private void report3pdf() {
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
	
			Paragraph p3 = new Paragraph("Cost Report",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0)));
			p3.setAlignment(Paragraph.ALIGN_CENTER);
			p3.setSpacingBefore(25);		
			document.add(p3);
	
			PdfPTable t = new PdfPTable(6);
			t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			t.setSpacingBefore(30);
			t.setWidthPercentage(95);
			t.setSpacingAfter(5);
			Font bold = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
	
			Phrase phrase1 = new Phrase("Nr", bold);
			PdfPCell c1 = new PdfPCell(phrase1);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c1);
			Phrase phrase2 = new Phrase("Agency Name", bold);
			PdfPCell c2 = new PdfPCell(phrase2);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c2);
			Phrase phrase3 = new Phrase("Cost Report", bold);
			PdfPCell c3 = new PdfPCell(phrase3);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c3);
			Phrase phrase4 = new Phrase("CR Prepared By", bold);
			PdfPCell c4 = new PdfPCell(phrase4);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c4);
			Phrase phrase5 = new Phrase("Sent to Client (Y/N)", bold);
			PdfPCell c5 = new PdfPCell(phrase5);
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c5);
			Phrase phrase6 = new Phrase("CR Notes", bold);
			PdfPCell c6 = new PdfPCell(phrase6);
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c6);
	
			for(accounting_instructions table_pdf : data3){
				k = k+1;
				t.addCell(Integer.toString(k));
				t.addCell(table_pdf.getAgency_name());
				t.addCell("---->");
				t.addCell(table_pdf.getPreparer_cost_report());
				if(table_pdf.getSent_cost_report() == 1 )
					t.addCell("Y");
				else
					t.addCell("N");
				t.addCell(table_pdf.getAdditional_info_cost_report());
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
	
	int m = 0;
	
	@FXML
	private void report4pdf() {
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
	
			Paragraph p3 = new Paragraph("Cost Report",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0)));
			p3.setAlignment(Paragraph.ALIGN_CENTER);
			p3.setSpacingBefore(25);		
			document.add(p3);
	
			PdfPTable t = new PdfPTable(6);
			t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			t.setSpacingBefore(30);
			t.setWidthPercentage(95);
			t.setSpacingAfter(5);
			Font bold = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
	
			Phrase phrase1 = new Phrase("Nr", bold);
			PdfPCell c1 = new PdfPCell(phrase1);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c1);
			Phrase phrase2 = new Phrase("Agency Name", bold);
			PdfPCell c2 = new PdfPCell(phrase2);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c2);
			Phrase phrase3 = new Phrase("Tax Return", bold);
			PdfPCell c3 = new PdfPCell(phrase3);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c3);
			Phrase phrase4 = new Phrase("TR Prepared By", bold);
			PdfPCell c4 = new PdfPCell(phrase4);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c4);
			Phrase phrase5 = new Phrase("Sent to Client (Y/N)", bold);
			PdfPCell c5 = new PdfPCell(phrase5);
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c5);
			Phrase phrase6 = new Phrase("TR Notes", bold);
			PdfPCell c6 = new PdfPCell(phrase6);
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			t.addCell(c6);
	
			for(accounting_instructions table_pdf : data3){
				m = m+1;
				t.addCell(Integer.toString(m));
				t.addCell(table_pdf.getAgency_name());
				t.addCell("---->");
				t.addCell(table_pdf.getPreparer_tax_return());
				if(table_pdf.getSent_tax_return() == 1 )
					t.addCell("Y");
				else
					t.addCell("N");
				t.addCell(table_pdf.getAdditional_info_tax_return());
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

}
