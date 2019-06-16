/**
 * Sample Skeleton for 'ShowAndPurchase.fxml' Controller Class
 */

package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShowAndPurchase implements Initializable {

	@FXML // fx:id="image"
	private ImageView image; // Value injected by FXMLLoader

	@FXML // fx:id="sub"
	private CheckBox sub; // Value injected by FXMLLoader

	@FXML // fx:id="Purchase"
	private Button Purchase; // Value injected by FXMLLoader

	@FXML // fx:id="ChoosePath"
	private Button ChoosePath; // Value injected by FXMLLoader

	@FXML // fx:id="ShowPreviousCity"
	private Button ShowPreviousCity; // Value injected by FXMLLoader

	@FXML // fx:id="ShowNextCity"
	private Button ShowNextCity; // Value injected by FXMLLoader

	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML // fx:id="InformationView"
	private TextArea InformationView; // Value injected by FXMLLoader

	@FXML // fx:id="onetime"
	private CheckBox onetime; // Value injected by FXMLLoader

	@FXML // fx:id="ThePath"
	private TextField ThePath; // Value injected by FXMLLoader
	String MyEmail = null;

	static String CityId;

	public void setimage(Image im) {
		image.setImage(im);
	}

	public void SeTEmail(String theEmail) {
		// TODO Auto-generated method stub
		MyEmail = theEmail;
	}

	public void setCityId(String city) {
		CityId = city;
	}

	@FXML
	void ShowPreviousCity(ActionEvent event) {
		String message = "ShowPreviousCity#";
		Connect.client.handleMessageFromClientUI(message);
		String[] TheMsg = Connect.client.servermsg.split("@");
		if ("ShowPreviousCity".equals(TheMsg[0])) {
			if ((TheMsg[1] == null) || (TheMsg[2] == null)) {
				JOptionPane.showMessageDialog(null, "Showing Previous One Failed ! ");
			} else {
				String[] TheText = TheMsg[2].split("#");
				CityId = TheMsg[1];
				InformationView.clear();
				for (int i = 0; i < TheText.length; i++) {
					SetText(TheText[i]);
				}
			}
		} else if ("CantShowPrevoiousCity".equals(TheMsg[0])) {
			JOptionPane.showMessageDialog(null, "Showing Previous One Failed ! ");
		}

	}

	@FXML
	void ShowNextCity(ActionEvent event) {
		String message = "ShowNextCity#";
		Connect.client.handleMessageFromClientUI(message);
		String[] TheMsg = Connect.client.servermsg.split("@");
		if ("ShowNextCity".equals(TheMsg[0])) {

			String[] TheText = TheMsg[2].split("#");
			CityId = TheMsg[1];
			InformationView.clear();
			for (int i = 0; i < TheText.length; i++) {
				SetText(TheText[i]);
			}
		} else if ("CantShowNextCity".equals(TheMsg[0])) {
			JOptionPane.showMessageDialog(null, "Showing Next One Failed !");

		}
	}

	@FXML
	void Purchase(ActionEvent event) {
		String message = null;
		if (sub.isSelected() && onetime.isSelected()) {
			JOptionPane.showMessageDialog(null, "You have to chose just one !");

		} else if (sub.isSelected()) {
			message = "Purchase#" + CityId + "#" + MyEmail + "#" + "sub";
			Connect.client.handleMessageFromClientUI(message);
			if ("Purchased".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "You Got It !!");

			} else if ("CantPurchase".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Purchasing Failed!");

			}

		} else if (onetime.isSelected()) {
			message = "Purchase#" + CityId + "#" + MyEmail + "#" + "onetime";
			Connect.client.handleMessageFromClientUI(message);
			if ("Purchased".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "You Got It !!");
			} else if ("CantPurchase".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Purchasing Failed!");

			}

		}

	}

	@FXML
	void ChoosePath(ActionEvent event) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//
//    	chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.showSaveDialog(null);
		File f = chooser.getSelectedFile();
		if (f != null) {
			String filename = f.getAbsolutePath();
			if (filename != null) {
				ThePath.setText(filename);
			}
		}

	}

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		UserHomePage user = loader.getController();
		Image im = new Image("images/background.jpg");
		user.setimage(im);
		user.set(MyEmail);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();

	}

	@FXML
	void download(ActionEvent event) {
		String msg = "download#" + CityId + "#" + ThePath.getText() + "#" + MyEmail;
		if (ThePath.getText().equals("null")) {
			JOptionPane.showMessageDialog(null, "please enter where to save the maps!");

		}
		Connect.client.handleMessageFromClientUI(msg);
		if ("download".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "downloaded successfully ");
		} else if ("Cantdownload".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "download Failed!");

		} else if ("onetimedownloadFaild".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "You cann't downlaod this city morethan one time !");

		}
	}

	public void SetText(String information) {
		InformationView.appendText(information);
		InformationView.appendText("\n");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
