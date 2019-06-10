/**
 * Sample Skeleton for 'EditPath.fxml' Controller Class
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


public class EditPath {

    @FXML // fx:id="enterPathId"
    private TextField enterPathId; // Value injected by FXMLLoader

    @FXML // fx:id="editDiscription"
    private TextField editDiscription; // Value injected by FXMLLoader

    @FXML // fx:id="editNewPath"
    private TextField editNewPath; // Value injected by FXMLLoader

    @FXML
    void Editpath(ActionEvent event) throws IOException {
    	String message="EditPath,"+enterPathId.getText()+","+editNewPath.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("EditPath")) {
    	   Parent pane= FXMLLoader.load(getClass().getResource("employeeHomePage.fxml"));
           Scene log=new Scene(pane);
           Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           app_Stage.setScene(log);
           app_Stage.show();
        }


    }

    @FXML
    void Editdesc(ActionEvent event) throws IOException {
    	String message="EditPathdisc,"+enterPathId.getText()+","+editDiscription.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("EditPathdisc")) {
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
