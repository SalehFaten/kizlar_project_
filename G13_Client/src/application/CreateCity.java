/**
 * Sample Skeleton for 'CreateCity.fxml' Controller Class
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateCity implements Initializable {

	@FXML // fx:id="Path"
	private Button Path; // Value injected by FXMLLoader

	@FXML // fx:id="path"
	private TextField path; // Value injected by FXMLLoader

	@FXML // fx:id="CityId1"
	private TextField CityName; // Value injected by FXMLLoader

	@FXML // fx:id="CityId"
	private TextField CityId; // Value injected by FXMLLoader

	@FXML // fx:id="description"
	private TextField description; // Value injected by FXMLLoader

	@FXML // fx:id="MapId"
	private TextField MapId; // Value injected by FXMLLoader
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
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		employeeHomePage employee = loader.getController();
		Image im = new Image("images/background.jpg");
		employee.setimage(im);
		employee.set(MyEmail);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
	}

	@FXML
	void Create(ActionEvent event) throws IOException {
		String message = "Create#" + CityName.getText() + "#" + CityId.getText() + "#" + MapId.getText() + "#"
				+ description.getText() + "#" + path.getText();
		Connect.client.handleMessageFromClientUI(message);
		if ("Created".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "City Created Successfully!! ");

		} else if ("NotCreated".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "City can't created!! ");

		}

	}

	@FXML
	void Path_dir(ActionEvent event) {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		String filename = f.getAbsolutePath();
		if (filename != null) {
			path.setText(filename);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
