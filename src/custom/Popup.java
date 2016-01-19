package custom;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.Dialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
*
* @author Long Huynh
*/

public class Popup {       
    public void sucessMessage(){
           
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/popup/Sucess.fxml").openStream());
            fXMLLoader.load();
                   
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setResizable(false);
            nStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Popup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
