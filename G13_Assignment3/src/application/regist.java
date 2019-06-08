/**
 * Sample Skeleton for 'regist.fxml' Controller Class
 */

package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class regist {

    @FXML // fx:id="MM"
    private TextField MM; // Value injected by FXMLLoader

    @FXML // fx:id="CVV"
    private TextField CVV; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private TextField password; // Value injected by FXMLLoader

    @FXML // fx:id="visa"
    private TextField visa; // Value injected by FXMLLoader

    @FXML // fx:id="mobile"
    private TextField mobile; // Value injected by FXMLLoader

    @FXML // fx:id="ID"
    private TextField ID; // Value injected by FXMLLoader

    @FXML // fx:id="Fistname"
    private TextField Fistname; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private TextField email; // Value injected by FXMLLoader

    @FXML // fx:id="lastname"
    private TextField lastname; // Value injected by FXMLLoader

    @FXML
    void back(ActionEvent event) throws IOException {
  	  Parent pane= FXMLLoader.load(getClass().getResource("Homepage.fxml"));
      Scene log=new Scene(pane);
      Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
      app_Stage.setScene(log);
      app_Stage.show();
    }

    @FXML
    void SignUp(ActionEvent event) throws IOException {
     	String message="SignUp,"+email.getText()+","+password.getText()+","+Fistname.getText()+","+lastname.getText()+","+mobile.getText()+","+visa.getText()+","+CVV.getText()+","+MM.getText()+","+ID.getText()+","+"1";
        Connect.client.handleMessageFromClientUI(message);
        System.out.println(Connect.client.servermsg);
    }

}




