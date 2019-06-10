/**
 /**
 * Sample Skeleton for 'ChangePrice.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChangePrice implements Initializable{

    @FXML // fx:id="enterCityId"
    private TextField enterCityId; // Value injected by FXMLLoader

    @FXML // fx:id="enterNewPrice"
    private TextField enterNewPrice; // Value injected by FXMLLoader

    @FXML // fx:id="sendPriceToCompanyM"
    private Button sendPriceToCompanyM; // Value injected by FXMLLoader
	  @FXML // fx:id="image"
	    private ImageView image; // Value injected by FXMLLoader
	 public void setimage(Image im) {
			image.setImage(im);
	    }
    @FXML
    void sendPriceToCompanyM(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) throws IOException {
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("Mhomepage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		CDMhomePage employee = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		employee.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane = FXMLLoader.load(getClass().getResource("Mhomepage.fxml"));
		 * Scene log = new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
    }

    @FXML
    void showprice(ActionEvent event) {
      	String message="showprice,"+enterCityId.getText();
        Connect.client.handleMessageFromClientUI(message);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
