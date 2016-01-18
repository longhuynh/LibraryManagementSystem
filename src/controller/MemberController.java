package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.member.ViewMemberController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
//import media.UserNameMedia;

/**
 *
 * @author Long Huynh
 */
public class MemberController implements Initializable {

	@FXML
	private Label lblView;
	@FXML
	private MenuItem btnViewEmployee;
	@FXML
	private MenuItem btnAddEmployee;

	@FXML
	private ImageView ivEmployeIcon;

	@FXML
	private StackPane spEmployeContent;
	@FXML
	public BorderPane bpContent;

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ivEmployeIcon.setImage(new Image("/icon/addmember.png"));
	}

	@FXML
	public void btnViewEmployeeOnAction(ActionEvent event) throws IOException {
		lblView.setText("Members");

		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.load(getClass().getResource("/view/member/ViewAll.fxml").openStream());

		ViewMemberController controller = fXMLLoader.getController();

		controller.showDetails();
		controller.btnClearFirstName.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearLastName.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearPhoneNumber.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearStreet.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearCity.getStylesheets().add("/style/btnOnText.css");
		AnchorPane acPane = fXMLLoader.getRoot();
		spEmployeContent.getChildren().clear();
		spEmployeContent.getChildren().add(acPane);
	}

	@FXML
	private void btnAddEmployeeOnClick(ActionEvent event) throws IOException {
		lblView.setText("Add member");

		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.load(getClass().getResource("/view/application/employe/AddEmploye.fxml").openStream());

		// AddEmployeController addEmployeController =
		// fXMLLoader.getController();
		// addEmployeController.setNameMedia(nameMedia);
		// addEmployeController.btnClrEmail.getStylesheets().add("/style/btnOnText.css");
		// addEmployeController.btnClrFullName.getStylesheets().add("/style/btnOnText.css");
		// addEmployeController.btnClrPassword.getStylesheets().add("/style/btnOnText.css");
		// addEmployeController.btnClrPhone.getStylesheets().add("/style/btnOnText.css");
		// addEmployeController.btnClrSalary.getStylesheets().add("/style/btnOnText.css");
		// addEmployeController.btnClrUsrName.getStylesheets().add("/style/btnOnText.css");

		AnchorPane acPane = fXMLLoader.getRoot();

		spEmployeContent.getChildren().clear();

		spEmployeContent.getChildren().add(acPane);

	}
}
