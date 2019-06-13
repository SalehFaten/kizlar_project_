/**
 * Sample Skeleton for 'PersonalInformationPage.fxml' Controller Class
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PersonalInformatiolnPage  {

    @FXML // fx:id="Search"
    private ImageView Search; // Value injected by FXMLLoader

    @FXML // fx:id="Tel"
    private TextField Tel; // Value injected by FXMLLoader

    @FXML // fx:id="FirstName"
    private TextField FirstName; // Value injected by FXMLLoader

    @FXML // fx:id="LastName"
    private TextField LastName; // Value injected by FXMLLoader

    @FXML // fx:id="ID"
    private TextField ID; // Value injected by FXMLLoader

    @FXML // fx:id="VisaNum"
    private TextField VisaNum; // Value injected by FXMLLoader

    @FXML // fx:id="Password"
    private TextField Password; // Value injected by FXMLLoader

    @FXML // fx:id="CVV"
    private TextField CVV; // Value injected by FXMLLoader

    @FXML // fx:id="VisaDate"
    private TextField VisaDate; // Value injected by FXMLLoader

    @FXML // fx:id="Email"
    private TextField Email; // Value injected by FXMLLoader

    @FXML // fx:id="BackToUserHome"
    private Button BackToUserHome; // Value injected by FXMLLoader

    @FXML
    void BackToUserHome(ActionEvent event) throws IOException {
    	Parent pane= FXMLLoader.load(getClass().getResource("UserHomePage.fxml"));
    	Scene log=new Scene(pane);
        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        app_Stage.setScene(log);
        app_Stage.show();
    }

    @FXML
    void CVVEdit(ActionEvent event) {

    }

    @FXML
    void EmailEdit(ActionEvent event) {

    }

    @FXML
    void FirstNameEdit(ActionEvent event) {

    }

    @FXML
    void IdEdit(ActionEvent event) {

    }

    @FXML
    void LastNameEdit(ActionEvent event) {

    }

    @FXML
    void PasswordEdit(ActionEvent event) {

    }

    @FXML
    void TelEdit(ActionEvent event) {

    }

    @FXML
    void VisaDateEdit(ActionEvent event) {

    }

    @FXML
    void VisaNumEdit(ActionEvent event) {

    }

}
