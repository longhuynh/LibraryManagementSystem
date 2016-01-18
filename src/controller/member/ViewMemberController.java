package controller.member;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

import custom.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.LibraryMember;

import javax.imageio.ImageIO;

public class ViewMemberController implements Initializable {

    CustomPasswordField cPf = new CustomPasswordField();
    CustomTextField cTf = new CustomTextField();
    

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
    private Button btnUpdate;
    
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtCity;
  
    
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
    private CheckBox cbStatus;

    @FXML
    private TableView<LibraryMember> tblMemberList;
    @FXML
    private TableColumn<Object, Object> clmEmployeId;
    @FXML
    private TableColumn<Object, Object> clmEmployeName;
    @FXML
    private Label lblCreator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        cTf.clearTextFieldByButton(txtFirstName, btnClearFirstName);
        cTf.clearTextFieldByButton(txtLastName, btnClearLastName);
        cTf.clearTextFieldByButton(txtPhoneNumber, btnClearPhoneNumber);
        cTf.clearTextFieldByButton(txtCity, btnClearCity);
      
        cTf.clearTextFieldByButton(txtStreet, btnClearStreet);

        cTf.numaricTextfield(txtStreet);

    }

    @FXML
    private void onActionSearch(ActionEvent event) {

    }

   
    @FXML
    private void onClickTableMember(KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP)) {
            setselectedView();
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            setselectedView();
        }
    }
    @FXML
    public void tblEmloyeOnClick(Event event) {
        setselectedView();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws FileNotFoundException {

      
    }

    @FXML
    private void cbOnAction(ActionEvent event) {
            if (cbStatus.isSelected()) {
                cbStatus.setText("Active");
            } else {
                cbStatus.setText("Deactive");
            }
    }


    public void setselectedView() {
        clearAll();
//        ListEmployee employeeList = tblEmoyeeList.getSelectionModel().getSelectedItem();
//        if (employeeList != null) {
//            users.id = employeeList.getEmployeeId();
//            usersGetway.selectedView(users);
//            id = users.id;
//            tfUserName.setText(users.userName);
//            tfFullName.setText(users.fullName);
//            tfPhoneNumber.setText(users.contactNumber);
//            tfEmailAddress.setText(users.emailAddress);
//            tfSalary.setText(users.salary);
//            tfDateofJoin.setText(users.date);
//            creatorId = users.creatorId;
//            taAddress.setText(users.address);
//            image = users.image;
//            recUsrImage.setFill(new ImagePattern(image));
//            sql.creatorNameFindar(creatorId, lblCreator);
//            tfCreatedBy.setText(lblCreator.getText());
//            if (users.status.matches("1")) {
//                cbStatus.setSelected(true); cbStatus.setText("Active");
//            } else if (users.status.matches("0")) {
//                cbStatus.setSelected(false); cbStatus.setText("Deactive");
//            }
//            if(users.id.matches("1")){
//                btnUpdate.setVisible(false); btnDelete.setVisible(false); hlChangePassword.setVisible(false); hlViewPermission.setVisible(false);
//            }else{
//                btnUpdate.setVisible(true); btnDelete.setVisible(true); hlChangePassword.setVisible(true); hlViewPermission.setVisible(true);
//            }
//
//        }
    }

    public void showDetails() {
//        tblEmoyeeList.setItems(users.employeeLists);
//        clmEmployeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
//        clmEmployeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
//        usersGetway.view(users);


    }

//    public void checqPermission() {
//        try {
//            pst = con.prepareStatement("select * from UserPermission where UserId=?");
//            pst.setString(1, userId);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                if (rs.getInt(13) != 1) {
//                    hlChangePassword.setDisable(true);
//                } else {
//                    hlChangePassword.setDisable(false);
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ViewAllController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private void clearAll() {
        txtMemberId.clear();
        txtFirstName.clear();
       
        txtStreet.clear();
        txtLastName.clear();
        txtCity.clear();
        txtPhoneNumber.clear();
      
    }
}
