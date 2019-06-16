/**
 * Sample Skeleton for 'Report.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Report  implements Initializable  {

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="CitiesReport"
    private Button CitiesReport; // Value injected by FXMLLoader

    @FXML // fx:id="DateFrom"
    private TextField DateFrom; // Value injected by FXMLLoader

    @FXML // fx:id="CityID"
    private TextField CityID; // Value injected by FXMLLoader
    @FXML // fx:id="DateTo"
    private TextField DateTo; // Value injected by FXMLLoader
    
    @FXML // fx:id="email"
    private Label email; // Value injected by FXMLLoader
String DTo=DateTo.getText();
String DFrom=DateFrom.getText();
String CID=CityID.getText();
String MEmail;
    @FXML
    void back(ActionEvent event) throws IOException {
    	if (MEmail.contains("@mapcd.co.il"))
    	{
    	  FXMLLoader loader = new FXMLLoader(getClass().getResource("MhomePage"));
  		AnchorPane root = (AnchorPane) loader.load();
  		Report CDMhomePage = loader.getController();
  		    Image im= new Image("images/background.jpg");
  		  CDMhomePage .setimage(im);
  		CDMhomePage.SeTEmail(MEmail);
  		Scene regist = new Scene(root);
  		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
  		app_stage.setScene(regist);
  		app_stage.show();
    }
    	else if (MEmail.contains("@mapcdm.co.il"))
    	{
      	  FXMLLoader loader = new FXMLLoader(getClass().getResource("CompanyMhomePage"));
    		AnchorPane root = (AnchorPane) loader.load();
    		Report CMhomePage = loader.getController();
    		    Image im= new Image("images/background.jpg");
    		    CMhomePage .setimage(im);
    		    CMhomePage.SeTEmail(MEmail);
    		Scene regist = new Scene(root);
    		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		app_stage.setScene(regist);
    		app_stage.show();
      }
    }
    private void setimage(Image im) {
		// TODO Auto-generated method stub
    	image.setImage(im);
	}
	@FXML
    void CityReport(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CityReport"));
		AnchorPane root = (AnchorPane) loader.load();
		CityReport CReport = loader.getController();
		    Image im= new Image("images/background.jpg");
		    CReport .setimage(im);
		    CReport.SeTEmail(MEmail);
		    CReport.getCityID(CID);
		    CReport.getDate(DFrom,DTo);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
    }

    @FXML
    void CitiesReport(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CitiesReports"));
		AnchorPane root = (AnchorPane) loader.load();
		CitiesReports CsReport = loader.getController();
		    Image im= new Image("images/background.jpg");
		    CsReport .setimage(im);
		    CsReport.SeTEmail(MEmail);
		   // CsReport.getCityName(CName);
		    CsReport.getDate(DFrom,DTo);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
    }

    @FXML
    void UserReport(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersReports"));
		AnchorPane root = (AnchorPane) loader.load();
		CitiesReports UserReport = loader.getController();
		    Image im= new Image("images/background.jpg");
		    UserReport .setimage(im);
		    UserReport.SeTEmail(MEmail);
		   // CsReport.getCityName(CName);
		    UserReport.getDate(DFrom,DTo);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
    }
 public void  SeTEmail(String text)
 {
    email.setText(text);
	MEmail = text;
 }
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}
}