/**
 * Sample Skeleton for 'Mhomepage.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class CDMhomePage implements Initializable{

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="CDchangePrice"
    private Button CDchangePrice; // Value injected by FXMLLoader

    @FXML // fx:id="back"
    private Button back; // Value injected by FXMLLoader

    @FXML // fx:id="CDacceptVersion"
    private Button CDacceptVersion; // Value injected by FXMLLoader

    @FXML // fx:id="outEmploeeHome"
    private Button outEmploeeHome; // Value injected by FXMLLoader
    static List<String> users_with_new_version;
	 public void setimage(Image im) {
	image.setImage(im);
}
    @FXML
    void CDchangePrice(ActionEvent event) throws IOException {
	   Parent pane= FXMLLoader.load(getClass().getResource("ChangePrice.fxml"));
      Scene log=new Scene(pane);
      Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
      app_Stage.setScene(log);
      app_Stage.show();
    }

    @FXML
    void CDacceptVersion(ActionEvent event) {
    	String message="CDacceptVersion,";
    	Connect.client.handleMessageFromClientUI(message);
        if ("CDacceptVersion".equals( Connect.client.servermsg.split("@")[0]))
        {
        	String[] arr = (Connect.client.servermsg.split("@")[1]).split(",");
        	users_with_new_version = new ArrayList<String>();
        	for(int i=0; i<arr.length;i++) {
        		users_with_new_version.add(arr[i]);
        	}
			JOptionPane.showMessageDialog(null, "New Version is accepted");
        }
        else if ("CDNotacceptVersion".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "cann't accept ");

        }        
			
    }

    @FXML
    void EditSettings(ActionEvent event) throws IOException {
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
    void back(ActionEvent event) {

    }

    @FXML
    void LogOut(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Controller home = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		home.setimage(im);
		home.setarrayversion(users_with_new_version);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

///**
// * Sample Skeleton for 'CDMhomePage.fxml' Controller Class
// */
//
//package application;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
//
//public class CDMhomePage implements Initializable{
//    @FXML // fx:id="image"
//    private ImageView image; // Value injected by FXMLLoader
//
//    @FXML // fx:id="CDchangePrice"
//    private Button CDchangePrice; // Value injected by FXMLLoader
//
//    @FXML // fx:id="outEmploeeHome"
//    private Button outEmploeeHome; // Value injected by FXMLLoader
//
//    @FXML // fx:id="CDacceptVersion"
//    private Button CDacceptVersion; // Value injected by FXMLLoader
//
//
//	 public void setimage(Image im) {
//			image.setImage(im);
//	    }
//    @FXML
//    void LogOut(ActionEvent event) throws IOException {
//       	FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
//    			AnchorPane root = (AnchorPane) loader.load();
//    			Controller home = loader.getController();
//    	    	Image im= new Image("images/world-map-background-copy.jpg");
//    			home.setimage(im);
//    			Scene regist = new Scene(root);
//    			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//    			app_stage.setScene(regist);
//    			app_stage.show();
//		/*
//		 * Parent pane= FXMLLoader.load(getClass().getResource("Homepage.fxml")); Scene
//		 * log=new Scene(pane); Stage
//		 * app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//		 * app_Stage.setScene(log); app_Stage.show();
//		 */
//
//    }
//
//    @FXML
//    void CDchangePrice(ActionEvent event) throws IOException {
//  	   Parent pane= FXMLLoader.load(getClass().getResource("ChangePrice.fxml"));
//       Scene log=new Scene(pane);
//       Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//       app_Stage.setScene(log);
//       app_Stage.show();
//
//    }
//
//    @FXML
//    void CDacceptVersion(ActionEvent event) {
//
//    }
//
//    @FXML
//    void EditSettings(ActionEvent event) throws IOException {
//     	FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
//		AnchorPane root = (AnchorPane) loader.load();
//		employeeHomePage employee = loader.getController();
//    	Image im= new Image("images/world-map-background-copy.jpg");
//		employee.setimage(im);
//		Scene regist = new Scene(root);
//		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		app_stage.setScene(regist);
//		app_stage.show();
//		/*
//		 * Parent pane=
//		 * FXMLLoader.load(getClass().getResource("EmployeeHomePage.fxml")); Scene
//		 * log=new Scene(pane); Stage
//		 * app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//		 * app_Stage.setScene(log); app_Stage.show();
//		 */
//
//    }
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
//


