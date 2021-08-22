package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.Utils;

public class login implements Initializable {

	@FXML private JFXTextField txtUsername;
	@FXML private JFXPasswordField txtPassword;
	@FXML private Label lblError;
	@FXML private Button btnLogin;

	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	@FXML
	private void checkLogin() throws IOException {
		String user = txtUsername.getText();
		String passwd = Utils.encrypt(Utils.key, Utils.initVector, txtPassword.getText());

		if(user.isEmpty() || txtPassword.getText().isEmpty() ) {
			lblError.setText("*Please fill all the fields !");	
			return;
		}

		if(ControlDAO.getControlDao().getUserDao().check_user_and_pass(user,passwd))
			new Utils().open_viewButton("dashboard", btnLogin, "/images/login_logo.png", "Dashboard");
		else {
			lblError.setText("Incorrect User or Password !");
			return;
		}
	}

	
}
