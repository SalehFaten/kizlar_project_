/**
 * Sample Skeleton for 'Psearch.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Psearch implements Initializable{

    @FXML // fx:id="area"
    private TextArea area; // Value injected by FXMLLoader


    @FXML
    void back(ActionEvent event) throws IOException {
    	Parent pane = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
		area.clear();
    }

	public void set(String text) {
	area.appendText(text);
	area.appendText("\n");

}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}


//
///**
// * Sample Skeleton for 'Psearch.fxml' Controller Class
// */
//
//package application;
//
//import java.awt.TextArea;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//public class Psearch implements Initializable{
//	 @FXML // fx:id="area"
//	    private TextArea area; // Value injected by FXMLLoader
//    @FXML // fx:id="Text"
//    private Label Text; // Value injected by FXMLLoader
//
//    @FXML
//    void back(ActionEvent event) throws IOException {
//    	  Parent pane= FXMLLoader.load(getClass().getResource("Controller.fxml"));
//    	    Scene regist=new Scene(pane);
//    	    Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//    	    app_stage.setScene(regist);
//    	    app_stage.show();
//    	    
//    }
//
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//	public void set(String text) {
//	area.setText(text);
//}
//
//}



