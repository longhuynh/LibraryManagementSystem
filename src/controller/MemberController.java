package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.member.AllMemberController;
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

public class MemberController implements Initializable {

	@FXML
	private Label lblView;
	@FXML
	private MenuItem btnViewMember;
	@FXML
	private MenuItem btnAddMember;

	@FXML
	private StackPane spMemberContent;
	@FXML
	public BorderPane bpContent;

	@FXML
	private ImageView imgMemberIcon;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		imgMemberIcon.setImage(new Image("/icon/addmember.png"));
	}

	@FXML
	public void onClickViewMemberButton(ActionEvent event) throws IOException {
		lblView.setText("Members");
		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.load(getClass().getResource("/view/member/ViewAll.fxml").openStream());
		AllMemberController controller = fXMLLoader.getController();
		generateControls(controller);
		AnchorPane acPane = fXMLLoader.getRoot();
		spMemberContent.getChildren().clear();
		spMemberContent.getChildren().add(acPane);
	}

	private void generateControls(AllMemberController controller) {
		controller.showDetails();
		controller.btnClearFirstName.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearLastName.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearPhoneNumber.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearStreet.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearCity.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearState.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearZip.getStylesheets().add("/style/btnOnText.css");
	}

	@FXML
	private void onClickAddMemberButton(ActionEvent event) throws IOException {
		lblView.setText("Add member");
		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.load(getClass().getResource("/view/member/ViewAll.fxml").openStream());
		AllMemberController controller = fXMLLoader.getController();
		generateControls(controller);
		controller.generateCreateNewMeber();
		AnchorPane acPane = fXMLLoader.getRoot();
		spMemberContent.getChildren().clear();
		spMemberContent.getChildren().add(acPane);
	}
}
