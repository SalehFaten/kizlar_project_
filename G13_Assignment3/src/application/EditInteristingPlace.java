/**
 * Sample Skeleton for 'EditInteristingPlace.fxml' Controller Class
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
import javafx.stage.Stage;




public class EditInteristingPlace {

    @FXML // fx:id="EditDescription"
    private TextField EditDescription; // Value injected by FXMLLoader


    @FXML // fx:id="PlaceId"
    private TextField PlaceId; // Value injected by FXMLLoader
    
    @FXML // fx:id="EditPlaceName"
    private TextField EditPlaceName; // Value injected by FXMLLoader

    @FXML // fx:id="EditPath"
    private TextField EditPath; // Value injected by FXMLLoader

    @FXML // fx:id="EditLocation"
    private TextField EditLocation; // Value injected by FXMLLoader

    @FXML // fx:id="EditRecomendedTime"
    private TextField EditRecomendedTime; // Value injected by FXMLLoader

    @FXML
    void Editname(ActionEvent event) throws IOException {
       	String message="EditPlaceName,"+PlaceId.getText()+","+EditPlaceName.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("EditPlaceName")) {
    	   Parent pane= FXMLLoader.load(getClass().getResource("employeeHomePage.fxml"));
           Scene log=new Scene(pane);
           Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           app_Stage.setScene(log);
           app_Stage.show();
        }


    }

    @FXML
    void EditDescription(ActionEvent event) throws IOException {
      	String message="EditPlaceDisc,"+PlaceId.getText()+","+EditDescription.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("EditPlaceDisc")) {
    	   Parent pane= FXMLLoader.load(getClass().getResource("employeeHomePage.fxml"));
           Scene log=new Scene(pane);
           Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           app_Stage.setScene(log);
           app_Stage.show();
        }


    }

    @FXML
    void EditPath(ActionEvent event) throws IOException {
      	String message="EditPlacePath,"+PlaceId.getText()+","+EditPath.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("EditPlacPath")) {
    	   Parent pane= FXMLLoader.load(getClass().getResource("employeeHomePage.fxml"));
           Scene log=new Scene(pane);
           Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           app_Stage.setScene(log);
           app_Stage.show();
        }


    }

    @FXML
    void EditRecomendedTime(ActionEvent event) throws IOException {
      	String message="EditPlacetime,"+PlaceId.getText()+","+EditRecomendedTime.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("EditPlacetime")) {
    	   Parent pane= FXMLLoader.load(getClass().getResource("employeeHomePage.fxml"));
           Scene log=new Scene(pane);
           Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           app_Stage.setScene(log);
           app_Stage.show();
        }


    }

    @FXML
    void EditLocation(ActionEvent event) throws IOException {
      	String message="EditPlacelocation,"+PlaceId.getText()+","+EditLocation.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("EditPlacelocation")) {
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
