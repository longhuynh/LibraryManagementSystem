package controller.book;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.BookBusiness;
import business.MemberBusiness;
import controller.Dialog;
import controller.LoginController;

import controller.member.AllMemberController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class AllBookController implements Initializable {
	private static final Logger logger = Logger.getLogger(AllBookController.class.getName());
	public ObservableList<Book> books = FXCollections.observableArrayList();
	@FXML
	private AnchorPane acMainContent;
	@FXML
	private TextField txtSearch;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnCheckout;
	@FXML
	private TableView<Book> tblBook;
	@FXML
	private TableColumn<Object, Object> clmIsbn;
	@FXML
	private TableColumn<Object, Object> clmTitle;
	@FXML
	private TableColumn<Object, Object> clmCheckoutLength;

	@FXML
	private Button btnRefresh;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void onKeyReleasedTextBoxSearch(Event event) {
		String searchString = txtSearch.getText();
		BookBusiness bookBusiness = new BookBusiness();
		books = FXCollections.observableArrayList(bookBusiness.search(searchString));
		tblBook.setItems(books);
	}

	@FXML
	private void onClickButtonAdd(ActionEvent event) {
		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource("/view/book/AddNew.fxml"));
		try {
			fXMLLoader.load();
			Parent parent = fXMLLoader.getRoot();
			Scene scene = new Scene(parent);
			scene.setFill(new Color(0, 0, 0, 0));
			AddBookController controller = fXMLLoader.getController();
			controller.lblAddBookContent.setText("Add Book");

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.show();
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void onClickButtonCopy(ActionEvent event) {
		Book book = tblBook.getSelectionModel().getSelectedItem();
		if (book == null) {
			Dialog.showInformationDialog("Information", null, "Plaese select a book want to copy.");
		} else {
			FXMLLoader fXMLLoader = new FXMLLoader();
			fXMLLoader.setLocation(getClass().getResource("/view/book/Copy.fxml"));
			try {
				fXMLLoader.load();
				Parent parent = fXMLLoader.getRoot();
				Scene scene = new Scene(parent);
				scene.setFill(new Color(0, 0, 0, 0));
				CopyController controller = fXMLLoader.getController();
				controller.viewDetails(book);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.show();
			} catch (IOException ex) {
				logger.log(Level.SEVERE, null, ex);
			}
		}
	}

	@FXML
	private void onClickButtonCheckout(ActionEvent event) {
		Book book = tblBook.getSelectionModel().getSelectedItem();
		if (book == null) {
			Dialog.showInformationDialog("Information", null, "Plaese select a book want to checkout.");
		} else {
			FXMLLoader fXMLLoader = new FXMLLoader();
			fXMLLoader.setLocation(getClass().getResource("/view/book/Checkout.fxml"));
			try {
				fXMLLoader.load();
				Parent parent = fXMLLoader.getRoot();
				Scene scene = new Scene(parent);
				scene.setFill(new Color(0, 0, 0, 0));
				CheckoutController controller = fXMLLoader.getController();
				controller.viewDetails(book);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.show();
			} catch (IOException ex) {
				logger.log(Level.SEVERE, null, ex);
			}
		}
	}

	public void viewDetails() {
		BookBusiness bookBusiness = new BookBusiness();
		books = FXCollections.observableArrayList(bookBusiness.getAll());
		tblBook.setItems(books);
		clmIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		clmTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		clmCheckoutLength.setCellValueFactory(new PropertyValueFactory<>("maxCheckoutLength"));
	}

	@FXML
	private void onClickTableBook(MouseEvent event) {
		if (event.getClickCount() == 2) {
			selectedView();
		} else {
			System.out.println("CLICK");
		}
	}

	public void selectedView() {
		Book book = tblBook.getSelectionModel().getSelectedItem();

		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource("/view/book/AddNew.fxml"));
		try {
			fXMLLoader.load();
			Parent parent = fXMLLoader.getRoot();
			Scene scene = new Scene(parent);
			scene.setFill(new Color(0, 0, 0, 0));
			AddBookController controller = fXMLLoader.getController();
			controller.lblAddBookContent.setText("Book Details");
			// controller.btnSave.setText("Update");
			controller.viewDetails(book);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.show();
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void onClickButtonRefresh(ActionEvent event) {
		txtSearch.setText("");
		BookBusiness bookBusiness = new BookBusiness();
		books = FXCollections.observableArrayList(bookBusiness.getAll());
		tblBook.setItems(books);
	}

}
