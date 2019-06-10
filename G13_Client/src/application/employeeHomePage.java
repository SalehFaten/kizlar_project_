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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
	  @FXML // fx:id="image"
	    private ImageView image; // Value injected by FXMLLoader
	 public void setimage(Image im) {
			image.setImage(im);
	    }
	@FXML
	void addPath(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("addpath.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		AddPath addp = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		addp.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane = FXMLLoader.load(getClass().getResource("addpath.fxml")); Scene
		 * log = new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
	}

	@FXML
	void editPath(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPath.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		EditPath ep = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		ep.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane = FXMLLoader.load(getClass().getResource("EditPath.fxml")); Scene
		 * log = new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
	}

	@FXML
	void addInterestingPlace(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddIneristingPlace.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		AddIneristingPlace ap = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		ap.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane =
		 * FXMLLoader.load(getClass().getResource("AddIneristingPlace.fxml")); Scene log
		 * = new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
	}

	@FXML
	void editInterestingPlace(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("editInteristingPlace.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		EditInteristingPlace epl = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		epl.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane =
		 * FXMLLoader.load(getClass().getResource("editInteristingPlace.fxml")); Scene
		 * log = new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
	}

	@FXML
	void createCity(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("Createcity.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		CreateCity CC = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		CC.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane = FXMLLoader.load(getClass().getResource("Createcity.fxml"));
		 * Scene log = new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
	}

	@FXML
	void employeeSeeMaps(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeSeeMaps.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		EmployeeSeeMaps SEE = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		SEE.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane =
		 * FXMLLoader.load(getClass().getResource("EmployeeSeeMaps.fxml")); Scene log =
		 * new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
	}

	@FXML
	void addMapToCity(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("addMapToCity.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		AddMapToCity ATC = loader.getController();
    	Image im= new Image("images/world-map-background-copy.jpg");
		ATC.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane = FXMLLoader.load(getClass().getResource("addMapToCity.fxml"));
		 * Scene log = new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */

	}

	@FXML
	void LogOut(ActionEvent event) throws IOException {
		
		String message = "Employeelogout," + email.getText();
		Connect.client.handleMessageFromClientUI(message);
		if(Connect.client.servermsg!=null && Connect.client.servermsg.equals("Employeelogout")) {
		JOptionPane.showMessageDialog(null, "You are logout ");
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
			 * Parent pane = FXMLLoader.load(getClass().getResource("Homepage.fxml")); Scene
			 * log = new Scene(pane); Stage app_Stage = (Stage) ((Node)
			 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
			 * app_Stage.show();
			 */
		}
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
