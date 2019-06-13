/**
 * Sample Skeleton for 'ShowAndPurchase.fxml' Controller Class
 */

package application;

import java.awt.TextArea;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ShowAndPurchase implements Initializable {

	@FXML // fx:id="InformationView"
    private TextArea InformationView; // Value injected by FXMLLoader

    @FXML // fx:id="ShowPreviousCity"
    private Button ShowPreviousCity; // Value injected by FXMLLoader

    @FXML // fx:id="ShowNextCity"
    private Button ShowNextCity; // Value injected by FXMLLoader

    @FXML // fx:id="Purchase"
    private Button Purchase; // Value injected by FXMLLoader

    @FXML // fx:id="ThePath"
    private TextField ThePath; // Value injected by FXMLLoader

    @FXML // fx:id="ChoosePath"
    private Button ChoosePath; // Value injected by FXMLLoader

    @FXML
    void ChoosePath(ActionEvent event) {

JFileChooser chooser=new JFileChooser();
chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
chooser.showSaveDialog(null);
File f=chooser.getSelectedFile();
String filename=f.getAbsolutePath();
if(filename!=null)
{
	ThePath.setText(filename);
}

    }

    @FXML
    void Purchase(ActionEvent event) {
    	String message = "Purchase," + CityId + ","+ ThePath.getText();
    	if (ThePath.getText()==null)
    	{
    		JOptionPane.showMessageDialog(null, "There is no where to download , please choose where to save!");
    	}
		Connect.client.handleMessageFromClientUI(message);
		if ("Purchased".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "You Got It !!");
		} else if ("CantPurchase".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "Purchasing Failed!");

		}
    }

    static String CityId;
    
    @FXML
    void ShowNextCity(ActionEvent event) {
         String message="ShowNextCity ,"  ;
         Connect.client.handleMessageFromClientUI(message);
         String[] TheMsg=Connect.client.servermsg.split("@");
 		if ("ShowNextCity".equals(Connect.client.servermsg)) {
 		
 			String[] TheText=TheMsg[2].split(".");
 			int i=0;
 			CityId=TheMsg[1];
 			while(i<TheText.length)
 			{
 				SetText(TheText[i]);
 			}
 		} else if ("CantShowNextCity".equals(Connect.client.servermsg)) {
 			JOptionPane.showMessageDialog(null, "Showing Next One Failed !");

 		}
    }

    @FXML
    void ShowPreviousCity(ActionEvent event) {
    	 String message="ShowPreviousCity ,"  ;
    
    Connect.client.handleMessageFromClientUI(message);
    String[] TheMsg=Connect.client.servermsg.split("@");
	if ("ShowPreviousCity".equals(TheMsg[0])) {
		if ((TheMsg[1]==null) || (TheMsg[2]==null))
		{
			JOptionPane.showMessageDialog(null, "Showing Previous One Failed ! ");
		}
		else
		{
		String[] TheText=TheMsg[2].split(".");
		int i=0;
		CityId=TheMsg[1];
		while(i<TheText.length)
		{
			SetText(TheText[i]);
		}
		}
	} 
	else if ("CantShowPrevoiousCity".equals(TheMsg[0])) {
		JOptionPane.showMessageDialog(null, "Showing Previous One Failed ! ");

	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void SetText(String information)
	{
		 InformationView.append(information);
		 InformationView.append("\n");
	}
	
}

