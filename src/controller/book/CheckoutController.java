package controller.book;

import java.net.URL;
import java.util.ResourceBundle;

import business.BookBusiness;
import business.MemberBusiness;
import controller.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Book;
import model.LibraryMember;
import util.LibrarySystemException;

public class CheckoutController implements Initializable {
	private String memberId;
	@FXML
	private MenuButton mbtMember;
	@FXML
	private TableView<LibraryMember> tblMember;

	@FXML
	private TableColumn<Object, Object> clmMemberId;
	@FXML
	private TableColumn<Object, Object> clmFirstName;
	@FXML
	private TableColumn<Object, Object> clmLastName;
	@FXML
	private TextField txtSearchMember;
	@FXML
	private TextField txtIsbn;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnOK;

	ObservableList<LibraryMember> members = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void onClickTableMember(MouseEvent event) {
		mbtMember.setText(tblMember.getSelectionModel().getSelectedItem().getFullName());
		memberId = tblMember.getSelectionModel().getSelectedItem().getMemberId();
		System.out.println(memberId);

	}

	@FXML
	private void onClickedMenuButtonMember(MouseEvent event) {
		MemberBusiness memberBusiness = new MemberBusiness();
		members = FXCollections.observableArrayList(memberBusiness.getAll());
		tblMember.setItems(members);
		clmMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
		clmFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		clmLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	}

	@FXML
	private void onClickButtonOK(ActionEvent event) {
		LibraryMember member = tblMember.getSelectionModel().getSelectedItem();
		BookBusiness bookBusiness = new BookBusiness();
		try {
			bookBusiness.checkoutBook(member.getMemberId(), txtIsbn.getText());
			Stage stage = (Stage) btnOK.getScene().getWindow();
			stage.close();
		} catch (LibrarySystemException e) {
			Dialog.showErrorDialog("Error", null, e.getMessage());
		}
	}

	@FXML
	private void onClickButtonClose(ActionEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void onKeyReleasedTextFieldSearch(KeyEvent event) {
		String searchString = txtSearchMember.getText();
		MemberBusiness memberBusiness = new MemberBusiness();
		members = FXCollections.observableArrayList(memberBusiness.search(searchString));
		tblMember.setItems(members);
	}

	public void viewDetails(Book book) {
		txtIsbn.setText(book.getIsbn());
	}
}
