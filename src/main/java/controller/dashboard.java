package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import dao.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.global;
import model.users;
import utils.Controllers;
import utils.Utils;

public class dashboard extends DAO implements Initializable{
	@FXML public VBox switch_view,main_vbox,chart,chart_1,root;
	@FXML public TitledPane pane1,pane2,pane5,pane6,pane7;
	@FXML private ListView<String> list1,list2,list5,list6,list7;
	@FXML private FontAwesomeIconView iconSearch,iconDashboard;
	@FXML private Pane pane_upside;
	@FXML private HBox root_base;
	@FXML private Label lblData,lblUsername;
	@FXML private Button changePass,btnAddEmployee;
	List<String> c, c_updates;
	public static String last_clicked = "", last_clicked_updates = "";

	public void initialize(URL location, ResourceBundle resources) {
		try {
			lblUsername.setText(Utils.username);
			Utils.full_name.addListener(new ChangeListener<Object>(){
				@Override
				public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
					lblUsername.setText((String)newValue);
				}
			});
			titled_pane_listeners(pane1,pane2,pane5,pane6,pane7);

			if(Utils.user_group.equals("Admin"))
				btnAddEmployee.setVisible(true);

			c = ControlDAO.getControlDao().getAgencyUsersDao().getAgencyByClientsForList(Utils.idP);
			c_updates = ControlDAO.getControlDao().getAgencyUsersDao().getAgencyByClients(Utils.idP);
			list1.getItems().addAll(c);
			list2.getItems().addAll(global.b2,global.b3,global.b10);
			list5.getItems().addAll(c_updates);
			list6.getItems().addAll(global.b8);
			list7.getItems().addAll(global.b7,global.b4,global.b11,global.b9,global.b12,global.b13);

			subitems_clicked(list2);
			subitems_clicked(list5);
			subitems_clicked(list6);
			subitems_clicked(list7);
			clickedOnClientNames();
			clickedOnClientNamesUpdates();
			Controllers.getClients(switch_view);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void openQuickInfo() throws IOException, SQLException {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		Stage stage = new Stage();
		ScrollPane root = new ScrollPane();
		TilePane tilePane = new TilePane();
		VBox vbox = new VBox();
		
		String query = "select c.client_id,agency_name,address,phone,fax,npi,ptan,dde_login,dde_password,tax_id from liberty.clients c " + 
				"join liberty.agency_quick_info a on c.client_id = a.client_id " + 
				"group by agency_name";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			VBox vb = new VBox();
			
			HBox labelAgency = new HBox(20);
			HBox labelNpi = new HBox(20);
			HBox labelPtan = new HBox(20);
			HBox labelTax = new HBox(20);
			HBox labelDde= new HBox(20);
			
			Label agencyName_label = new Label("Agency Name : ");
			Label npi_label  = new Label("NPI : ");
			Label ptan_label  = new Label("PTAN : ");
			Label tax_label  = new Label("TAX ID : ");
			Label dde_label  = new Label("DDE : ");
			
			agencyName_label.setStyle(" -fx-text-fill : #1794B2;-fx-font-size : 20px;");
			npi_label.setStyle(" -fx-text-fill : #1794B2;-fx-font-size : 20px;");
			ptan_label.setStyle(" -fx-text-fill : #1794B2;-fx-font-size : 20px;");
			tax_label.setStyle(" -fx-text-fill : #1794B2;-fx-font-size : 20px;");
			dde_label.setStyle(" -fx-text-fill : #1794B2;-fx-font-size : 20px;");
			
			Label agencyName = new Label();
			Label npi = new Label();
			Label ptan = new Label();
			Label taxid = new Label();
			Label dde = new Label();
			
			agencyName.setStyle(" -fx-text-fill : black;-fx-font-size : 19px;");
			npi.setStyle(" -fx-text-fill : black;-fx-font-size : 19px;");
			ptan.setStyle(" -fx-text-fill : black;-fx-font-size : 19px;");
			taxid.setStyle(" -fx-text-fill : black;-fx-font-size : 19px;");
			dde.setStyle(" -fx-text-fill : black;-fx-font-size : 19px;");
			
			agencyName.setText(rs.getString(2));
			npi.setText(rs.getString(6));
			ptan.setText(rs.getString(7));
			taxid.setText(rs.getString(10));
			dde.setText(rs.getString(8) + "\t" + rs.getString(9));
			
			
			labelAgency.getChildren().add(agencyName_label);
			labelAgency.getChildren().add(agencyName);
			
			labelNpi.getChildren().add(npi_label);
			labelNpi.getChildren().add(npi);

			labelPtan.getChildren().add(ptan_label);
			labelPtan.getChildren().add(ptan);
			
			labelTax.getChildren().add(tax_label);
			labelTax.getChildren().add(taxid);
			
			labelDde.getChildren().add(dde_label);
			labelDde.getChildren().add(dde);
			
			vb.getChildren().add(labelAgency);
			vb.getChildren().add(labelNpi);
			vb.getChildren().add(labelPtan);
			vb.getChildren().add(labelTax);
			vb.getChildren().add(labelDde);

			vb.setStyle("-fx-border-color : #1794B2;-fx-padding : 15px");
			
			vbox.getChildren().add(vb);
			vbox.setMargin(vb, new Insets(20,20,20,20));
		}
		
		root.setStyle("-fx-background-color : white");
		tilePane.setStyle("-fx-background-color : white");
		tilePane.getChildren().add(vbox);
		root.setContent(tilePane);
		root.setFitToWidth(true);
		root.setMaxWidth(600);
		root.setMinWidth(600);
		root.setPrefWidth(600);
		Scene scene = new Scene(root, 600, primaryScreenBounds.getHeight() - 80);
		stage.setScene(scene);
		
		stage.setResizable(true);
		stage.setX(primaryScreenBounds.getWidth() - 620);  
		stage.setY(20);
		stage.setTitle("Quick info about Clients");
		stage.getIcons().add(new Image("/images/login_logo.png"));
		stage.show();
	}

	@FXML
	private void addEmployee() throws IOException {
		new Utils().open_edit_scene(root,"add_employee", "add_icon");
	}
	
	public void clickedOnClientNamesUpdates() {
		list5.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)  {
				if(!list5.getSelectionModel().isEmpty()) {

					String subitem_name = list5.getSelectionModel().getSelectedItem().toString(); 
					last_clicked_updates = subitem_name;
					switch_view.getChildren().clear();
					root.getChildren().remove(switch_view);
					agency_notes_controller agency_notes_controller = new agency_notes_controller();
					root.getChildren().add(2,switch_view);
					switch_view.getChildren().add(agency_notes_controller);

				}

			}
		});

	}


	public void clickedOnClientNames() {
		list1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)  {
				if(!list1.getSelectionModel().isEmpty()) {

					String subitem_name = list1.getSelectionModel().getSelectedItem().toString(); 
					last_clicked = subitem_name;
					switch_view.getChildren().clear();
					root.getChildren().remove(switch_view);
					client_profile_controller client_profile_controller = new client_profile_controller();
					root.getChildren().add(2,switch_view);
					switch_view.getChildren().add(client_profile_controller);

				}

			}
		});

	}

	public static void build_change_password(Button b) {
		JFXAlert alert = new JFXAlert((Stage) b.getScene().getWindow());
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setOverlayClose(false);
		JFXDialogLayout layout = new JFXDialogLayout();
		layout.setHeading(new Label("Password Change"));
		VBox txtfields = new VBox();

		Label lblError = new Label();
		lblError.setStyle("-fx-text-fill : #1794B2");
		txtfields.setMargin(lblError, new Insets(30, 0, 0, 0));

		JFXTextField existing_password = new JFXTextField();
		existing_password.setPromptText("*Old Password");
		txtfields.setMargin(existing_password, new Insets(30, 0, 0, 0));
		existing_password.setLabelFloat(true);

		JFXTextField new_password = new JFXTextField();
		new_password.setPromptText("*New Password");
		txtfields.setMargin(new_password, new Insets(50, 0, 50, 0));
		new_password.setLabelFloat(true);

		JFXTextField repeat_new_password = new JFXTextField();
		repeat_new_password.setPromptText("*Repeat Password");
		repeat_new_password.setLabelFloat(true);

		JFXButton change_pass = new JFXButton("Change");
		change_pass.setStyle("-fx-pref-width: 120px;-fx-background-color: #1794B2;-fx-text-fill: white;-fx-cursor: hand;");
		JFXButton goBack = new JFXButton("Cancel");
		goBack.setStyle("-fx-pref-width: 120px;-fx-background-color: black;-fx-text-fill: white;-fx-cursor: hand;");

		txtfields.getChildren().addAll(existing_password,new_password,repeat_new_password,change_pass,lblError);

		layout.setBody(txtfields);

		change_pass.setOnAction(e -> {
			if(Utils.check_empty_text(existing_password.getText().toString(),new_password.getText().toString(),repeat_new_password.getText().toString())) {
				lblError.setText("**Fill out all the fields !");
				return;
			}
			try {
				if(!ControlDAO.getControlDao().getLoginDao().get_pass().equals(existing_password.getText().toString())) {
					lblError.setText("**Old Password is not correct!"); 
					return;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (!new_password.getText().toString().equals(repeat_new_password.getText().toString())) {
				lblError.setText("**Passwords don't match !"); 
				return;
			}
			users u = new users();
			u.setPassword(new_password.getText().toString());
			try {
				ControlDAO.getControlDao().getLoginDao().changePassword(u);
				//insert logs
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			alert.hideWithAnimation();
		});

		goBack.setOnAction(e -> {
			alert.hideWithAnimation();
		});
		layout.setActions(goBack,change_pass);
		alert.setContent(layout);
		alert.show();
	}

	private void subitems_clicked(ListView<?> list_view) {

		list_view.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)  {
				if(!list_view.getSelectionModel().isEmpty()) {

					String subitem_name = list_view.getSelectionModel().getSelectedItem().toString(); 
					if(subitem_name.equals(last_clicked))
						return;

					switch (subitem_name) {

					case global.b2:
						last_clicked = global.b2;
						Controllers.getClients(switch_view);
						break;
					case global.b3:
						last_clicked = global.b3;
						try {
							new Utils().openSceneNoResizable("add_agency" , "Add New Agency", "/images/add_red.png");
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case global.b9:
						last_clicked = global.b9;
						try {
							new Utils().openSceneNoResizable("edit_employee" , "Edit Profile", "/images/add_red.png");
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case global.b4:
						last_clicked = global.b4;
						Controllers.getBillingInstructions(switch_view);
						break;
					case global.b10:
						last_clicked = global.b10;
						try {
							new Utils().open_viewButton("dashboard", changePass, "/images/login_logo.png", "Dashboard");
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case global.b8:
						last_clicked = global.b8;
						Controllers.getReports(switch_view);
						break;
					case global.b7:
						last_clicked = global.b7;
						Controllers.getClientCommNotes(switch_view);
						break;
					case global.b11:
						last_clicked = global.b11;
						Controllers.getAccInstr(switch_view);
						break;
					case global.b12:
						last_clicked = global.b12;
						Controllers.getInactiveClients(switch_view);
						break;
					case global.b13:
						//last_clicked = global.b13;
						Controllers.getQuickInfo(switch_view);
						break;
					default:
						break;
					}
				}

			}
		});
	}


	public void titled_pane_icon(TitledPane p,FontAwesomeIcon icon) {

		FontAwesomeIconView f = new FontAwesomeIconView(icon);
		f.setTranslateX(-14); 
		f.setFocusTraversable(true);
		p.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (newPropertyValue) 
					f.setStyle(" -fx-fill: white;");
				else
					f.setStyle(" -fx-fill: #cbcbcb;");
			}
		});

		p.setGraphic(f); 

	}

	private void titled_pane_listeners(TitledPane... t) {
		for (int i=0; i<t.length; i++) {
			t[i].expandedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean a, Boolean newValue) {
					titled_pane_expanded();
				}
			});
		}
	}
	@SafeVarargs
	public static void title_pane_clear(ListView<String>... l) {
		for(int i=0;i<l.length;i++) 
			l[i].getSelectionModel().clearSelection();
	}

	private void titled_pane_expanded() {
		title_pane_clear(list1,list2,list5,list6,list7);
	}


	@FXML
	private void logout() {
		Platform.exit();
	}

	@FXML
	private void ndryshoPass() {
		build_change_password(changePass);
	}
}


