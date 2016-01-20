package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import custom.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.MemberBusiness;
import business.UserBusineess;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.LibraryMember;
import model.User;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Long Huynh
 */

public class LoginController implements Initializable {
	private static final Logger logger = Logger.getLogger(LoginController.class.getName());
	
	@FXML
    private Label lblLoginFailed;
    @FXML
    private TextField txtUserName;
    @FXML
    private Button btnClearUserNameField;
    @FXML
    private Button btnClearPasswordField;
    @FXML
    private PasswordField pwfUserPassword;

    CustomTextField customTextField = new CustomTextField();
    CustomPasswordField customPasswordField = new CustomPasswordField();
    
    @FXML
    private Button btnLogin;
    @FXML
    private AnchorPane apMother;
    @FXML
    private AnchorPane apDesignPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customTextField.clearTextFieldByButton(txtUserName, btnClearUserNameField);
        customPasswordField.clearPassFieldByButton(pwfUserPassword, btnClearPasswordField);
        BooleanBinding boolenBinding = txtUserName.textProperty().isEmpty()
                .or(pwfUserPassword.textProperty().isEmpty());
        btnLogin.disableProperty().bind(boolenBinding);
    }   

    @FXML
    private void onClickButtonLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Application.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Scene adminPanelScene = new Scene(parent);
        Stage adminPanelStage = new Stage();
        adminPanelStage.setMaximized(true);
        
        if (isValidCondition()) {
           try {      
        	   String memberId = txtUserName.getText();
        	   String password = pwfUserPassword.getText();
        	   UserBusineess userBusiness = new UserBusineess();
        	   User user = userBusiness.login(memberId, password);
        	   if(user != null){        	   
	        	   ApplicationController controller = new ApplicationController();
	        	   controller = loader.getController();               
	        	   controller.onClickHomeButton(event);
	        	   controller.setPermission(user.getRole());
	        	   controller.viewDetails(user);
	        	   
	               adminPanelStage.setScene(adminPanelScene);
	               adminPanelStage.getIcons().add(new Image("/image/icon.png"));
	               adminPanelStage.setTitle("Welcome [" + user.getUserName() + "] to Library Management System");
	               adminPanelStage.show();
	
	               Stage stage = (Stage) btnLogin.getScene().getWindow();
	               stage.close();
               }
        	   else{
        		   Dialog.showErrorDialog("Login failed", null, "Please check your user name or password.");
        	   }
               return;    
                    
           } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }

        }
    }

    private boolean isValidCondition() {    	
        return  !(txtUserName.getText().trim().isEmpty()
                || pwfUserPassword.getText().isEmpty());
    }

    @FXML
    private void onHitEnterUserNameField(ActionEvent event) {
        try {
        	onClickButtonLogin(event);
        } catch (IOException ex) {
        	logger.log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onHitEnterPasswordField(ActionEvent event) {
        try {
        	onClickButtonLogin(event);
        } catch (IOException ex) {
        	logger.log(Level.SEVERE, null, ex);
        }
    }
}
