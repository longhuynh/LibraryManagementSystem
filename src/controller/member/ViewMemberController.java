package controller.member;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import custom.*;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.LibraryMember;
import model.Role;

import business.MemberBusiness;

public class ViewMemberController implements Initializable {
	public ObservableList<LibraryMember> members = FXCollections.observableArrayList();
	CustomPasswordField customPasswordField = new CustomPasswordField();
	CustomTextField customTextField = new CustomTextField();

	@FXML
	private TextField txtMemberId;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextField txtSearch;
	@FXML
	private ComboBox<Role> cboRole;

	@FXML
	private TextField txtStreet;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtState;
	@FXML
	private TextField txtZip;

	@FXML
	private Button btnUpdate;
	@FXML
	public Button btnClearFirstName;
	@FXML
	public Button btnClearLastName;
	@FXML
	public Button btnClearPhoneNumber;
	@FXML
	public Button btnClearStreet;
	@FXML
	public Button btnClearCity;
	@FXML
	public Button btnClearState;
	@FXML
	public Button btnClearZip;

	@FXML
	private TableView<LibraryMember> tblMember;
	@FXML
	private TableColumn<Object, Object> clmMemberId;
	@FXML
	private TableColumn<Object, Object> clmFirstName;
	@FXML
	private TableColumn<Object, Object> clmLastName;
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		customTextField.clearTextFieldByButton(txtFirstName, btnClearFirstName);
		customTextField.clearTextFieldByButton(txtLastName, btnClearLastName);
		customTextField.clearTextFieldByButton(txtPhoneNumber, btnClearPhoneNumber);
		customTextField.clearTextFieldByButton(txtCity, btnClearCity);
		customTextField.clearTextFieldByButton(txtStreet, btnClearStreet);
		customTextField.clearTextFieldByButton(txtState, btnClearState);
		customTextField.clearTextFieldByButton(txtZip, btnClearZip);

		cboRole.getItems().add(Role.LIBRARIAN);
		cboRole.getItems().add(Role.ADMIN);
		cboRole.getItems().add(Role.BOTH);
		cboRole.getSelectionModel().select(0);
		// customTextField.numaricTextfield(txtStreet);

	}

	@FXML
	private void onActionSearch(ActionEvent event) {

	}

	@FXML
	private void onKeyReleasedTableMember(KeyEvent event) {
		if (event.getCode().equals(KeyCode.UP)) {
			setselectedView();
		} else if (event.getCode().equals(KeyCode.DOWN)) {
			setselectedView();
		}
	}

	@FXML
	public void onClickTableMember(Event event) {
		setselectedView();
	}

	@FXML
	private void onClickUpdateButton(ActionEvent event) throws FileNotFoundException {

	}

	public void setselectedView() {
		clearAll();
		LibraryMember member = tblMember.getSelectionModel().getSelectedItem();
		if (member != null) {
			txtFirstName.setText(member.getFirstName());
		}
	}

	public void showDetails() {
		MemberBusiness memberBusiness = new MemberBusiness();
		members = FXCollections.observableArrayList(memberBusiness.getAll());
		tblMember.setItems(members);
		clmMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
		clmFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		clmLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	}

	private void clearAll() {
		txtMemberId.clear();
		txtFirstName.clear();
		txtLastName.clear();
		txtPhoneNumber.clear();
		txtStreet.clear();
		txtLastName.clear();
		txtCity.clear();
		
	}
}
