/**
 * Sample Skeleton for 'SeeMaps.fxml' Controller Class
 */

package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SeeMaps implements Initializable{

    @FXML // fx:id="backToUserHomePage"
    private Button backToUserHomePage; // Value injected by FXMLLoader
    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader
    public void setimage(Image im) {
		image.setImage(im);
    }
    @FXML
    void backToUserHomePage(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
