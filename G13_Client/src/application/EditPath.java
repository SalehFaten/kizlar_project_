/**
 * Sample Skeleton for 'EditPath.fxml' Controller Class
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
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class EditPath implements Initializable {

	@FXML // fx:id="image"
	private ImageView image; // Value injected by FXMLLoader

	@FXML // fx:id="enterPathId"
	private TextField enterPathId; // Value injected by FXMLLoader

	@FXML // fx:id="editDiscription"
	private TextField editDiscription; // Value injected by FXMLLoader

	@FXML // fx:id="editNewPath"
	private TextField editNewPath; // Value injected by FXMLLoader

	public void setimage(Image im) {
		image.setImage(im);
	}

	String MyEmail = null;

	public void SeTEmail(String theEmail) {
		// TODO Auto-generated method stub
		MyEmail = theEmail;
	}

	@FXML
	void Editpath(ActionEvent event) throws IOException {
		String message = "EditPath#" + enterPathId.getText() + "#" + editNewPath.getText();
		if (enterPathId.getText() == null) {
			JOptionPane.showMessageDialog(null, "the field path Id is empty");

		} else if (editNewPath.getText() == null) {
			JOptionPane.showMessageDialog(null, "the field new path is empty");
		} else {
			Connect.client.handleMessageFromClientUI(message);
			if ("EditPath".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Edit path finished successfully ");

			} else if ("NotEditPath".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Coudn't edit ");

			}
		}
	}

	@FXML
	void Editdesc(ActionEvent event) throws IOException {
		String message = "EditPathdisc#" + enterPathId.getText() + "#" + editDiscription.getText();
		if (enterPathId.getText() == null) {
			JOptionPane.showMessageDialog(null, "the field path Id is empty");

		} else if (editDiscription.getText() == null) {
			JOptionPane.showMessageDialog(null, "the field descreption is empty");

		} else {
			Connect.client.handleMessageFromClientUI(message);
			if ("editPathdisc".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Edit description finished successfully ");

			} else if ("NotEditPathdisc".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Coudn't edit ");

			}
		}
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
	void maplocation(ActionEvent event) {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		String filename = f.getAbsolutePath();
		if (filename != null) {
			editNewPath.setText(filename);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	void removepath(ActionEvent event) {
		String message = "RemovePath#" + enterPathId.getText();
		if (enterPathId.getText() == null) {
			JOptionPane.showMessageDialog(null, "the field path Id is empty");

		} else {
			Connect.client.handleMessageFromClientUI(message);
			if ("removing path done".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "successfully deleted");

			} else if ("removing path failed!!".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Coudn't remove this path ");

			}
		}
	}

}
