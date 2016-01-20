package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.book.AllBookController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


/**
 * FXML Controller class
 *
 * @author Long Huynh
 */
public class BookController implements Initializable {
    @FXML
    private StackPane spMainContent;

    @FXML
    public AnchorPane acMainSells;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }
  
    public void loadBookGridView(ActionEvent event) throws IOException {      
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/book/ViewAll.fxml").openStream());       
        AllBookController bookcontroller = fXMLLoader.getController();       
        bookcontroller.viewDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    private void tbtnReportsOnAction(ActionEvent event) throws IOException {

    }

}
