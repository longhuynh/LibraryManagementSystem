package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Long Huynh
 */
public class AboutController implements Initializable {

	@FXML
	private ImageView imgLibraryMgtmIcon = new ImageView();

	@FXML
	private Circle circularImageLong;

	@FXML
	private Circle circularImageAchyut;

    @FXML
    private Circle circularImageAmit;



	Image libraryMgtmIcon = new Image("/image/icon.png");
	Image long1 = new Image("/image/long.jpg");
	Image achyut = new Image("/image/achyut.jpg");
	Image amit = new Image("/image/amit.jpg");


	@Override
    public void initialize(URL url, ResourceBundle rb) {
		imgLibraryMgtmIcon.setImage(libraryMgtmIcon);
		circularImageLong.setFill(new ImagePattern(long1));
		circularImageAchyut.setFill(new ImagePattern(achyut));
		circularImageAmit.setFill(new ImagePattern(amit));
    }


}