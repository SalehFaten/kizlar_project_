/**
 * Sample Skeleton for 'CDMhomePage.fxml' Controller Class
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
import javafx.stage.Stage;


public class CDMhomePage {

    @FXML // fx:id="CDchangePrice"
    private Button CDchangePrice; // Value injected by FXMLLoader

    @FXML // fx:id="outEmploeeHome"
    private Button outEmploeeHome; // Value injected by FXMLLoader

    @FXML // fx:id="CDacceptVersion"
    private Button CDacceptVersion; // Value injected by FXMLLoader

    @FXML
    void LogOut(ActionEvent event) throws IOException {
 	   Parent pane= FXMLLoader.load(getClass().getResource("Homepage.fxml"));
       Scene log=new Scene(pane);
       Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       app_Stage.setScene(log);
       app_Stage.show();

    }

    @FXML
    void CDchangePrice(ActionEvent event) throws IOException {
  	   Parent pane= FXMLLoader.load(getClass().getResource("ChangePrice.fxml"));
       Scene log=new Scene(pane);
       Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       app_Stage.setScene(log);
       app_Stage.show();

    }

    @FXML
    void CDacceptVersion(ActionEvent event) {

    }

    @FXML
    void EditSettings(ActionEvent event) throws IOException {
  	   Parent pane= FXMLLoader.load(getClass().getResource("EmployeeHomePage.fxml"));
       Scene log=new Scene(pane);
       Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       app_Stage.setScene(log);
       app_Stage.show();

    }

}



