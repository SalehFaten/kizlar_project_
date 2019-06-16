/**
 * Sample Skeleton for 'Mhomepage.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class CDMhomePage implements Initializable {

	@FXML // fx:id="image"
	private ImageView image; // Value injected by FXMLLoader

	@FXML // fx:id="CDchangePrice"
	private Button CDchangePrice; // Value injected by FXMLLoader

	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML // fx:id="email"
	private Label email; // Value injected by FXMLLoader

	@FXML // fx:id="CDacceptVersion"
	private Button CDacceptVersion; // Value injected by FXMLLoader

	@FXML // fx:id="outEmploeeHome"
	private Button outEmploeeHome; // Value injected by FXMLLoader
	static List<String> users_with_new_version;
	public void setimage(Image im) {
		image.setImage(im);
	}

	String TheEmail;

	public void set(String text) {
		email.setText(text);
		TheEmail = text;
	}

	@FXML
	void CDchangePrice(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePrice.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		ChangePrice change = loader.getController();
		Image im = new Image("images/background.jpg");
		change.setimage(im);
		change.SeTEmail(email.getText());
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
	}

	@FXML
	void CDacceptVersion(ActionEvent event) {
    	String message="CDacceptVersion#";
    	Connect.client.handleMessageFromClientUI(message);
    	String[] arr1=Connect.client.servermsg.split("@");
        if ("CDacceptVersion".equals(arr1[0]))
        {
        	String[] arr = (arr1[1]).split(",");
        	users_with_new_version = new ArrayList<String>();
        	for(int i=0; i<arr.length;i++) {
        		users_with_new_version.add(arr[i]);
        	}
			JOptionPane.showMessageDialog(null, "New Version is accepted");
        }
        else if ("CDNotacceptVersion".equals( Connect.client.servermsg))
        {
			JOptionPane.showMessageDialog(null, "cann't accept ");

        }   
	}

	@FXML
	void EditSettings(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		employeeHomePage employee = loader.getController();
		Image im = new Image("images/background.jpg");
		employee.setimage(im);
		employee.set(email.getText());
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
	}

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CompanyMhomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		CompanyMhomePage CM = loader.getController();
		Image im = new Image("images/background.jpg");
		CM.setimage(im);
		CM.set(TheEmail);
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
