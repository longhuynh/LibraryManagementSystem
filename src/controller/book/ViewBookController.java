package controller.book;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.LoginController;
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
	private static final Logger logger = Logger.getLogger(ViewBookController.class.getName());
	
	@FXML
	private AnchorPane acMainContent;
	@FXML
	private TextField txtSearch;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private TableView<Book> tblBook;
	@FXML
	private TableColumn<Object, Object> clmIsbn;
	@FXML
	private TableColumn<Object, Object> clmTitle;
	@FXML
	private TableColumn<Object, Object> clmAuthor;
	@FXML
	private TableColumn<Object, Object> clmCopyNumber;

	@FXML
	private TableColumn<Object, Object> clmCheckoutLength;

	@FXML
	private Button btnRefresh;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void onKeyReleasedTextBoxSearch(Event event) {

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
	            controller.btnUpdate.setVisible(false);
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

	}

	@FXML
	private void onClickButtonCheckout(ActionEvent event) {

	}

	public void viewDetails() {

	}

	public void selectedView() {

	}

	@FXML
	private void onClickTableBook(MouseEvent event) {

	}

	@FXML
	private void onClickButtonRefresh(ActionEvent event) {

	}

}
