/**
 /**
 * Sample Skeleton for 'ChangePrice.fxml' Controller Class
 */

package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePrice {

    @FXML // fx:id="enterCityId"
    private TextField enterCityId; // Value injected by FXMLLoader

    @FXML // fx:id="enterNewPrice"
    private TextField enterNewPrice; // Value injected by FXMLLoader

    @FXML // fx:id="sendPriceToCompanyM"
    private Button sendPriceToCompanyM; // Value injected by FXMLLoader

    @FXML
    void sendPriceToCompanyM(ActionEvent event) {
       	String message="sendPriceToManager,"+enterNewPrice.getText();
        Connect.client.handleMessageFromClientUI(message);
        if ("sendPriceToManager".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "sending Price To Manager Done");

        }
        else  if ("NotsendPriceToManager".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "cann't send!! ");

        }

    }

    @FXML
    void back(ActionEvent event) throws IOException {
     	    Parent pane = FXMLLoader.load(getClass().getResource("Mhomepage.fxml"));
    		Scene log = new Scene(pane);
    		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		app_Stage.setScene(log);
    		app_Stage.show();
    }

    @FXML
    void showprice(ActionEvent event) {
    	String cityId=enterCityId.getText();
    	String message="showPrice,"+ cityId;
    	if(cityId==null) {
    		JOptionPane.showMessageDialog(null, "You Must enter city id ");
    	}
    	else {
    		Connect.client.handleMessageFromClientUI(message);
    		if("dontShowPrice".equals(Connect.client.servermsg)) {
    			JOptionPane.showMessageDialog(null, "showing price failed");
    		}
    		else  {
    			JOptionPane.showMessageDialog(null, "the current price is"+ Connect.client.servermsg);
    		}
    	}
    	
    	 
         if("SignFailed".equals(Connect.client.servermsg))
         {
 			JOptionPane.showMessageDialog(null, "You are connected from another device !!");

         }
         else if ("NotFoundEmail".equals(Connect.client.servermsg)) {
 			JOptionPane.showMessageDialog(null, "You are not registed !!");

 		} 
      	
        
    }

}
