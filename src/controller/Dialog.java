package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Dialog {
	public static void show(AlertType alertType, String title, String headerText, String contentText){
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	public static void showInformationDialog(String title, String headerText, String contentText){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	public static void showWarningDialog(String title, String headerText, String contentText){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	public static void showErrorDialog(String title, String headerText, String contentText){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}	
}
