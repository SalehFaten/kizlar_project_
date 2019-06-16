/**
 /**
 * Sample Skeleton for 'ChangePrice.fxml' Controller Class
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

public class ChangePrice implements Initializable {

	@FXML // fx:id="enterCityId"
	private TextField enterCityId; // Value injected by FXMLLoader

	@FXML // fx:id="enterNewPrice"
	private TextField enterNewPrice; // Value injected by FXMLLoader

	@FXML // fx:id="sendPriceToCompanyM"
	private Button sendPriceToCompanyM; // Value injected by FXMLLoader
	@FXML // fx:id="image"
	private ImageView image; // Value injected by FXMLLoader

	public void setimage(Image im) {
		image.setImage(im);
	}

	String MyEmail = null;

	public void SeTEmail(String theEmail) {
		// TODO Auto-generated method stub
		MyEmail = theEmail;
	}

	@FXML
	void sendPriceToCompanyM(ActionEvent event) throws IOException {
		String cityId = enterCityId.getText();
		String newPrice = enterNewPrice.getText();
		String message = "sendPriceToManager#" + cityId + "#" + newPrice;
		if (newPrice == null) {
			JOptionPane.showMessageDialog(null, "You Must enter new price if you want to change");
		} else if (cityId == null) {
			JOptionPane.showMessageDialog(null, "You Must enter city id to change price");
		} else {
			Connect.client.handleMessageFromClientUI(message);
			// String msgg=Connect.client.servermsg;
			if ("sendPriceToManager".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "sending Price To Manager Done");

				FXMLLoader loader = new FXMLLoader(getClass().getResource("Mhomepage.fxml"));
				AnchorPane root = (AnchorPane) loader.load();
				CDMhomePage employee = loader.getController();
				Image im = new Image("images/world-map-background-copy.jpg");
				employee.setimage(im);
				Scene regist = new Scene(root);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(regist);
				app_stage.show();

			} else if ("NotsendPriceToManager".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "cann't send!! ");

			}
		}

	}

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Mhomepage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		CDMhomePage employee = loader.getController();
		Image im = new Image("images/background.jpg");
		employee.setimage(im);
		employee.set(MyEmail);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();

	}

	@FXML
	void showprice(ActionEvent event) {
		String cityId = enterCityId.getText();
		String message = "showPrice#" + cityId;
		if (cityId == null) {
			JOptionPane.showMessageDialog(null, "You Must enter city id to change price");
		} else {
			Connect.client.handleMessageFromClientUI(message);
			String[] msgg = Connect.client.servermsg.split("@");
			if ("dontShowPrice".equals(msgg[0])) {
				JOptionPane.showMessageDialog(null, "showing price failed");
			}
			// else if("-2".equals(msgg)) {
			// JOptionPane.showMessageDialog(null, "There is no such city id ");
			// }
			else if ("showPrice".equals(msgg[0])) {
				JOptionPane.showMessageDialog(null, "the current price is" + msgg[1]);
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
