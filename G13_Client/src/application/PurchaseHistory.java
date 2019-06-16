
//
/**
 * Sample Skeleton for 'PurchaseHistoryPage.fxml' Controller Class
 */

package application;

import java.awt.TextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PurchaseHistory implements Initializable {

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="TEXTAREA"
    private TextArea TEXTAREA; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private Label email; // Value injected by FXMLLoader
String UserEmail;
    public void SeTEmail(String textmail)
    {
    	UserEmail=textmail;
    	email.setText(UserEmail);
    }
    public void setimage(Image im)
    {
    	image.setImage(im);
    }
    @FXML
    void Back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		UserHomePage UserPage = loader.getController();
		UserPage.set(UserEmail);
    	Image im= new Image("images/background.jpg");
		UserPage.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
    }
    public void setUserInfo()
	 {
		 String message="GetUserInfo#"+UserEmail;
		    Connect.client.handleMessageFromClientUI(message);
		    String msg=Connect.client.servermsg;
		    String [] SplitArray=msg.split("@");
		    if("Success".equals(SplitArray[0]))
		    {
		    	String[] Split2=SplitArray[1].split("$");
		        for (int i=0;i<Split2.length;i++)
		        {
		        	TEXTAREA.append(Split2[i]);
		        	TEXTAREA.append("\n");
		        }
		    }
		    else  if ("Failed".equals(SplitArray[0]))
		    	TEXTAREA.setText("There are no results!");		 
	 }	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

