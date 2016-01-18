package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Long Huynh
 */
public class ApplicationController implements Initializable {

    @FXML
    private StackPane stackPaneContent;
    @FXML
    private ScrollPane leftSideBarScroolPane;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private ImageView imgMenuBtn;
    @FXML
    private BorderPane appContent;
    @FXML
    private Button btnLogOut;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private AnchorPane acHead;
    @FXML
    private AnchorPane acMain;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private Button btnHome;
    @FXML
    private ImageView imgHomeButton;

    @FXML
    private Button btnEmplopye;
    @FXML
    private ImageView imgEmployeBtn;
    @FXML
    private Button btnSell;
    @FXML
    private ImageView imgSellBtn;
    @FXML
    private Button btnAbout;
    @FXML
    private ImageView imgAboutBtn;
    @FXML
    private Label lblUserName;
    @FXML
    private Label lblUserNamePopOver;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblRoleAs;
 
    @FXML
    private Circle imgUserTop;
    @FXML
    private Circle circleImgUsr;
    @FXML
    private Label lblUserId;

    Image menuImage = new Image("/icon/menu.png");
    Image menuImageRed = new Image("/icon/menuRed.png");   

    String defultStyle = "-fx-border-width: 0px 0px 0px 5px; -fx-border-color:none";
    String activeStyle = "-fx-border-width: 0px 0px 0px 5px; -fx-border-color:#FF4E3C";

    Image home = new Image("/icon/home.png");
    Image homeRed = new Image("/icon/homeRed.png");    
    Image book = new Image("/icon/book.png");
    Image bookRed = new Image("/icon/bookRed.png");
    Image member = new Image("/icon/member.png");
    Image memberRed = new Image("/icon/memberRed.png");  
    Image about = new Image("/icon/about.png");
    Image aboutRed = new Image("/icon/aboutRed.png");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgMenuBtn.setImage(menuImage);
        Image userImage = new Image("/image/user.png");
        imgUserTop.setFill(new ImagePattern(userImage));
        circleImgUsr.setFill(new ImagePattern(userImage));
    }

    @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) throws IOException {
        if (sideMenuToogleBtn.isSelected()) {
            imgMenuBtn.setImage(menuImageRed);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(-130);
            sideMenu.play();
            acDashBord.getChildren().clear();
        } else {
            imgMenuBtn.setImage(menuImage);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(130);
            sideMenu.play();
            acDashBord.getChildren().add(leftSideBarScroolPane);
        }
    }

    @FXML
    private void btnLogOut(ActionEvent event) throws IOException {
        stackPaneContent.getChildren().clear();
        stackPaneContent.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Login.fxml")));
        acDashBord.getChildren().clear();
        acHead.getChildren().clear();
        acHead.setMaxHeight(0.0);
    }

    @FXML
    private void registerMainAction(KeyEvent event) {
        if (event.getCode() == KeyCode.F11) {
            Stage stage = (Stage) acMain.getScene().getWindow();
            stage.setFullScreen(true);
        }
    }

    @FXML
    public void onClickHomeButton(ActionEvent event){
        activeHomeButton();
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/Home.fxml").openStream());
        } catch (IOException e) {
            
        }
        AnchorPane root = fxmlLoader.getRoot();
        stackPaneContent.getChildren().clear();
        stackPaneContent.getChildren().add(root);
        System.out.println(lblUserName.getText());
        System.out.println(lblUserId.getText());

    }
    @FXML
    private void onClickMemberButton(ActionEvent event) throws IOException {
        activeMemberButton();       
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/Member.fxml").openStream());       
        MemberController controller = fXMLLoader.getController();
        controller.bpContent.getStylesheets().add("/style/MainStyle.css");
        controller.btnViewEmployeeOnAction(event);
        AnchorPane acPane = fXMLLoader.getRoot();
        stackPaneContent.getChildren().clear();
        stackPaneContent.getChildren().add(acPane);    }

   
    @FXML
    private void onClickAboutButton(ActionEvent event) {
    	 try {
	    	 activeAboutButton();
	         FXMLLoader fXMLLoader = new FXMLLoader();
	         fXMLLoader.load(getClass().getResource("/view/About.fxml").openStream());
	         AnchorPane anchorPane = fXMLLoader.getRoot();
	         stackPaneContent.getChildren().clear();
	         stackPaneContent.getChildren().add(anchorPane);     
    	 } catch (IOException ex) {
           Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void btnSellOnClick(ActionEvent event) {
        sellActive();     
        
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/Book.fxml").openStream());           
            BookController bookcontroller = fXMLLoader.getController();           
            bookcontroller.acMainSells.getStylesheets().add("/style/MainStyle.css");
            bookcontroller.loadBookGridView(event);
            AnchorPane anchorPane = fXMLLoader.getRoot();
            stackPaneContent.getChildren().clear();
            stackPaneContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPermission() {


//                if (rs.getInt(17) == 0) {
//                    btnEmplopye.setDisable(true);
//                }
//                if (rs.getInt(15) == 0) {
//                    btnSell.setDisable(true);
//                } else {
//
//                }

       
    }

    private void activeHomeButton() {
        imgHomeButton.setImage(homeRed);      
        imgSellBtn.setImage(book);
        imgEmployeBtn.setImage(member); 
        imgAboutBtn.setImage(about);
        btnHome.setStyle(activeStyle);       
        btnSell.setStyle(defultStyle);
        btnEmplopye.setStyle(defultStyle);        
        btnAbout.setStyle(defultStyle);
    }

    private void sellActive() {
        imgHomeButton.setImage(home);       
        imgSellBtn.setImage(bookRed);
        imgEmployeBtn.setImage(member);      
        imgAboutBtn.setImage(about);
        btnHome.setStyle(defultStyle);       
        btnSell.setStyle(activeStyle);
        btnEmplopye.setStyle(defultStyle);      
        btnAbout.setStyle(defultStyle);
    }

    private void activeMemberButton() {
        imgHomeButton.setImage(home);       
        imgSellBtn.setImage(book);
        imgEmployeBtn.setImage(memberRed);        
        imgAboutBtn.setImage(about);
        btnHome.setStyle(defultStyle);       
        btnSell.setStyle(defultStyle);
        btnEmplopye.setStyle(activeStyle);      
        btnAbout.setStyle(defultStyle);
    }
   
    private void activeAboutButton() {
        imgHomeButton.setImage(home);       
        imgSellBtn.setImage(book);
        imgEmployeBtn.setImage(member);       
        imgAboutBtn.setImage(aboutRed);
        btnHome.setStyle(defultStyle);      
        btnSell.setStyle(defultStyle);
        btnEmplopye.setStyle(defultStyle);        
        btnAbout.setStyle(activeStyle);
    }

    public void viewDetails() {
//        users.id = id;
//        usersGetway.selectedView(users);
//        image = users.image;
//        circleImgUsr.setFill(new ImagePattern(image));
//        imgUsrTop.setFill(new ImagePattern(image));
//        lblFullName.setText(users.fullName);
//        lblUsrNamePopOver.setText(users.userName);
    }
}
