package utils;

import java.io.IOException;
import java.time.LocalDate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Utils {

	public static String key = "JohnJohnJohnJohn",initVector = "RandomInitVector"; 
	public static int idP = 0, i=0, lastAgencyId = 0;
	public static String user_group = "",username = "", passwordDecrypted = "";
	public static boolean isBonus = false;
	public static StringProperty full_name = new SimpleStringProperty("");
	
	public static void alert_fshirje(JFXAlert alert,String entity,Button konfirmo,JFXButton anullo) {
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setOverlayClose(false);
		JFXDialogLayout layout = new JFXDialogLayout();
		ImageView img = new ImageView(new Image("/images/icons-confirm.png"));
		layout.setHeading(img);
		layout.setBody(new Label("Do you want to delete "+entity));
		layout.setActions(konfirmo,anullo);
		alert.setContent(layout);
		alert.show();
	}
	
	public static void alert_add_to_list(JFXAlert alert,String entity,Button konfirmo,JFXButton anullo) {
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setOverlayClose(false);
		JFXDialogLayout layout = new JFXDialogLayout();
		ImageView img = new ImageView(new Image("/images/list.png"));
		layout.setHeading(img);
		layout.setBody(new Label("Do you want to add "+entity));
		layout.setActions(konfirmo,anullo);
		alert.setContent(layout);
		alert.show();
	}
	
	public static void style_delete_button(JFXButton btn_delete) {
		btn_delete.setMaxWidth(20);
		btn_delete.setCursor(Cursor.HAND);
		btn_delete.getStyleClass().add("delete_user");
	}
	
	public static void styleAddButton(JFXButton btn_delete) {
		btn_delete.setMaxWidth(20);
		btn_delete.setCursor(Cursor.HAND);
		btn_delete.getStyleClass().add("add_to_list");
	}


	public static void make_textfield_decimal(JFXTextField txt) {
		txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}([,.]\\d{0,3})?")) {
                	txt.setText(oldValue);
                }
            }
        });
	}
	public void open_edit_scene(VBox vbox,String view_name, String icon) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+view_name+".fxml")); 
		Parent root=(Parent)loader.load();
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		Stage stage = new Stage();
		stage.initStyle(StageStyle.TRANSPARENT); 
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}
	
	public void open_edit_scene_view_data(VBox vbox,String view_name, String icon) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+view_name+".fxml")); 
		Parent root=(Parent)loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}
	
	public static boolean check_empty_text(String... strings) {
		for(String s : strings)
			if(s == null || s.isEmpty())
				return true;

		return false;
		
	}
	

	public static void addTextLimiterPassword(final JFXPasswordField tf, final int maxLength) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
				if (tf.getText().length() > maxLength) {
					String s = tf.getText().substring(0, maxLength);
					tf.setText(s);
				}

			}
		});
	}

	public static void addTextLimiterText(final JFXTextField tf, final int maxLength) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
				if (tf.getText().length() > maxLength) {
					String s = tf.getText().substring(0, maxLength);
					tf.setText(s);
				}

			}
		});
	}

	public static void refresh_focus_table(TableView<?> tbl) {
		tbl.refresh();
		tbl.requestFocus();
	}

	public static void setLocalDateNow(JFXDatePicker... datePicker) {
		for(i=0;i<datePicker.length;i++) {
			datePicker[i].setValue(LocalDate.now());
		}
	}

	public static void close_stage(JFXButton button) {
		Stage stage = (Stage) button.getScene().getWindow();
		stage.close();
	}

	public void openScene(String view_name) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+view_name+".fxml")); 
		Parent root=(Parent)loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}


	public void openSceneNoResizable(String view_name, String title, String icon_path) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+view_name+".fxml")); 
		Parent root=(Parent)loader.load();
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		Stage stage = new Stage();
		stage.initStyle(StageStyle.TRANSPARENT); 
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.setMaximized(false);
		stage.setResizable(false);
		stage.getIcons().add(new Image(icon_path));
		stage.setTitle(title);
		stage.showAndWait();
	}
	
	public void openSceneViewNotes(String view_name, String title, String icon_path) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+view_name+".fxml")); 
		Parent root=(Parent)loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(true);
		stage.getIcons().add(new Image(icon_path));
		stage.setTitle(title);
		stage.showAndWait();
	}

	public static boolean check_empty_combobox(JFXComboBox<?>... combos ) {

		for (JFXComboBox<?> s : combos)
			if (s.getValue() == null || s.getValue() == "") 
				return true;

		return false;
	}


	public static boolean check_checkbox(JFXCheckBox c) {
		if (!c.isSelected()) 
			return true;

		return false;
	}

	public static void alerti(String title, String text, AlertType type) {
		Alert alert = new Alert (type,text);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	public static boolean check_datePicker(JFXDatePicker d) {
		if (d.getValue() == null) 
			return true;

		return false;
	}

	public static String encrypt(String key, String initVector, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static String decrypt(String key, String initVector, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static void make_textfield_integer(TextField txt) {
		txt.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,7}?")) {
					txt.setText(oldValue);
				}
			}
		});
	}
	
	public static void make_jfxtextfield_integer(JFXTextField txt) {
		txt.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,7}?")) {
					txt.setText(oldValue);
				}
			}
		});
	}

	public void open_viewButton(String view_name, Button btn, String icon_path, String title) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+ view_name +".fxml")); 
		Parent root=(Parent)loader.load();
		Scene scene = new Scene(root);
		Stage old_stage = (Stage)btn.getScene().getWindow();
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(true);
		stage.setMaximized(false);
		old_stage.close();
		stage.getIcons().add(new Image(icon_path));
		stage.setTitle(title);
		stage.setOnCloseRequest( event -> {
			System.exit(0);
		});
		stage.show();
	}
	
	public void open_viewLabel(String view_name, Label lbl, String icon_path, String title) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+ view_name +".fxml")); 
		Parent root=(Parent)loader.load();
		Scene scene = new Scene(root);
		Stage old_stage = (Stage)lbl.getScene().getWindow();
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(true);
		stage.setMaximized(false);
		old_stage.close();
		stage.getIcons().add(new Image(icon_path));
		stage.setTitle(title);
		stage.setOnCloseRequest( event -> {
			System.exit(0);
		});
		stage.show();
	}

	public void openViewModal(String view_name, Button btn, String icon_path, String title) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+ view_name +".fxml")); 
		Parent root=(Parent)loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(true);
		stage.setMaximized(true);
		stage.setTitle(title);
		stage.getIcons().add(new Image(icon_path));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		stage.setTitle(title);
	}
}
