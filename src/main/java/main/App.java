package main;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	public Parent root;
	
    @Override
    public void start(Stage primaryStage) throws IOException {       
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
    	root = loader.load();
    	
    	Scene scene = new Scene(root);
    	primaryStage.setResizable(true);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Login");
    	primaryStage.getIcons().add(new Image("/images/login_logo.png"));
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}