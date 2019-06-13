/**
 * Sample Skeleton for 'PersonalInformationPage.fxml' Controller Class
 */

package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    String Email1=null;
    public void setimage(Image im)
    {
    	Search.setImage(im);
    }
    @FXML
    void BackToUserHome(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		UserHomePage UserPage = loader.getController();
		UserPage.set(Email1);
    	Image im= new Image("images/background.jpg");
		UserPage.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
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
    public void SeTEmail(String theEmail) {
		// TODO Auto-generated method stub
		Email1=theEmail;
	}
}
