
/**
 * Sample Skeleton for 'addpath.fxml' Controller Class
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
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddPath implements Initializable{
    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="enterPathId"
    private TextField enterPathId; // Value injected by FXMLLoader


    @FXML // fx:id="enterDiscription"
    private TextField enterDiscription; // Value injected by FXMLLoader

    @FXML // fx:id="enterPathToNewPath"
    private TextField enterPathToNewPath; // Value injected by FXMLLoader
    
    @FXML // fx:id="entermapId"
    private TextField entermapId; // Value injected by FXMLLoader

	 public void setimage(Image im) {
			image.setImage(im);
	    }
    @FXML
    void AddPathbtn(ActionEvent event) throws IOException {
    	String message="AddPath,"+entermapId.getText()+","+enterPathId.getText()+","+enterDiscription.getText()+","+enterPathToNewPath.getText();
        Connect.client.handleMessageFromClientUI(message);
        if ("addPath".equals(Connect.client.servermsg)) {
        	JOptionPane.showMessageDialog(null, "Adding Map Finished Successfully");
  
    }
        else if ("NotAddPath".equals(Connect.client.servermsg))
        {
        	JOptionPane.showMessageDialog(null, "Coudn't add");

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
    }

    @FXML
    void maplocation(ActionEvent event) {
   	 JFileChooser chooser=new JFileChooser();
   	 chooser.showOpenDialog(null);
   	 File f =chooser.getSelectedFile();
   	 String filename=f.getAbsolutePath();
   	 if(filename!=null) {
    enterPathToNewPath.setText(filename);
    }
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}



