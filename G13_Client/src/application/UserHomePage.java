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

    @FXML // fx:id="PersonalInformation"
    private Button PersonalInformation; // Value injected by FXMLLoader


    @FXML // fx:id="Email"
    private Label Email; // Value injected by FXMLLoader 
    

    @FXML // fx:id="WhatToSearch"
    private TextField WhatToSearch; // Value injected by FXMLLoader

    @FXML // fx:id="LogOut"
    private Button LogOut; // Value injected by FXMLLoader

    @FXML // fx:id="Search"
    private Button Search; // Value injected by FXMLLoader

    @FXML
    private Button ShowPurchaseHistory;
    
    public void setimage(Image im)
    {
    	image.setImage(im);
    }
    String TheEmail ;
    public void set(String txt)
    {
    	Email.setText(txt);
    	TheEmail=txt;
    }
    @FXML
    void ShowPurchase(ActionEvent event) throws IOException {
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAndPurchase.fxml"));
    		AnchorPane root = (AnchorPane) loader.load();
    		ShowAndPurchase SHOW = loader.getController();
    		 SHOW.SeTEmail(TheEmail);
        	Image im= new Image("images/background.jpg");
    		SHOW.setimage(im);
    		Scene regist = new Scene(root);
    		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		app_stage.setScene(regist);
    		app_stage.show();
    }

    @FXML
    void Search(ActionEvent event) {

    }

    @FXML
    void LogOut(ActionEvent event) throws IOException {
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
    		AnchorPane root = (AnchorPane) loader.load();
    		Controller home = loader.getController();
        	Image im= new Image("images/background.jpg");
    		home.setimage(im);
    		Scene regist = new Scene(root);
    		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		app_stage.setScene(regist);
    		app_stage.show();
    }

    @FXML
    void PersonalInformationChange(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("PersonalInformationPage.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
    	PersonalInformatiolnPage PERSONAL = loader.getController();
    		String message="PersonalInformation,"+ TheEmail;
            Connect.client.handleMessageFromClientUI(message);
            String [] res = Connect.client.servermsg.split("<");
            if("PersonalInformation".equals(res[0])) {
           String [] MyInfo = res[1].split("#");
           
          int flag =0;
          int i=0;
          while(i<MyInfo.length)
          {
         	 if( MyInfo[i]==null)
         		 flag=1;
         			 i++;
          }
          if (flag==0)
          {
        	  PERSONAL.setinfo(MyInfo);
     		PERSONAL.SeTEmail(TheEmail);
         	Image im= new Image("images/background.jpg");
     		PERSONAL.setimage(im);
     		Scene regist = new Scene(root);
     		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     		app_stage.setScene(regist);
     		app_stage.show();
           
          
           }
      
       
            }
    		
    }
    
    @FXML
    void ShowPurchaseHistory(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("PurchaseHistoryPage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		PurchaseHistory history = loader.getController();
		history.SeTEmail(TheEmail);
    	Image im= new Image("images/background.jpg");
    	history.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
    }
   
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}



