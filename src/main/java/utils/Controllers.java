package utils;

import controller.*;
import javafx.scene.layout.VBox;

public class Controllers {

	private static clients_controller clients_controller;
	private static billing_instructions_controller billing_instructions_controller;
	private static client_profile_controller client_profile_controller;
	private static reports_controller reports_controller;
	private static clients_comm_notes_controller clients_comm_notes_controller;
	private static accInstController accInstController;
	private static inactive_clients_controller inactive_clients_controller;
	private static quickInfoController quickInfoController;
	
	private Controllers() {}

	public static void getClients(VBox box) {
		clients_controller = new clients_controller();
		config(box, clients_controller);
	}
	
	public static void getBillingInstructions(VBox box) {
		billing_instructions_controller = new billing_instructions_controller();
		config(box, billing_instructions_controller);
	}
	
	public static void getClientProfile(VBox box) {
		client_profile_controller = new client_profile_controller();
		config(box, client_profile_controller);
	}
	
	public static void getReports(VBox box) {
		reports_controller = new reports_controller();
		config(box, reports_controller);
	}
	
	public static void getClientCommNotes(VBox box) {
		clients_comm_notes_controller = new clients_comm_notes_controller();
		config(box, clients_comm_notes_controller);
	}
	
	public static void getAccInstr(VBox box) {
		accInstController = new accInstController();
		config(box, accInstController);
	}
	
	public static void getQuickInfo(VBox box) {
		quickInfoController = new quickInfoController();
		config(box, quickInfoController);
	}

	public static void getInactiveClients(VBox box) {
		inactive_clients_controller = new inactive_clients_controller();
		config(box, inactive_clients_controller);
	}
	
	public static void config(VBox box, VBox content) {
		box.getChildren().clear();
		box.getChildren().add(content);
	}


}
