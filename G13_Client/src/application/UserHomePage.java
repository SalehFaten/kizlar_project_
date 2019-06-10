
/**
 * Sample Skeleton for 'UserHomePage.fxml' Controller Class
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class UserHomePage implements Initializable{

    @FXML // fx:id="UserHomePage"
    private AnchorPane UserHomePage; // Value injected by FXMLLoader

    @FXML // fx:id="PurchaseOfUser"
    private Button PurchaseOfUser; // Value injected by FXMLLoader

    @FXML // fx:id="path"
    private TextField path; // Value injected by FXMLLoader

    @FXML // fx:id="SearchForUser"
    private Button SearchForUser; // Value injected by FXMLLoader

    @FXML // fx:id="SeeMaps"
    private Button SeeMaps; // Value injected by FXMLLoader


    @FXML // fx:id="MapId"
    private TextField MapId; // Value injected by FXMLLoader
    
    @FXML // fx:id="email"
    private Label email; // Value injected by FXMLLoader
    
    @FXML // fx:id="LogOut_UserHomePage"
    private Button LogOut_UserHomePage; // Value injected by FXMLLoader

    @FXML
    void purchase(ActionEvent event) {

    }

    @FXML
    void LogOut_UserHomePage(ActionEvent event) throws IOException {
		String message = "userlogout," + email.getText();
		Connect.client.handleMessageFromClientUI(message);
		if(Connect.client.servermsg!=null && Connect.client.servermsg.equals("userlogout")) {
		JOptionPane.showMessageDialog(null, "You are logout ");
		Parent pane = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
		}
    }

    @FXML
    void SeeMaps(ActionEvent event) {

    }

    @FXML
    void SearchForUser(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {
      	String message="saveMap,"+path.getText();
        Connect.client.handleMessageFromClientUI(message);
    }

    @FXML
    void get(ActionEvent event) {
    	 JFileChooser chooser=new JFileChooser();
    	 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//    	 chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    	 chooser.showSaveDialog(null);
    	 File f =chooser.getSelectedFile();
    	 String filename=f.getAbsolutePath();

    	 if(filename!=null) {
    	 path.setText(filename);
    }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void set(String text) {
		email.setText(text);
	}
}






















///**
// * Sample Skeleton for 'UserHomePage.fxml' Controller Class
// */
//
//package application;
//
//
//import java.io.IOException;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
//public class UserHomePage {
//
//    @FXML // fx:id="UserHomePage"
//    private AnchorPane UserHomePage; // Value injected by FXMLLoader
//
//    @FXML // fx:id="PurchaseOfUser"
//    private Button PurchaseOfUser; // Value injected by FXMLLoader
//
//    @FXML // fx:id="SeeMaps"
//    private Button SeeMaps; // Value injected by FXMLLoader
//
//    @FXML // fx:id="LogOut_UserHomePage"
//    private Button LogOut_UserHomePage; // Value injected by FXMLLoader
//
//    @FXML
//    void purchase(ActionEvent event) throws IOException {
//    	 Parent pane= FXMLLoader.load(getClass().getResource("purchase.fxml"));
//         Scene log=new Scene(pane);
//         Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//         app_Stage.setScene(log);
//         app_Stage.show();
//    	
//    }
//
//    @FXML
//    void LogOut_UserHomePage(ActionEvent event) throws IOException {
//    	Parent pane= FXMLLoader.load(getClass().getResource("signin.fxml"));
//        Scene log=new Scene(pane);
//        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//        app_Stage.setScene(log);
//        app_Stage.show();
//
//    }
//
//    @FXML
//    void SeeMaps(ActionEvent event) throws IOException {
//    	Parent pane= FXMLLoader.load(getClass().getResource("SeeMaps.fxml"));
//        Scene log=new Scene(pane);
//        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//        app_Stage.setScene(log);
//        app_Stage.show();
//
//    }
//    
//
//    @FXML
//    void SearchForUser(ActionEvent event) throws IOException {
//    	Parent pane= FXMLLoader.load(getClass().getResource("SeeMaps.fxml"));
//        Scene log=new Scene(pane);
//        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//        app_Stage.setScene(log);
//        app_Stage.show();
//
//    }
//
//
//}
