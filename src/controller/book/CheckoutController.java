package controller.book;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import model.CheckoutRecord;
import model.CheckoutRecordEntry;
import model.CheckoutRecordTableEntry;
import model.LibraryMember;
import util.LibrarySystemException;

public class CheckoutController implements Initializable {
	private static final Logger logger = Logger.getLogger(CheckoutController.class.getName());

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
	private TableView<CheckoutRecordTableEntry> tblCheckoutRecord;	
	@FXML
	private TableColumn<Object, Object> clmTitle;
	@FXML
	private TableColumn<Object, Object> clmIsbn;
	@FXML
	private TableColumn<Object, Object> clmDueDate;
	@FXML
	private TableColumn<Object, Object> clmCheckoutDate;
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
		
		try {
			showCheckoutEntries();			
		
		}catch (LibrarySystemException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	private void showCheckoutEntries() throws LibrarySystemException {
		ObservableList<CheckoutRecordTableEntry> checkoutRecords = FXCollections.observableArrayList();
		BookBusiness bookBusiness = new BookBusiness();		
		CheckoutRecord checkoutRecord = bookBusiness.getCheckoutRecordByMemberId(memberId);
		for (CheckoutRecordEntry entry : checkoutRecord.getCheckoutRecordEntries()) {
			System.out.println(entry.toString());
			checkoutRecords.add(new CheckoutRecordTableEntry(entry));
		}

		tblCheckoutRecord.getItems().clear();
		tblCheckoutRecord.setItems(checkoutRecords);
		for (CheckoutRecordTableEntry checkoutRecordTableEntry : checkoutRecords) {
			System.out.println(checkoutRecordTableEntry.toString());
		}
		clmIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		clmTitle.setCellValueFactory(new PropertyValueFactory<>("title"));		
		clmDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		clmCheckoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
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
		
		if("ok".equals(btnOK.getText().toLowerCase())){
			btnOK.setText("Close");
		LibraryMember member = tblMember.getSelectionModel().getSelectedItem();
		BookBusiness bookBusiness = new BookBusiness();
		checkAllRule();
		try {
			bookBusiness.checkoutBook(member.getMemberId(), txtIsbn.getText());
			Dialog.showInformationDialog("Error", null, "Checkout book successful.");
		showCheckoutEntries();
			
		} catch (LibrarySystemException e) {
			Dialog.showErrorDialog("Error", null, e.getMessage());
		}}
		else{
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
			AllBookController controller = new AllBookController();
			controller.viewDetails();
		}
	}
	
	private void checkAllRule() {
		try {
			if (tblMember.getSelectionModel().getSelectedItems().isEmpty())
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
