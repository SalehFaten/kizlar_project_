/**
 * Sample Skeleton for 'ChangePrice.fxml' Controller Class
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

public class ChangePrice {

    @FXML // fx:id="enterCityId"
    private TextField enterCityId; // Value injected by FXMLLoader

    @FXML // fx:id="enterNewPrice"
    private TextField enterNewPrice; // Value injected by FXMLLoader

    @FXML // fx:id="sendPriceToCompanyM"
    private Button sendPriceToCompanyM; // Value injected by FXMLLoader

    @FXML
    void sendPriceToCompanyM(ActionEvent event) {

    }

    @FXML
    void enterNewPrice(ActionEvent event) {

    }

    @FXML
    void enterCityId(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent pane= FXMLLoader.load(getClass().getResource("ChangePrice.fxml"));
        Scene log=new Scene(pane);
        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        app_Stage.setScene(log);
        app_Stage.show();

    }

}
