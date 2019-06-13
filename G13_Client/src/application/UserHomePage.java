
 /**
  * Sample Skeleton for 'UserHomePage.fxml' Controller Class
  */

 package application;

 import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
 import javafx.scene.control.TextField;
 import javafx.scene.text.Text;
import javafx.stage.Stage;

 public class UserHomePage {

     @FXML // fx:id="Search"
     private Button Search; // Value injected by FXMLLoader

     @FXML // fx:id="ShowPurchase"
     private Button ShowPurchase; // Value injected by FXMLLoader

     @FXML // fx:id="WhatToSearch"
     private TextField WhatToSearch; // Value injected by FXMLLoader

     @FXML // fx:id="LogOut"
     private Button LogOut; // Value injected by FXMLLoader

     @FXML // fx:id="PersonalInformation"
     private Button PersonalInformation; // Value injected by FXMLLoader

     @FXML // fx:id="Email"
     private Text Email; // Value injected by FXMLLoader

     @FXML
     void LogOut(ActionEvent event) throws IOException {
    	 Parent pane= FXMLLoader.load(getClass().getResource("HomePage.fxml"));
         Scene log=new Scene(pane);
         Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();       
         app_Stage.setScene(log);
         app_Stage.show();
     }

     @FXML
     void PersonalInformationChange(ActionEvent event) throws IOException {
    	 Parent pane= FXMLLoader.load(getClass().getResource("PersonalInformatiolnPage.fxml"));
         Scene log=new Scene(pane);
         Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();       
         app_Stage.setScene(log);
         app_Stage.show();
     }

     @FXML
     void Search(ActionEvent event) {

     }

     @FXML
     void ShowPurchase(ActionEvent event) throws IOException {
    	 Parent pane= FXMLLoader.load(getClass().getResource("ShowAndPurchase.fxml"));
         Scene log=new Scene(pane);
         Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();       
         app_Stage.setScene(log);
         app_Stage.show();
     }

 }

