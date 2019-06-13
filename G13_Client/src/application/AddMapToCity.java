/**
 * Sample Skeleton for 'addMapToCity.fxml' Controller Class
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddMapToCity implements Initializable{
	@FXML // fx:id="Path"
	private Button Path; // Value injected by FXMLLoader

	@FXML // fx:id="path"
	private TextField path; // Value injected by FXMLLoader

	@FXML // fx:id="CityId"
	private TextField CityId; // Value injected by FXMLLoader

	@FXML // fx:id="description"
	private TextField description; // Value injected by FXMLLoader

	@FXML // fx:id="addMapToCity"
	private Button addMapToCity; // Value injected by FXMLLoader

	@FXML // fx:id="MapId"
	private TextField MapId; // Value injected by FXMLLoader

	  @FXML // fx:id="image"
	    private ImageView image; // Value injected by FXMLLoader
	  
	 public void setimage(Image im) {
			image.setImage(im);
	    }
	@FXML
	void add(ActionEvent event) throws IOException {
		String message = "AddMap," + CityId.getText() + "," + MapId.getText() + "," + description.getText() + ","
				+ path.getText();
		Connect.client.handleMessageFromClientUI(message);
		if ("AddMap".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "Adding Map Finished Successfully");
		} else if ("NotAdd".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "Couldn't Adding Map Finished Successfully");

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

	}

	@FXML
	void Path_dir(ActionEvent event) {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		String filename = f.getAbsolutePath();
		if (filename != null) {
			path.setText(filename);
		}

//FileChooser filechooser=new FileChooser();
//filechooser.setTitle("Open File Dialog");
//Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//filechooser.showOpenDialog(app_Stage);
//if(filechooser !=null)
//{
//	 JFileChooser chooser=new JFileChooser();
//	 File f =chooser.getSelectedFile();
//	 String filename=f.getAbsolutePath();
//	path.setText(filename);
//
//
//    }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
