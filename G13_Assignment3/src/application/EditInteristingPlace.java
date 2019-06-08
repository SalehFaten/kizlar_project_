
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditInteristingPlace {

	   @FXML // fx:id="EditplaceId"
	    private TextField EditplaceId; // Value injected by FXMLLoader

	    @FXML // fx:id="EditDescription"
	    private TextField EditDescription; // Value injected by FXMLLoader

	    @FXML // fx:id="PlaceId"
	    private TextField PlaceId; // Value injected by FXMLLoader

	    @FXML // fx:id="EditPlaceName"
	    private TextField EditPlaceName; // Value injected by FXMLLoader


    @FXML
    void Editname(ActionEvent event) {
       	String message="EditPlaceName,"+PlaceId.getText()+","+EditPlaceName.getText();
        Connect.client.handleMessageFromClientUI(message);

    }

    @FXML
    void EditDescription(ActionEvent event) {
     	String message="EditPlaceDisc,"+PlaceId.getText()+","+EditDescription.getText();
        Connect.client.handleMessageFromClientUI(message);
    }

    @FXML
    void removeplace(ActionEvent event) {
     	String message="RemovePlace,"+PlaceId.getText();
        Connect.client.handleMessageFromClientUI(message);
    }

    @FXML
    void EditId(ActionEvent event) {
     	String message="EditPlaceId,"+PlaceId.getText()+","+EditplaceId.getText();
        Connect.client.handleMessageFromClientUI(message);
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








