package controller.book;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import business.BookBusiness;
import controller.Dialog;
import controller.member.AllMemberController;
import custom.CustomTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Book;
import model.LibraryMember;
import util.LibrarySystemException;

public class CopyController implements Initializable {
	private static final Logger logger = Logger.getLogger(AllMemberController.class.getName());
	@FXML
	private TextField txtTitle;
	@FXML
	private TextField txtIsbn;
	@FXML
	private Button btnClose;
	@FXML
	private Button btnOK;	
	@FXML
	private Button btnClearCopyNumber;

	ObservableList<LibraryMember> members = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}

	@FXML
	private void onClickButtonOK(ActionEvent event) {		
		try {
			BookBusiness bookBusiness = new BookBusiness();
			bookBusiness.addBookCopy(txtIsbn.getText());			
			Stage stage = (Stage) btnOK.getScene().getWindow();
			stage.close();
			Dialog.showInformationDialog("Copy book sucessful", null, "Please, click on refresh button to see new data");
		} catch (LibrarySystemException e) {
			Dialog.showErrorDialog("Error", null, e.getMessage());
		}
	}
	
	@FXML
	private void onClickButtonClose(ActionEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}

	public void viewDetails(Book book) {
		txtIsbn.setText(book.getIsbn());
		txtTitle.setText(book.getTitle());
	}
}
