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
    	if (Email.equals("")|| pass.equals("")) {
			JOptionPane.showMessageDialog(null, "One or more files are empty!! ");
		} 
    	else {
        Connect.client.handleMessageFromClientUI(message);
//        if(Connect.client.servermsg!=null && "SignFailed".equals(Connect.client.servermsg))
//        {
//			JOptionPane.showMessageDialog(null, "You are connected from another device !!");
//			Connect.client.servermsg=null;
//
//        }
        if (Connect.client.servermsg!=null &&"NotFoundEmail".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "You are not registed !!");
			Connect.client.servermsg=null;

		} 
        else if (Connect.client.servermsg!=null &&"NotFoundPass".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "You entered uncorrect password !!");
			Connect.client.servermsg=null;

   		} 
        else if (Connect.client.servermsg!=null &&"SignIn".equals(Connect.client.servermsg)) {
        	if(email.getText().contains("@map.co.il"))
        	{
        		
					FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
					AnchorPane root = (AnchorPane) loader.load();
					employeeHomePage employee = loader.getController();
					employee.set(email.getText());
				 	Image im= new Image("images/background.jpg");
	        		employee.setimage(im);
					Scene regist = new Scene(root);
					Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					app_stage.setScene(regist);
					app_stage.show();
					Connect.client.servermsg=null;

        	}
        	else if(email.getText().contains("@mapcd.co.il"))
        	{
             	FXMLLoader loader = new FXMLLoader(getClass().getResource("Mhomepage.fxml"));
        		AnchorPane root = (AnchorPane) loader.load();
        		CDMhomePage employee = loader.getController();
            	Image im= new Image("images/background.jpg");
        		employee.setimage(im);
        		Scene regist = new Scene(root);
        		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		app_stage.setScene(regist);
        		app_stage.show();
    			Connect.client.servermsg=null;
        	}
        	else if(email.getText().contains("@mapcdm.co.il"))
        	{
           	 Parent pane= FXMLLoader.load(getClass().getResource("CompanyMhomePage.fxml"));
             Scene log=new Scene(pane);
             Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             app_Stage.setScene(log);
             app_Stage.show();
 			Connect.client.servermsg=null;
        	}
        	else {
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHomePage.fxml"));
				AnchorPane root = (AnchorPane) loader.load();
				UserHomePage user = loader.getController();
				user.set(email.getText());
				Image im= new Image("images/background.jpg");
				user.setimage(im);
				Scene regist = new Scene(root);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(regist);
				app_stage.show();
				Connect.client.servermsg=null;

        	}
        }
    	}
 
    	}
    
    	
        
    
    @FXML
    void back(ActionEvent event) throws IOException {
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
