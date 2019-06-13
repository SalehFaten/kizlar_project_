/**
 * Sample Skeleton for 'CompanyMhomePage.fxml' Controller Class
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CompanyMhomePage implements Initializable{

    @FXML // fx:id="seeUserReports"
    private Button seeUserReports; // Value injected by FXMLLoader

    @FXML // fx:id="acceptPrice"
    private Button acceptPrice; // Value injected by FXMLLoader

    @FXML // fx:id="CMacceptVersion"
    private Button CMacceptVersion; // Value injected by FXMLLoader

    @FXML // fx:id="outCDMHome"
    private Button outCDMHome; // Value injected by FXMLLoader
	  @FXML // fx:id="image"
	   private ImageView image; // Value injected by FXMLLoader
	 public void setimage(Image im) {
			image.setImage(im);
	    }
    @FXML
    void acceptPrice(ActionEvent event) {

    }

    @FXML
    void seeUserReports(ActionEvent event) {

    }

    @FXML
    void CMacceptVersion(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) throws IOException {
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("Mhomepage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		CDMhomePage employee = loader.getController();
    	Image im= new Image("images/background.jpg");
		employee.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
    }

    @FXML
    void LogOut(ActionEvent event) throws IOException {
       	FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			Controller home = loader.getController();
    	    	Image im= new Image("images/background.jpg");
    			home.setimage(im);
    			Scene regist = new Scene(root);
    			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    			app_stage.setScene(regist);
    			app_stage.show();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
