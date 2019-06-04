/**
 * Sample Skeleton for 'addpath.fxml' Controller Class
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


public class AddPath {

    @FXML // fx:id="enterCityId"
    private TextField enterCityId; // Value injected by FXMLLoader


    @FXML // fx:id="enterDiscription"
    private TextField enterDiscription; // Value injected by FXMLLoader


    @FXML // fx:id="enterPathToNewPath"
    private TextField enterPathToNewPath; // Value injected by FXMLLoader

    @FXML // fx:id="enterPathName"
    private TextField enterPathName; // Value injected by FXMLLoader

    @FXML
    void AddPathbtn(ActionEvent event) throws IOException {
    	String message="AddPath,"+enterCityId.getText()+","+enterPathName.getText()+","+enterDiscription.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("AddPath")) {
        	JOptionPane.showMessageDialog(null, "Adding Map Finished Successfully");
    	   Parent pane= FXMLLoader.load(getClass().getResource("employeeHomePage.fxml"));
           Scene log=new Scene(pane);
           Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           app_Stage.setScene(log);
           app_Stage.show();
        }

    }

    @FXML
    void back(ActionEvent event) throws IOException {
    	 Parent pane= FXMLLoader.load(getClass().getResource("EmployeeHomePage.fxml"));
         Scene log=new Scene(pane);
         Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         app_Stage.setScene(log);
         app_Stage.show();

    }

}
