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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Address;
import model.LibraryMember;
import util.LibrarySystemException;
import util.RandomIdGenarator;
import util.RuleSetFactory;
import business.MemberBusiness;
import controller.Dialog;

public class AllMemberController implements Initializable {
	private static final Logger logger = Logger.getLogger(AllMemberController.class.getName());
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
		customTextField.numaricTextfield(txtZip);
		customTextField.numaricTextfield(txtPhoneNumber);
	}

	@FXML
	private void onActionSearch(ActionEvent event) {
		String searchString = txtSearch.getText();
		MemberBusiness memberBusiness = new MemberBusiness();
		members = FXCollections.observableArrayList(memberBusiness.search(searchString));
		tblMember.setItems(members);
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
	private void onClickUpdateButton(ActionEvent event) {
		String memberId = txtMemberId.getText();
		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		String telephone = txtPhoneNumber.getText();

		String street = txtStreet.getText();
		String city = txtCity.getText();
		String state = txtState.getText();
		String zip = txtZip.getText();
		Address address = new Address(street, city, state, zip);
		if (checkAllRule()) {
			if ("update".equals(btnUpdate.getText().toLowerCase())) {
				updateMember(memberId, firstName, lastName, telephone, address);
			} else {
				addNewMember(memberId, firstName, lastName, telephone, address);
			}
		}
	}

	private void updateMember(String memberId, String firstName, String lastName, String telephone, Address address) {
		try {
			MemberBusiness memberBusiness = new MemberBusiness();
			memberBusiness.updateMemberInfo(memberId, firstName, lastName, telephone, address);
			int selectedIndex = tblMember.getSelectionModel().getSelectedIndex();
			showDetails();
			tblMember.requestFocus();
			tblMember.getSelectionModel().selectIndices(selectedIndex);
			tblMember.getFocusModel().focus(selectedIndex);
			return;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	private void addNewMember(String memberId, String firstName, String lastName, String telephone, Address address) {
		try {
			MemberBusiness memberBusiness = new MemberBusiness();
			memberBusiness.addNewMember(memberId, firstName, lastName, telephone, address);
			showDetails();
			LibraryMember member = memberBusiness.findBy(memberId);
			tblMember.getSelectionModel().select(member);
			return;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	public void setselectedView() {
		clearAll();
		btnUpdate.setDisable(false);
		btnUpdate.setText("Update");
		LibraryMember member = tblMember.getSelectionModel().getSelectedItem();
		if (member != null) {
			Address address = member.getAddress();
			txtMemberId.setText(member.getMemberId());
			txtFirstName.setText(member.getFirstName());
			txtLastName.setText(member.getLastName());
			txtPhoneNumber.setText(member.getTelephone());

			txtStreet.setText(address.getStreet());
			txtCity.setText(address.getCity());
			txtState.setText(address.getState());
			txtZip.setText(address.getZip());
		}
	}

	public void showDetails() {
		btnUpdate.setText("Update");
		btnUpdate.setDisable(true);
		MemberBusiness memberBusiness = new MemberBusiness();
		members = FXCollections.observableArrayList(memberBusiness.getAll());
		tblMember.setItems(members);
		clmMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
		clmFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		clmLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	}

	public void generateCreateNewMeber() {
		clearAll();
		btnUpdate.setDisable(false);
		btnUpdate.setText("Save");
		String memberId = RandomIdGenarator.randomString(6, 6);
		txtMemberId.setText(memberId);
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

	private boolean checkAllRule() {
		try {
			String memberId = txtMemberId.getText();
			String firstName = txtFirstName.getText();
			String lastName = txtLastName.getText();
			String telephone = txtPhoneNumber.getText();

			String street = txtStreet.getText();
			String city = txtCity.getText();
			String state = txtState.getText();
			String zip = txtZip.getText();

			if (isAnyEmpty(firstName, lastName, telephone, street, city, state, zip))
				throw new LibrarySystemException("All fields must be nonempty");

			if (!RuleSetFactory.isNumeric(memberId))
				throw new LibrarySystemException("Member Id field must be numeric");
			if (!RuleSetFactory.isAllLetters(firstName))
				throw new LibrarySystemException("First Name field must be all letters and no space");
			if (!RuleSetFactory.isAllLetters(lastName))
				throw new LibrarySystemException("Last Name field must be all letters and no space");
			if (!RuleSetFactory.isNumeric(zip))
				throw new LibrarySystemException("Zip code field must be numeric");
			if (!RuleSetFactory.isExactLength(zip, 5))
				throw new LibrarySystemException("Zip field must be exactly 5 digits");
			if (!RuleSetFactory.isExactLength(state, 2))
				throw new LibrarySystemException("State field must be exactly 2 digits");
			if (!RuleSetFactory.isExactLength(telephone, 10))
				throw new LibrarySystemException("Phone Number field must be exactly 10 digits");
			if (!RuleSetFactory.isAllCapitals(state))
				throw new LibrarySystemException("State field must be in the range A-Z");
		} catch (LibrarySystemException e) {
			Dialog.showWarningDialog("Error", null, e.getMessage());
			return false;
		}
		return true;
	}

	private boolean isAnyEmpty(String firstName, String lastName, String telephone, String street, String city,
			String state, String zip) {
		return firstName.isEmpty() || lastName.isEmpty() || telephone.isEmpty() || state.isEmpty() || street.isEmpty()
				|| city.isEmpty() || zip.isEmpty();
	}
}