/**
 * Sample Skeleton for 'ClientSearch.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ClientSearch implements Initializable {

	@FXML // fx:id="area"
	private TextArea area; // Value injected by FXMLLoader

	@FXML // fx:id="image"
	private ImageView image; // Value injected by FXMLLoader
	String MyEmail = null;

	public void SeTEmail(String theEmail) {
		// TODO Auto-generated method stub
		MyEmail = theEmail;
	}

	public void setimage(Image im) {
		image.setImage(im);
	}

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		UserHomePage home = loader.getController();
		Image im = new Image("images/background.jpg");
		home.setimage(im);
		home.set(MyEmail);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		area.clear();
	}

	public void set(String text) {
		area.appendText(text);
		area.appendText("\n");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
