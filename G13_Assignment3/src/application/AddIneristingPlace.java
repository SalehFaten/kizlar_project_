/**
 * Sample Skeleton for 'AddIneristingPath.fxml' Controller Class
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


public class AddIneristingPlace {


    @FXML // fx:id="enterPlaceId"
    private TextField enterPlaceId; // Value injected by FXMLLoader

    @FXML // fx:id="enterRecomendedTime"
    private TextField enterRecomendedTime; // Value injected by FXMLLoader

    @FXML // fx:id="enterDiscription"
    private TextField enterDiscription; // Value injected by FXMLLoader

    @FXML // fx:id="enterCityId"
    private TextField enterCityId; // Value injected by FXMLLoader

    @FXML // fx:id="enterPathToPlace"
    private TextField enterPathToPlace; // Value injected by FXMLLoader


    @FXML // fx:id="enterPlaceName"
    private TextField enterPlaceName; // Value injected by FXMLLoader


    @FXML // fx:id="enterLocation"
    private TextField enterLocation; // Value injected by FXMLLoader

    @FXML

    void enterDiscription(ActionEvent event) {

    }

    @FXML
    void Addbtn(ActionEvent event) {


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
