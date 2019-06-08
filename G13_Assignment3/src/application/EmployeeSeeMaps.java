
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EmployeeSeeMaps {

    @FXML // fx:id="SearchMapId"
    private TextField SearchMapId; // Value injected by FXMLLoader

    @FXML // fx:id="imageview"
    private ImageView imageview; // Value injected by FXMLLoader

    @FXML // fx:id="Maptext"
    private TextArea Maptext; // Value injected by FXMLLoader

    @FXML
    void SearchMapId(ActionEvent event) {
    	String message = "EmployeeSeeMapId," + SearchMapId.getText();
		Connect.client.handleMessageFromClientUI(message);
		String[] input = Connect.client.servermsg.split("@");
		if ("MapPath".equals(input[0])) {
			if (input[1] != null) {
				String imagepath=input[1];
					Image image = new Image("file:" + imagepath);
					imageview.setImage(image);
					Maptext.setText(input[2]);
				}
			}
		}

    

    @FXML
    void back(ActionEvent event) throws IOException {
    	Parent pane = FXMLLoader.load(getClass().getResource("EmployeeHomePage.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
    }

}




