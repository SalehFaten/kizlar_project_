/**
 * Sample Skeleton for 'UserHomePage.fxml' Controller Class
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserHomePage implements Initializable{

	@FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="ShowPurchase"
    private Button ShowPurchase; // Value injected by FXMLLoader

    @FXML // fx:id="WhatToSearch"
    private TextField WhatToSearch; // Value injected by FXMLLoader

    @FXML // fx:id="LogOut"
    private Button LogOut; // Value injected by FXMLLoader

    @FXML // fx:id="Search"
    private Button Search; // Value injected by FXMLLoader
    
    @FXML // fx:id="email"
    private Label email; // Value injected by FXMLLoader
    
	 public void setimage(Image im) {
			image.setImage(im);
	    }
	 
	 public void set(String input)
	 {
			email.setText(input);
	    }
	 
    @FXML
    void LogOut(ActionEvent event) throws IOException {
       	FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			Controller home = loader.getController();
    	    	Image im= new Image("images/world-map-background-copy.jpg");
    			home.setimage(im);
    			Scene regist = new Scene(root);
    			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    			app_stage.setScene(regist);
    			app_stage.show();
    }

    @FXML
    void Search(ActionEvent event) {

    }

    @FXML
    void ShowPurchase(ActionEvent event) throws IOException {
       	FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Controller home = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		home.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

///**
// * Sample Skeleton for 'UserHomePage.fxml' Controller Class
// */
//
///**
// * Sample Skeleton for 'UserHomePage.fxml' Controller Class
// */
//
//package application;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//import javafx.fxml.*;
//public class UserHomePage implements Initializable {
//
//    @FXML // fx:id="Search"
//    private Button Search; // Value injected by FXMLLoader
//
//    @FXML // fx:id="ShowPurchase"
//    private Button ShowPurchase; // Value injected by FXMLLoader
//
//    @FXML // fx:id="WhatToSearch"
//    private TextField WhatToSearch; // Value injected by FXMLLoader
//
//    @FXML // fx:id="LogOut"
//    private Button LogOut; // Value injected by FXMLLoader
//
//    @FXML
//    void LogOut(ActionEvent event) {
//
//    }
//
//    @FXML
//    void Search(ActionEvent event) {
//
//    }
//
//    @FXML
//    void ShowPurchase(ActionEvent event) throws IOException {
//    	Parent pane= FXMLLoader.load(getClass().getResource("ShowAndPurchase.fxml"));
//        Scene log=new Scene(pane);
//        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//        app_Stage.setScene(log);
//        app_Stage.show();
//    }
//   
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}
