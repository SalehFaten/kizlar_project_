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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserHomePage {

    @FXML // fx:id="UserHomePage"
    private AnchorPane UserHomePage; // Value injected by FXMLLoader

    @FXML // fx:id="PurchaseOfUser"
    private Button PurchaseOfUser; // Value injected by FXMLLoader

    @FXML // fx:id="SeeMaps"
    private Button SeeMaps; // Value injected by FXMLLoader

    @FXML // fx:id="LogOut_UserHomePage"
    private Button LogOut_UserHomePage; // Value injected by FXMLLoader

    @FXML
    void purchase(ActionEvent event) throws IOException {
    	 Parent pane= FXMLLoader.load(getClass().getResource("purchase.fxml"));
         Scene log=new Scene(pane);
         Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         app_Stage.setScene(log);
         app_Stage.show();
    	
    }

    @FXML
    void LogOut_UserHomePage(ActionEvent event) throws IOException {
    	Parent pane= FXMLLoader.load(getClass().getResource("signin.fxml"));
        Scene log=new Scene(pane);
        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        app_Stage.setScene(log);
        app_Stage.show();

    }

    @FXML
    void SeeMaps(ActionEvent event) throws IOException {
    	Parent pane= FXMLLoader.load(getClass().getResource("SeeMaps.fxml"));
        Scene log=new Scene(pane);
        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        app_Stage.setScene(log);
        app_Stage.show();

    }
    

    @FXML
    void SearchForUser(ActionEvent event) throws IOException {
    	Parent pane= FXMLLoader.load(getClass().getResource("SeeMaps.fxml"));
        Scene log=new Scene(pane);
        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        app_Stage.setScene(log);
        app_Stage.show();

    }


}
