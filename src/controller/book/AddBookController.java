package controller.book;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddBookController implements Initializable {
    @FXML
    private TextField txtIsbn;
    @FXML
    public Button btnSave; 
    @FXML
    private Button btnClose;
    @FXML
    public Button btnUpdate;
    @FXML
	public Label lblAddBookContent;
    
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
       
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
      
    }
    
    public void viewDetails(){
       
    }
}
