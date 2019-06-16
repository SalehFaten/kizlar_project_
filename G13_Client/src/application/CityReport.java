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

public class CityReport  implements Initializable  {
	/**
	 * Sample Skeleton for 'ViewReports.fxml' Controller Class
	 */



	
    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

 

    @FXML // fx:id="email"
    private Label email; // Value injected by FXMLLoader

	    @FXML // fx:id="ViewReport"
	    private TextArea ViewReport; // Value injected by FXMLLoader

	    
	    String Date1;
	    String Date2;
	    String cID;
	 public void getDate(String date1,String date2)
	 {
		 Date1=date1;
		 Date2=date2;
		 
	 }
	 public void getCityID(String Text)
	 {
		 cID=Text;
		 
	 }
	 public void SetCityInfo()
	 {
		 String message="GetCityInfo#"+cID+"#"+Date1+"#"+Date2;
		    Connect.client.handleMessageFromClientUI(message);
		    String msg=Connect.client.servermsg;
		    String [] SplitArray=msg.split("@");
  		    if("Success".equals(SplitArray[0]))
  		    {
  		    	String[] Split2=SplitArray[1].split(",");
  		        for (int i=0;i<Split2.length;i++)
  		        {
  		        	ViewReport.append(Split2[i]);
  		        	ViewReport.append("\n");
  		        }
  		    }
  		    else  if ("Failed".equals(SplitArray[0]))
  		    	ViewReport.setText("There are no results!");
  		    	
	 }
	 
	    @FXML
	    void back(ActionEvent event) throws IOException {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Report.fxml"));
	AnchorPane root = (AnchorPane) loader.load();
	Report report = loader.getController();
	    Image im= new Image("images/background.jpg");
	image.setImage(im);
	report.SeTEmail(email.getText());
	Scene reports = new Scene(root);
	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	app_stage.setScene(reports);
	app_stage.show();
	    

	    }
		public void setimage(javafx.scene.image.Image im) {
			// TODO Auto-generated method stub
			image.setImage(im);
		}
		public void SeTEmail(String mEmail) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}

	
}
