/**
 * Sample Skeleton for 'EmployeeSeeMaps.fxml' Controller Class
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EmployeeSeeMaps {

    @FXML // fx:id="SearchMapId"
    private TextField SearchMapId; // Value injected by FXMLLoader

    @FXML // fx:id="MapImage"
    private ImageView MapImage; // Value injected by FXMLLoader

    @FXML
    void MapImage(ActionEvent event) {

    }

    @FXML
    void SearchMapId(ActionEvent event) throws IOException {
    	String message="EmployeeSeeMapId,"+SearchMapId.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("EmployeeSeeMapId")) {
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
