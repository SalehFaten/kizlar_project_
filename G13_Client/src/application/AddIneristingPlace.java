
/**
 * Sample Skeleton for 'AddIneristingPlace.fxml' Controller Class
 */

package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
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

public class AddIneristingPlace implements Initializable{

    @FXML // fx:id="enterPlaceId"
    private TextField enterPlaceId; // Value injected by FXMLLoader

    @FXML // fx:id="path"
    private TextField path; // Value injected by FXMLLoader

    @FXML // fx:id="enterDiscription"
    private TextField enterDiscription; // Value injected by FXMLLoader

    @FXML // fx:id="enterCityId"
    private TextField enterCityId; // Value injected by FXMLLoader

    @FXML // fx:id="enterPlaceName"
    private TextField enterPlaceName; // Value injected by FXMLLoader

    @FXML // fx:id="enterMapId"
    private TextField enterMapId; // Value injected by FXMLLoader

	  @FXML // fx:id="image"
	    private ImageView image; // Value injected by FXMLLoader
	 public void setimage(Image im) {
			image.setImage(im);
	    }
    @FXML
    void Addbtn(ActionEvent event) {
    	String message="AddPlace,"+enterPlaceName.getText()+","+enterPlaceId.getText()+","+enterDiscription.getText()+","+enterMapId.getText()+","+enterCityId.getText()+","+path.getText();
        Connect.client.handleMessageFromClientUI(message);
        if ("AddPlace".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "Adding InterstingPlace Finished Successfully");

        }
        else  if ("NotAdd".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "cann't add place to map!! ");

        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		employeeHomePage employee = loader.getController();
    	Image im= new Image("images/background.jpg");
		employee.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane =
		 * FXMLLoader.load(getClass().getResource("EmployeeHomePage.fxml")); Scene log =
		 * new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
    }

    @FXML
    void maplocation(ActionEvent event) {
    	 JFileChooser chooser=new JFileChooser();
    	 chooser.showOpenDialog(null);
    	 File f =chooser.getSelectedFile();
    	 String filename=f.getAbsolutePath();
    	 if(filename!=null) {
    	 path.setText(filename);
    }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

