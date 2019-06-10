/**
 * Sample Skeleton for 'addpath.fxml' Controller Class
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddPath implements Initializable{

    @FXML // fx:id="enterCityId"
    private TextField enterCityId; // Value injected by FXMLLoader


    @FXML // fx:id="enterDiscription"
    private TextField enterDiscription; // Value injected by FXMLLoader


    @FXML // fx:id="enterPathToNewPath"
    private TextField enterPathToNewPath; // Value injected by FXMLLoader

    @FXML // fx:id="enterPathName"
    private TextField enterPathName; // Value injected by FXMLLoader
    
	  @FXML // fx:id="image"
	    private ImageView image; // Value injected by FXMLLoader
	 public void setimage(Image im) {
			image.setImage(im);
	    }
    @FXML
    void AddPathbtn(ActionEvent event) throws IOException {
    	String message="AddPath,"+enterCityId.getText()+","+enterPathName.getText()+","+enterDiscription.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("AddPath")) {
        	JOptionPane.showMessageDialog(null, "Adding Map Finished Successfully");
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
			 * FXMLLoader.load(getClass().getResource("employeeHomePage.fxml")); Scene
			 * log=new Scene(pane); Stage
			 * app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			 * app_Stage.setScene(log); app_Stage.show();
			 */
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
