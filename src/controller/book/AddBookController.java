package controller.book;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import model.Book;
import business.BookBusiness;
import controller.Dialog;
import controller.member.AllMemberController;
import model.Address;
import custom.CustomTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Author;
import util.LibrarySystemException;
import util.RuleSetFactory;

public class AddBookController implements Initializable {
	private static final Logger logger = Logger.getLogger(AllMemberController.class.getName());
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("1000 N 4th", "Fairfield", "IA", "52557"));
			add(new Address("500 S. Washington", "Kansas City", "MI", "65434"));
			add(new Address("213 W. Ave", "Seville", "Georgia", "41234"));
			add(new Address("140 N. Baton", "Baton Rouge", "TX", "33556"));
			add(new Address("200 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("135 New York Ave", "California", "CA", "94301"));
			add(new Address("42 W 2nd ", "Fairfield", "IA", "52556"));
			add(new Address("501 Mountain", "Mountain View", "CA", "94707"));
		}
	};

	@SuppressWarnings("serial")
	public List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("Tom", "Thomas", "641123456", addresses.get(0),
					"Don’t think for a second that I actually care what you have to say."));
			add(new Author("Peter", "Thomas", "6413334444", addresses.get(0), "Every storm runs out of rain."));
			add(new Author("Maryam", "Pugh", "6412221111", addresses.get(1),
					"Have lots of hair and like ugly things."));
			add(new Author("Andrew", "Cleveland", "9764452232", addresses.get(2),
					"I have this new theory that human adolescence doesn’t end until your early thirties."));
			add(new Author("Sarah", "Connor", "1234222663", addresses.get(3),
					"I always feel sad for seedless watermelons, because what if they wanted babies?"));
		}
	};

	CustomTextField customTextField = new CustomTextField();
	@FXML
	private TextField txtIsbn;
	@FXML
	private TextField txtTitle;
	@FXML
	private ListView<Author> lvwAuthor;
	@FXML
	private TextField txtMaxCheckoutLength;

	@FXML
	public Button btnSave;
	@FXML
	private Button btnClose;
	
	@FXML
	public Label lblAddBookContent;
	@FXML
	public Button btnClearIsbn;
	@FXML
	public Button btnClearTitle;
	@FXML
	public Button btnClearMaxCheckoutLength;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		customTextField.clearTextFieldByButton(txtIsbn, btnClearIsbn);
		customTextField.clearTextFieldByButton(txtTitle, btnClearTitle);
		customTextField.clearTextFieldByButton(txtMaxCheckoutLength, btnClearMaxCheckoutLength);

		customTextField.numaricTextfield(txtIsbn);
		customTextField.numaricTextfield(txtMaxCheckoutLength);
		generateListView();
	}

	private void generateListView() {

		ObservableList<Author> authors = FXCollections.observableArrayList(allAuthors);

		lvwAuthor.setItems(authors);
		lvwAuthor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lvwAuthor.setCellFactory(new Callback<ListView<Author>, ListCell<Author>>() {
			@Override
			public ListCell<Author> call(ListView<Author> p) {

				ListCell<Author> cell = new ListCell<Author>() {

					@Override
					protected void updateItem(Author t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getFirstName() + " " + t.getLastName());
						}
					}
				};

				return cell;
			}
		});
	}

	@FXML
	private void onClickButtonSave(ActionEvent event) throws IOException {
		String isbn = txtIsbn.getText();
		String title = txtTitle.getText();
		String maxCheckoutLength = txtMaxCheckoutLength.getText();
		
		if (checkAllRule()) {
			String messageTitle = "Update successful";
			if ("update".equals(btnSave.getText().toLowerCase())) {
				updateBook(isbn, title, maxCheckoutLength);
			} else {
				addNewBook(isbn, title, maxCheckoutLength);		
				messageTitle = "Add new successful";
			}	
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();	
			Dialog.showInformationDialog(messageTitle, null, "Please, click on refresh button to see new data");
		}
	}

	private void updateBook(String isbn, String title, String maxCheckoutLength) {
	
			BookBusiness bookBusiness = new BookBusiness();
			System.out.println(isbn );
			Book book = bookBusiness.searchBy(isbn);
			if (book != null) {
				try {
					List<Author> selectedItems = lvwAuthor.getSelectionModel().getSelectedItems();
					Author[] authors = selectedItems.toArray(new Author[] {});
					
					bookBusiness.updateBookInfo(isbn, title, Integer.parseInt(maxCheckoutLength),
							Arrays.asList(authors));					
					
				
				} catch (LibrarySystemException ex) {
					ex.printStackTrace();
				}
			} else {
				Dialog.showErrorDialog("Error", null, "This book is not exist in system.");
			}

			return;
	
	}

	private void addNewBook(String isbn, String title, String maxCheckoutLength) {
	
			BookBusiness bookBusiness = new BookBusiness();
			Book book = bookBusiness.searchBy(isbn);
			if (book == null) {

				try {
					List<Author> selectedItems = lvwAuthor.getSelectionModel().getSelectedItems();
					Author[] authors = selectedItems.toArray(new Author[] {});

					bookBusiness.addBook(isbn, title, Integer.parseInt(maxCheckoutLength), Arrays.asList(authors));
	
				} catch (LibrarySystemException ex) {
					ex.printStackTrace();
				}
			} else {
				Dialog.showErrorDialog("Error", null, "This book exist in system.");
			}

			return;
	
	}

	@FXML
	private void onClickCloseButton(ActionEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}

	private boolean checkAllRule() {
		try {
			if (isAnyEmpty())
				throw new LibrarySystemException("All fields must be nonempty");
			if (!RuleSetFactory.isExactLength(txtIsbn.getText(), 7))
				throw new LibrarySystemException("ISBN field must be exactly 7 digits");
		} catch (LibrarySystemException e) {
			Dialog.showWarningDialog("Error", null, e.getMessage());
			return false;
		}
		return true;
	}

	private boolean isAnyEmpty() {
		return txtIsbn.getText().isEmpty() || txtTitle.getText().isEmpty() || txtTitle.getText().isEmpty()
				|| txtMaxCheckoutLength.getText().isEmpty()
				|| lvwAuthor.getSelectionModel().getSelectedItems().isEmpty();
	}

	public void viewDetails(Book book) {
		btnSave.setText("Update");
		txtIsbn.setText(book.getIsbn());
		txtTitle.setText(book.getTitle());
		txtIsbn.setDisable(true);
		txtMaxCheckoutLength.setText("" + book.getMaxCheckoutLength());
		int[] indices = getIndices(book.getAuthors());
		if (indices.length > 0) {
			lvwAuthor.getSelectionModel().selectIndices(indices[0], indices);
		}
	}

	private int[] getIndices(List<Author> list) {
		int[] indices = new int[list.size()];
		for (int i = 0, k = 0; i < allAuthors.size(); i++) {
			if (list.contains(allAuthors.get(i))) {
				indices[k++] = i;
			}
		}
		return indices;
	}
}
