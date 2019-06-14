/**
 * Sample Skeleton for 'AcceptPrice.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AcceptPrice implements Initializable{
	

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="cityName"
    private TextField cityName; // Value injected by FXMLLoader

    @FXML // fx:id="acceptPrice"
    private CheckBox acceptPrice; // Value injected by FXMLLoader

    @FXML // fx:id="nextCityPrice"
    private Button nextCityPrice; // Value injected by FXMLLoader

    @FXML // fx:id="currentPrice"
    private TextField currentPrice; // Value injected by FXMLLoader

    @FXML // fx:id="prevCityPrice"
    private Button prevCityPrice; // Value injected by FXMLLoader

    @FXML // fx:id="dontAcceptPrice"
    private CheckBox dontAcceptPrice; // Value injected by FXMLLoader

    @FXML // fx:id="newPrice"
    private TextField newPrice; // Value injected by FXMLLoader
    
    static String CityId;
    
	 public void setimage(Image im) {
	image.setImage(im);
}
	 public void setCityId(String city)
	 {
		 CityId=city;
	 }
	 
		public void SetcityName(String city_Name)
		{
			cityName.setText(city_Name);
			//InformationView.appendText("\n");
		}
		public void SetcurrentPrice(String current_Price)
		{
			currentPrice.setText(current_Price);
			//InformationView.appendText("\n");
		}
		public void SetnewPrice(String new_Price)
		{
			newPrice.setText(new_Price);
			//InformationView.appendText("\n");
		}

    @FXML
    void prevCityPrice(ActionEvent event) {
    	
      	 String message="ShowPreviousCityPrice,"  ;
         Connect.client.handleMessageFromClientUI(message);
         String[] TheMsg=Connect.client.servermsg.split("@");
      	if ("ShowPreviousCity".equals(TheMsg[0])) {
      		if ((TheMsg[1]==null))
      		{
      			JOptionPane.showMessageDialog(null, "Showing Previous One Failed ! ");
      		}
      		else
      		{
      		String[] res = TheMsg[1].split(",");
      		//String[] TheText=TheMsg[2].split("#");
           SetcityName( res[0]);
           SetcurrentPrice( res[1]);
           SetnewPrice( res[2]);
      		CityId=res[0];

      		}
      	} 
      	else if ("CantShowPrevoiousCity".equals(TheMsg[0])) {
      		JOptionPane.showMessageDialog(null, "Showing Previous One Failed ! ");
      	}   	

    }



    @FXML
    void nextCityPrice(ActionEvent event) {

        String message="ShowNextCity,"  ;
        Connect.client.handleMessageFromClientUI(message);
        String[] TheMsg=Connect.client.servermsg.split("@");
  		if ("ShowNextCity".equals(TheMsg[0])) {
  			String[] res = TheMsg[1].split(",");
  			//String[] TheText=TheMsg[2].split("#");
            SetcityName( res[0]);
            SetcurrentPrice( res[1]);
            SetnewPrice( res[2]);
       		CityId=res[0];
  		} else if ("CantShowNextCity".equals(TheMsg[0])) {
  			JOptionPane.showMessageDialog(null, "Showing Next One Failed !");

  		}   	
    	
    }


    @FXML
    void finishAcceptPrice(ActionEvent event) {

    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
