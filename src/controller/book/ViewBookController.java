package controller.book;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Book;

/**
 *
 * @author Long Huynh
 */
public class ViewBookController implements Initializable {

	@FXML
	private AnchorPane acCustomerMainContent;
	@FXML
	private TextField txtSearch;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private TableView<Book> tblCustomer;
	@FXML
	private TableColumn<Object, Object> tblClmName;
	@FXML
	private TableColumn<Object, Object> tblClmContNo;
	@FXML
	private TableColumn<Object, Object> tblClmAddres;
	@FXML
	private TableColumn<Object, Object> tblClmDate;

	@FXML
	private TableColumn<Object, Object> tblClmTotalBuy;

	@FXML
	private Button btnRefresh;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void tfSearchOnKeyReleased(Event event) {

	}

	@FXML
	private void btnAddOnAction(ActionEvent event) {

	}

	@FXML
	private void btnUpdateOnAction(ActionEvent event) {

	}

	@FXML
	private void btnDeleteOnAction(ActionEvent event) {

	}

	public void viewDetails() {

	}

	public void selectedView() {

	}

	@FXML
	private void tblCustomerOnClick(MouseEvent event) {

	}

	@FXML
	private void btnRefreshOnAction(ActionEvent event) {

	}

}
