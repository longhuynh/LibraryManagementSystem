package controller.book;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.BookBusiness;
import controller.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import model.BookTableEntry;
import model.Role;
import util.LibrarySystemException;

/**
 *
 * @author Long Huynh
 */
public class AllBookController implements Initializable {
	private static final Logger logger = Logger.getLogger(AllBookController.class.getName());
	public ObservableList<BookTableEntry> bookTableEntries = FXCollections.observableArrayList();
	@FXML
	private AnchorPane acMainContent;
	@FXML
	private TextField txtSearch;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnCopy;
	@FXML
	private Button btnCheckout;
	@FXML
	private TableView<BookTableEntry> tblBook;
	@FXML
	private TableColumn<Object, Object> clmIsbn;
	@FXML
	private TableColumn<Object, Object> clmTitle;
	@FXML
	private TableColumn<Object, Object> clmCheckoutLength;
	@FXML
	private TableColumn<Object, Object> clmCopyNumber;
	@FXML
	private TableColumn<Object, Object> clmAuthor;

	@FXML
	private Button btnRefresh;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML
	private void onKeyReleasedTextBoxSearch(Event event) {
		bookTableEntries = FXCollections.observableArrayList();
		String searchString = txtSearch.getText();
		BookBusiness bookBusiness = new BookBusiness();
		List<Book> allBooks = bookBusiness.search(searchString);

		for (Book entry : allBooks) {
			bookTableEntries.add(new BookTableEntry(entry));
		}
		tblBook.getItems().clear();
		tblBook.setItems(bookTableEntries);
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
		BookTableEntry entry = tblBook.getSelectionModel().getSelectedItem();
		if (entry == null) {
			Dialog.showInformationDialog("Information", null, "Plaese select a book want to copy.");
		} else {
			BookBusiness bookBusiness = new BookBusiness();
			Book book = bookBusiness.searchBy(entry.getIsbn());
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Copy Book");
			alert.setHeaderText(null);
			alert.setContentText("Do you want to copy book [" + book.getIsbn() + " - " + book.getTitle() + "]?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				try {
					bookBusiness.addBookCopy(book.getIsbn());
					Dialog.showInformationDialog("Copy book", null, "Copy book sucessful.");
					viewDetails();
				} catch (LibrarySystemException ex) {
					logger.log(Level.SEVERE, null, ex);
				}	
			} else {
			   
			}
			
//			FXMLLoader fXMLLoader = new FXMLLoader();
//			fXMLLoader.setLocation(getClass().getResource("/view/book/Copy.fxml"));
//			try {
//				fXMLLoader.load();
//				Parent parent = fXMLLoader.getRoot();
//				Scene scene = new Scene(parent);
//				scene.setFill(new Color(0, 0, 0, 0));
//				CopyController controller = fXMLLoader.getController();
//				controller.viewDetails(book);
//				Stage stage = new Stage();
//				stage.setScene(scene);
//				stage.initModality(Modality.APPLICATION_MODAL);
//				stage.initStyle(StageStyle.TRANSPARENT);
//				stage.show();
//			} catch (IOException ex) {
//				logger.log(Level.SEVERE, null, ex);
//			}
		}
	}

	@FXML
	private void onClickButtonCheckout(ActionEvent event) {
		BookTableEntry entry = tblBook.getSelectionModel().getSelectedItem();
		if (entry == null) {
			Dialog.showInformationDialog("Information", null, "Plaese select a book want to checkout.");
		} else {
			BookBusiness bookBusiness = new BookBusiness();
			Book book = bookBusiness.searchBy(entry.getIsbn());
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
		bookTableEntries = FXCollections.observableArrayList();
		BookBusiness bookBusiness = new BookBusiness();
		List<Book> allBooks = bookBusiness.getAll();
		for (Book entry : allBooks) {
			bookTableEntries.add(new BookTableEntry(entry));
		}
		tblBook.getItems().clear();
		tblBook.setItems(bookTableEntries);
		clmIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		clmTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		clmCopyNumber.setCellValueFactory(new PropertyValueFactory<>("copyNumber"));
		clmCheckoutLength.setCellValueFactory(new PropertyValueFactory<>("maxCheckoutLength"));
		clmAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));		
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
		BookTableEntry entry = tblBook.getSelectionModel().getSelectedItem();

		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource("/view/book/AddNew.fxml"));
		try {
			BookBusiness bookBusiness = new BookBusiness();
			Book book = bookBusiness.searchBy(entry.getIsbn());
			fXMLLoader.load();
			Parent parent = fXMLLoader.getRoot();
			Scene scene = new Scene(parent);
			scene.setFill(new Color(0, 0, 0, 0));
			AddBookController controller = fXMLLoader.getController();
			controller.lblAddBookContent.setText("Book Details");			
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
	public void onClickButtonRefresh(ActionEvent event) {
		txtSearch.setText("");
		viewDetails();
	}

	public void setPermission(Role role) {
		if (Role.LIBRARIAN.equals(role)) {
			btnAdd.setDisable(true);
			btnCopy.setDisable(true);
		}
		if (Role.ADMIN.equals(role)) {
			btnCheckout.setDisable(true);

		} else {

		}
	}
}
