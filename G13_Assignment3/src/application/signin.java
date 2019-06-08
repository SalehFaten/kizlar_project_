/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import application.Connect;
import common.ChatIF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import common .*;

public class signin  {

    @FXML // fx:id="password"
    private TextField password; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private TextField email; // Value injected by FXMLLoader
    
    @FXML
    void SignIn(ActionEvent event) throws IOException {
    	String message="SignIn,"+email.getText()+","+password.getText();
    	if (!email.getText().equals("")&&!password.getText().equals("")) {
        Connect.client.handleMessageFromClientUI(message);
        if ("SignIn".equals(Connect.client.servermsg)) {
        	Connect.client.servermsg=null;
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
           	 Parent pane= FXMLLoader.load(getClass().getResource("CDMhomePage.fxml"));
             Scene log=new Scene(pane);
             Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             app_Stage.setScene(log);
             app_Stage.show();
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
    	 Parent pane= FXMLLoader.load(getClass().getResource("UserHomePage.fxml"));
         Scene log=new Scene(pane);
         Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
         app_Stage.setScene(log);
         app_Stage.show();}
        	}
        }
    	else
    	{
			JOptionPane.showMessageDialog(null, "One or more files are empty!! ");
    	}
    	}
    	
        
    
    @FXML
    void back(ActionEvent event) throws IOException {
        Parent pane= FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene log=new Scene(pane);
        Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        app_Stage.setScene(log);
        app_Stage.show();

    }


}
