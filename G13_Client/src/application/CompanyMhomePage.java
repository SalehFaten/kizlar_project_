/**
 * Sample Skeleton for 'CompanyMhomePage.fxml' Controller Class
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CompanyMhomePage implements Initializable{

    @FXML // fx:id="seeUserReports"
    private Button seeUserReports; // Value injected by FXMLLoader

    @FXML // fx:id="acceptPrice"
    private Button acceptPrice; // Value injected by FXMLLoader

    @FXML // fx:id="CMacceptVersion"
    private Button CMacceptVersion; // Value injected by FXMLLoader

    @FXML // fx:id="outCDMHome"
    private Button outCDMHome; // Value injected by FXMLLoader
	  @FXML // fx:id="image"
	   private ImageView image; // Value injected by FXMLLoader
	 public void setimage(Image im) {
			image.setImage(im);
	    }
    @FXML
    void acceptPrice(ActionEvent event) throws IOException {
    	
    	
    	
//    	String msg = "show,";
//		Connect.client.handleMessageFromClientUI(msg);
//		String[] Msg = Connect.client.servermsg.split("@");
//		if ("show".equals(Msg[0])) {
//			if ((Msg[1] == null) || (Msg[2] == null)) {
//				JOptionPane.showMessageDialog(null, "Showing  Failed ! ");
//			} else {
//				String[] res = Msg[2].split("#");
//				FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAndPurchase.fxml"));
//				AnchorPane root = (AnchorPane) loader.load();
//				ShowAndPurchase SHOW = loader.getController();
//				for (int i = 0; i < res.length; i++) {
//					SHOW.SetText(res[i]);
//				}
//                SHOW.setCityId(Msg[1]);
//				Image im = new Image("images/background.jpg");
//				SHOW.setimage(im);
//				Scene regist = new Scene(root);
//				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//				app_stage.setScene(regist);
//				app_stage.show();
//			}
//		} else if ("CantShowCity".equals(Msg[0])) {
//			JOptionPane.showMessageDialog(null, "Cann't show city ! ");
//
//		}   	
    	String msg="AcceptPrice,";
    	Connect.client.handleMessageFromClientUI(msg);
    	String[] Msg = Connect.client.servermsg.split("@");
    	if ("show".equals(Msg[0])) {
			if ((Msg[1] == null)) {
				JOptionPane.showMessageDialog(null, "Showing  Failed ! ");
			} else {
				String[] res = Msg[1].split(",");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceptPrice.fxml"));
				AnchorPane root = (AnchorPane) loader.load();
				AcceptPrice SHOW = loader.getController();
//				for (int i = 0; i < res.length; i++) {
//					SHOW.SetText(res[i]);
//				}
                SHOW.SetcityName( res[0]);
                SHOW.SetcurrentPrice( res[1]);
                SHOW.SetnewPrice( res[2]);
				Image im = new Image("images/background.jpg");
				SHOW.setimage(im);
				Scene regist = new Scene(root);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(regist);
				app_stage.show();
			}
		} else if ("CantShowCity".equals(Msg[0])) {
			JOptionPane.showMessageDialog(null, "Cann't show new prices ! ");

		}   	
    	

    }

    @FXML
    void seeUserReports(ActionEvent event) {

    }

    @FXML
    void CMacceptVersion(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) throws IOException {
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
		 * Parent pane= FXMLLoader.load(getClass().getResource("CDMhomePage.fxml"));
		 * Scene log=new Scene(pane); Stage
		 * app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		 * app_Stage.setScene(log); app_Stage.show();
		 */
    }

    @FXML
    void LogOut(ActionEvent event) throws IOException {
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
		 * Parent pane= FXMLLoader.load(getClass().getResource("HomePage.fxml")); Scene
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
