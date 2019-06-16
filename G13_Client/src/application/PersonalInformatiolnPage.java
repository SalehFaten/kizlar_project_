/**
 * Sample Skeleton for 'PersonalInformationPage.fxml' Controller Class
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PersonalInformatiolnPage  implements Initializable{

    @FXML // fx:id="Search"
    private ImageView Search; // Value injected by FXMLLoader

    @FXML // fx:id="Tel"
    private TextField Tel; // Value injected by FXMLLoader

    @FXML // fx:id="FirstName"
    private TextField FirstName; // Value injected by FXMLLoader

    @FXML // fx:id="LastName"
    private TextField LastName; // Value injected by FXMLLoader

    @FXML // fx:id="ID"
    private TextField ID; // Value injected by FXMLLoader

    @FXML // fx:id="VisaNum"
    private TextField VisaNum; // Value injected by FXMLLoader

    @FXML // fx:id="Password"
    private TextField Password; // Value injected by FXMLLoader

    @FXML // fx:id="CVV"
    private TextField CVV; // Value injected by FXMLLoader

    @FXML // fx:id="VisaDate"
    private TextField VisaDate; // Value injected by FXMLLoader

    @FXML // fx:id="Email"
    private TextField Email; // Value injected by FXMLLoader

    @FXML // fx:id="BackToUserHome"
    private Button BackToUserHome; // Value injected by FXMLLoader
    String MyEmail=null;

    public void setimage(Image im)
    {
    	Search.setImage(im);
    }
    
    @FXML
    void BackToUserHome(ActionEvent event) throws IOException {
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		UserHomePage user = loader.getController();
    	Image im= new Image("images/background.jpg");
		user.setimage(im);
		user.set(MyEmail);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
    }

    @FXML
    
    void CVVEdit(ActionEvent event) {
    	String cvv = CVV.getText();
    	if (cvv.length() != 3 || (!cvv.matches("[0-9]+"))) {
			JOptionPane.showMessageDialog(null, "Please enter correct cvv number ");}
			else {
        CVV.setText(cvv);
        String message="Edit," +"CVV,"+ MyEmail  +"," +cvv;
        Connect.client.handleMessageFromClientUI(message);
       if( Connect.client.servermsg=="Editing Done")
    	   JOptionPane.showMessageDialog(null, "Editing Done Successfully ");
       else   JOptionPane.showMessageDialog(null, "Editing Failed! ");
    }
    	}

    @FXML
    void EmailEdit(ActionEvent event) {
        String newEmail = Email.getText();
       Email.setText(newEmail); 
    }

    @FXML
    void FirstNameEdit(ActionEvent event) {
    	String firstname = FirstName.getText();
    	FirstName.setText(firstname);
    }

    @FXML
    void IdEdit(ActionEvent event) {
    	String id = ID.getText();
    	ID.setText(id);
    	
    }

    @FXML
    void LastNameEdit(ActionEvent event) {
    	String last = LastName.getText();
    	LastName.setText(last);
    }

    @FXML
    void PasswordEdit(ActionEvent event) {
    	String pass = Password.getText();
    	Password.setText(pass);
    }

    @FXML
    void TelEdit(ActionEvent event) {
    	String tel = Tel.getText();
    	Tel.setText(tel);

    }

    @FXML
    void VisaDateEdit(ActionEvent event) {
    	String date = VisaDate.getText();
VisaDate.setText(date);
    }

    @FXML
    void VisaNumEdit(ActionEvent event) {
    	String Visa = VisaNum.getText();
    	VisaNum.setText(Visa);

    }
   public void setinfo(String [] text) 
   {
   	Email.setText(text[0]);
   	Password.setText(text[1]);
   	FirstName.setText(text[2]);
   	LastName.setText(text[3]);
   	Tel.setText(text[4]);
   	VisaNum.setText(text[5]);
   	CVV.setText(text[6]);
   	VisaDate.setText(text[7]);
   	ID.setText(text[8]);

   }
    public void SeTEmail(String theEmail) {
		// TODO Auto-generated method stub
		MyEmail=theEmail;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}