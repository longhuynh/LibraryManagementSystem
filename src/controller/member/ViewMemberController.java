package controller.member;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import custom.*;

import java.awt.Component;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Address;
import model.LibraryMember;
import model.Role;
import model.User;
import util.LibrarySystemException;
import util.RandomIdGenarator;
import util.RuleSetFactory;
import business.MemberBusiness;
import business.UserBusineess;
import controller.ApplicationController;
import controller.Dialog;
import controller.LoginController;

public class ViewMemberController implements Initializable {
	private static final Logger logger = Logger.getLogger(ViewMemberController.class.getName());
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
		checkAllRule();
		if("update".equals(btnUpdate.getText().toLowerCase())){
			updateMember(memberId, firstName, lastName, telephone, address);
		}
		else{	
			addNewMember(memberId, firstName, lastName, telephone, address);
		}	
		
	}

	private void updateMember(String memberId, String firstName, String lastName, String telephone, Address address) {
		try {
			
			MemberBusiness memberBusiness = new MemberBusiness();
			memberBusiness.updateMemberInfo(memberId, firstName, lastName, telephone, address);
			int selectedIndex = tblMember.getSelectionModel().getSelectedIndex();
			showDetails();
			tblMember.getSelectionModel().selectIndices(selectedIndex);
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
			LibraryMember member =memberBusiness.findBy(memberId); 
			tblMember.getSelectionModel().select(member);
			return;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	public void setselectedView() {
		clearAll();
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
		MemberBusiness memberBusiness = new MemberBusiness();
		members = FXCollections.observableArrayList(memberBusiness.getAll());
		tblMember.setItems(members);
		clmMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
		clmFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		clmLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	}
	
	public void createNewMeber() {
		clearAll();
		btnUpdate.setText("Save");
		String memberId = RandomIdGenarator.randomString();
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
	
	private void checkAllRule() {
		try {
			applyRules();
		} catch (LibrarySystemException e) {
			Dialog.showWarningDialog("Error", null, e.getMessage());			
		}
	}
	
	public void applyRules() throws LibrarySystemException {
		String memberId = txtMemberId.getText();
		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		String telephone = txtPhoneNumber.getText();

		String street = txtStreet.getText();
		String city = txtCity.getText();
		String state = txtState.getText();
		String zip = txtZip.getText();

		if (isAnyEmpty(firstName, lastName, telephone, street,city,state, zip ))
			throw new LibrarySystemException("All fields must be nonempty");

		if (!RuleSetFactory.isNumeric(memberId))
			throw new LibrarySystemException("Id field must be numeric");
		if (!RuleSetFactory.isAllLetters(firstName) || !RuleSetFactory.isAllLetters(lastName))
			throw new LibrarySystemException("First Name field must be all letters");
		if (!RuleSetFactory.isNumeric(zip))
			throw new LibrarySystemException("Zip field must be numeric");
		if (!RuleSetFactory.isExactLength(zip, 5))
			throw new LibrarySystemException("Zip field must be exactly 5 digits");
		if (!RuleSetFactory.isExactLength(state, 2))
			throw new LibrarySystemException("State field must be exactly 2 digits");

		if (!RuleSetFactory.isAllCapitals(state))
			throw new LibrarySystemException("State field must be in the range A-Z");		
	}

	public boolean isAnyEmpty(String firstName, String lastName, String telephone, String street, String city, String state, String zip) {	
		return firstName.isEmpty() || lastName.isEmpty() ||telephone.isEmpty()
				|| state.isEmpty() || street.isEmpty() 
				|| city.isEmpty() || zip.isEmpty();
	}
}