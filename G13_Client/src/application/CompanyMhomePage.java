
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CompanyMhomePage implements Initializable {

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="seeUserReports"
    private Button seeUserReports; // Value injected by FXMLLoader

    @FXML // fx:id="acceptPrice"
    private Button acceptPrice; // Value injected by FXMLLoader

    @FXML // fx:id="CMacceptVersion"
    private Button CMacceptVersion; // Value injected by FXMLLoader

    @FXML // fx:id="outCDMHome"
    private Button outCDMHome; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private Label email; // Value injected by FXMLLoader


	public void setimage(Image im) {
		image.setImage(im);
	}
	String TheEmail;

	public void set(String text) {
		email.setText(text);
		TheEmail = text;
	}


	@FXML
	void acceptPrice(ActionEvent event) throws IOException {
		String msg = "AcceptPrice#";
		Connect.client.handleMessageFromClientUI(msg);
		String[] Msg = Connect.client.servermsg.split("@");
		if ("show".equals(Msg[0])) {
			if (Msg[1].equals("null")) {
				JOptionPane.showMessageDialog(null, "There is no new prices ");
			} else {
				String[] res = Msg[1].split(",");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceptPrice.fxml"));
				AnchorPane root = (AnchorPane) loader.load();
				AcceptPrice SHOW = loader.getController();
//				for (int i = 0; i < res.length; i++) {
//					SHOW.SetText(res[i]);
//				}
				SHOW.SetcityName(res[0]);
				SHOW.SetcurrentPrice(res[1]);
				SHOW.SetnewPrice(res[2]);
				SHOW.setCityId(res[3]);
				SHOW.SeTEmail(TheEmail);
				Image im = new Image("images/background.jpg");
				SHOW.setimage(im);
				Scene regist = new Scene(root);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(regist);
				app_stage.show();
			}
		} else if ("Can't Show Price".equals(Msg[0])) {
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
		Image im = new Image("images/background.jpg");
		employee.setimage(im);
		employee.set(TheEmail);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
	}

    @FXML
    void LogOut(ActionEvent event) throws IOException {
		String message = "Employeelogout#" + email.getText();
		Connect.client.handleMessageFromClientUI(message);
		if (Connect.client.servermsg != null && "Employeelogout".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "You are logout ");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Controller home = loader.getController();
			Image im = new Image("images/background.jpg");
			home.setimage(im);
			Scene regist = new Scene(root);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(regist);
			app_stage.show();

		}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
