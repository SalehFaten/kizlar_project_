/**
 * Sample Skeleton for 'employeeHomePage.fxml' Controller Class
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class employeeHomePage implements Initializable {

	@FXML // fx:id="employeeSeeMaps"
	private Button employeeSeeMaps; // Value injected by FXMLLoader

	@FXML // fx:id="addInterestingPlace"
	private Button addInterestingPlace; // Value injected by FXMLLoader

	@FXML // fx:id="createCity"
	private Button createCity; // Value injected by FXMLLoader

	@FXML // fx:id="editPath"
	private Button editPath; // Value injected by FXMLLoader

	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML // fx:id="editInterestingPlace"
	private Button editInterestingPlace; // Value injected by FXMLLoader

	@FXML // fx:id="addMapToCity"
	private Button addMapToCity; // Value injected by FXMLLoader

	@FXML // fx:id="addPath"
	private Button addPath; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private Label email; // Value injected by FXMLLoader
    
	@FXML // fx:id="outEmploeeHome"
	private Button outEmploeeHome; // Value injected by FXMLLoader

	@FXML
	void addPath(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("addpath.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
	}

	@FXML
	void editPath(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("EditPath.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
	}

	@FXML
	void addInterestingPlace(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("AddIneristingPlace.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
	}

	@FXML
	void editInterestingPlace(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("editInteristingPlace.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
	}

	@FXML
	void createCity(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("Createcity.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
	}

	@FXML
	void employeeSeeMaps(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("EmployeeSeeMaps.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
	}

	@FXML
	void addMapToCity(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("addMapToCity.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();

	}

	@FXML
	void LogOut(ActionEvent event) throws IOException {
		String message = "Employeelogout," + email.getText();
		Connect.client.handleMessageFromClientUI(message);
		Parent pane = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
	}

	@FXML
	void back(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void set(String text) {
		email.setText(text);
	}

}
