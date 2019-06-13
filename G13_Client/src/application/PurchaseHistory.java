
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PurchaseHistory implements Initializable {

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="PurchaseTable"
    private TableView<?> PurchaseTable; // Value injected by FXMLLoader

    @FXML // fx:id="CityName"
    private TableColumn<?, ?> CityName; // Value injected by FXMLLoader

    @FXML // fx:id="PurchaseType"
    private TableColumn<?, ?> PurchaseType; // Value injected by FXMLLoader

    @FXML // fx:id="PurchaseDate"
    private TableColumn<?, ?> PurchaseDate; // Value injected by FXMLLoader

    @FXML // fx:id="Validity"
    private TableColumn<?, ?> Validity; // Value injected by FXMLLoader
    @FXML
    void ShowPurchaseHistory(ActionEvent event) {
    }
    String Email=null;
    public void setimage(Image im)
    {
    	image.setImage(im);
    }
    @FXML
    void Back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		UserHomePage UserPage = loader.getController();
		UserPage.set(Email);
    	Image im= new Image("images/background.jpg");
		UserPage.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
    }

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
     public void SeTEmail(String email)
    {
    	Email=email;
    }

    }


