/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Connect;
import common.ChatIF;
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
import common .*;

public class signin  implements Initializable{

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader 
    
    @FXML // fx:id="password"
    private TextField password; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private TextField email; // Value injected by FXMLLoader
    
    public void setimage(Image im) {
		image.setImage(im);
    }
    @FXML
    void SignIn(ActionEvent event) throws IOException {
    	
    String Email=email.getText();
    String pass=password.getText();
    	String message="SignIn,"+email.getText()+","+password.getText();
    	if (Email == null || pass == null) {
			JOptionPane.showMessageDialog(null, "One or more files are empty!! ");
		} 
    	if (!email.getText().equals("")&&!password.getText().equals("")) {
        Connect.client.handleMessageFromClientUI(message);
        if("SignFailed".equals(Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "You are connected from another device !!");

        }
        else if ("NotFoundEmail".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "You are not registed !!");

		} 
        else if ("NotFoundPass".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "You entered uncorrect password !!");

   		} 
        else if ("SignIn".equals(Connect.client.servermsg)) {
        	if(email.getText().contains("@map.co.il"))
        	{
        		
					FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
					AnchorPane root = (AnchorPane) loader.load();
					employeeHomePage employee = loader.getController();
					employee.set(email.getText());
					Scene regist = new Scene(root);
					Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					app_stage.setScene(regist);
					app_stage.show();

        	}
        	else if(email.getText().contains("@mapcd.co.il"))
        	{
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
					 * Parent pane= FXMLLoader.load(getClass().getResource("MhomePage.fxml")); Scene
					 * log=new Scene(pane); Stage
					 * app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
					 * app_Stage.setScene(log); app_Stage.show();
					 */
        	}
        	else if(email.getText().contains("@mapcdm.co.il"))
        	{
           	 Parent pane= FXMLLoader.load(getClass().getResource("CompanyMhomePage.fxml"));
             Scene log=new Scene(pane);
             Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             app_Stage.setScene(log);
             app_Stage.show();
        	}
        	else {
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHomePage.fxml"));
				AnchorPane root = (AnchorPane) loader.load();
				UserHomePage user = loader.getController();
				user.set(email.getText());
				Scene regist = new Scene(root);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(regist);
				app_stage.show();
        	}
        }
 
    	}
    }
    	
        
    
    @FXML
    void back(ActionEvent event) throws IOException {
       	FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			Controller home = loader.getController();
    	    	Image im= new Image("images/world-map-background-copy.jpg");
    			home.setimage(im);
    			Scene regist = new Scene(root);
    			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    			app_stage.setScene(regist);
    			app_stage.show();
		/*
		 * Parent pane= FXMLLoader.load(getClass().getResource("Homepage.fxml")); Scene
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
