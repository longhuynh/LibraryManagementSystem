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

	CustomTextField customTextField = new CustomTextField();
	@FXML
	private TextField txtCopyNumber;
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
		customTextField.clearTextFieldByButton(txtCopyNumber, btnClearCopyNumber);
		customTextField.numaricTextfield(txtCopyNumber);
	}

	@FXML
	private void onClickButtonOK(ActionEvent event) {
		checkAllRule();
		BookBusiness bookBusiness = new BookBusiness();
		try {
			int copyNumber = Integer.parseInt(txtCopyNumber.getText());
			for(int i = 0; i < copyNumber; i++){
				bookBusiness.addBookCopy(txtIsbn.getText());
			}
			
			Stage stage = (Stage) btnOK.getScene().getWindow();
			stage.close();
			AllBookController controller = new AllBookController();
			controller.viewDetails();
		} catch (LibrarySystemException e) {
			Dialog.showErrorDialog("Error", null, e.getMessage());
		}
	}
	
	private void checkAllRule() {
		try {
			if (txtCopyNumber.getText().isEmpty())
				throw new LibrarySystemException("All fields must be nonempty");
		} catch (LibrarySystemException e) {
			Dialog.showWarningDialog("Error", null, e.getMessage());
		}
	}
	
	@FXML
	private void onClickButtonClose(ActionEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}

	public void viewDetails(Book book) {
		txtIsbn.setText(book.getIsbn());
	}
}
