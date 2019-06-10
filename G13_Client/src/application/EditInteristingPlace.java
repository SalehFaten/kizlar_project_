
/**
 * Sample Skeleton for 'EditInteristingPlace.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditInteristingPlace implements Initializable{

	   @FXML // fx:id="EditplaceId"
	    private TextField EditplaceId; // Value injected by FXMLLoader

	    @FXML // fx:id="EditDescription"
	    private TextField EditDescription; // Value injected by FXMLLoader

	    @FXML // fx:id="PlaceId"
	    private TextField PlaceId; // Value injected by FXMLLoader

	    @FXML // fx:id="EditPlaceName"
	    private TextField EditPlaceName; // Value injected by FXMLLoader
		  @FXML // fx:id="image"
		    private ImageView image; // Value injected by FXMLLoader
		 public void setimage(Image im) {
				image.setImage(im);
		    }

    @FXML
    void Editname(ActionEvent event) {
       	String message="EditPlaceName,"+PlaceId.getText()+","+EditPlaceName.getText();
        Connect.client.handleMessageFromClientUI(message);
        if ("EditPlaceName".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "Edit Place name Finished Successfully");

        }
        else  if ("NotEditPlaceName".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "cann't edit !! ");

        }

    }

    @FXML
    void EditDescription(ActionEvent event) {
     	String message="EditPlaceDisc,"+PlaceId.getText()+","+EditDescription.getText();
        Connect.client.handleMessageFromClientUI(message);
        if ("EditPlaceDisc".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "Edit Place description Finished Successfully");

        }
        else  if ("NotEditPlaceDisc".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "cann't edit !! ");

        }
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
        if ("EditPlaceId".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "Edit Place id Finished Successfully");

        }
        else  if ("NotEditPlaceId".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "cann't edit !! ");

        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		employeeHomePage employee = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		employee.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane=
		 * FXMLLoader.load(getClass().getResource("EmployeeHomePage.fxml")); Scene
		 * log=new Scene(pane); Stage
		 * app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		 * app_Stage.setScene(log); app_Stage.show();
		 */
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}








