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
		ViewMemberController controller = fXMLLoader.getController();

		controller.showDetails();
		controller.btnClearFirstName.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearLastName.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearPhoneNumber.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearStreet.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearCity.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearState.getStylesheets().add("/style/btnOnText.css");
		controller.btnClearZip.getStylesheets().add("/style/btnOnText.css");
		AnchorPane acPane = fXMLLoader.getRoot();
		spMemberContent.getChildren().clear();
		spMemberContent.getChildren().add(acPane);
	}

	@FXML
	private void onClickAddMemberButton(ActionEvent event) throws IOException {
		lblView.setText("Add member");

		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.load(getClass().getResource("/view/member/AddMember.fxml").openStream());
		AnchorPane pane = fXMLLoader.getRoot();
		spMemberContent.getChildren().clear();
		spMemberContent.getChildren().add(pane);
	}
}
