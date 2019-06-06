/**
 * Sample Skeleton for 'Psearch.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Psearch implements Initializable{

    @FXML // fx:id="Text"
    private Label Text; // Value injected by FXMLLoader
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void set(String text) {
		Text.setText(text);
	}

}
