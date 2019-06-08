/**
 * Sample Skeleton for 'Control.fxml' Controller Class
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import client .*;
import common.ChatIF;
import application.Connect;
public  class Controller  {

//	  static ChatClient client=null;
//	ServerConsole server=null;
//	private AnchorPane rootPane;
    @FXML // fx:id="search"
    private TextField search; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private Button login; // Value injected by FXMLLoader

    @FXML // fx:id="register"
    private Button register; // Value injected by FXMLLoader


    @FXML
    void log(ActionEvent event) throws IOException {
        Parent pane= FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene log=new Scene(pane);
        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        app_Stage.setScene(log);
        app_Stage.show();
    }
    
    
    @FXML
    void Search(ActionEvent event) throws IOException {
    	String message="PublicSearch,"+search.getText();
       Connect.client.handleMessageFromClientUI(message); 
       if(Connect.client.servermsg!= null) {
       String[] result=Connect.client.servermsg.split("@");
FXMLLoader loader = new FXMLLoader(getClass().getResource( "Psearch.fxml" ));
AnchorPane root = (AnchorPane)loader.load();
       Psearch psearch1=loader.getController();
       if("PublicSearch".equals(result[0])) {
    	   for(int i=0;i<result.length;i++) {
    	       psearch1.set(result[i]);
    	       }
    	   
       Scene regist=new Scene(root);
       Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       app_stage.setScene(regist);
       app_stage.show();

       }
       }    
    }

    @FXML
    void regist(ActionEvent event) throws IOException {
    Parent pane= FXMLLoader.load(getClass().getResource("regist.fxml"));
    Scene regist=new Scene(pane);
    Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    app_stage.setScene(regist);
    app_stage.show();
    
    }


 



}
