/**
 * Sample Skeleton for 'PersonalInformationPage.fxml' Controller Class
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

public class PersonalInformatiolnPage implements Initializable {

	@FXML // fx:id="Search"
	private ImageView Search; // Value injected by FXMLLoader

	@FXML // fx:id="Tel"
	private TextField Tel; // Value injected by FXMLLoader

	@FXML // fx:id="FirstName"
	private TextField FirstName; // Value injected by FXMLLoader

	@FXML // fx:id="LastName"
	private TextField LastName; // Value injected by FXMLLoader

	@FXML // fx:id="ID"
	private TextField ID; // Value injected by FXMLLoader

	@FXML // fx:id="VisaNum"
	private TextField VisaNum; // Value injected by FXMLLoader

	@FXML // fx:id="Password"
	private TextField Password; // Value injected by FXMLLoader

	@FXML // fx:id="CVV"
	private TextField CVV; // Value injected by FXMLLoader

	@FXML // fx:id="VisaDate"
	private TextField VisaDate; // Value injected by FXMLLoader

	@FXML // fx:id="Email"
	private TextField Email; // Value injected by FXMLLoader

	@FXML // fx:id="BackToUserHome"
	private Button BackToUserHome; // Value injected by FXMLLoader
	String MyEmail = null;

	public void setimage(Image im) {
		Search.setImage(im);
	}

	@FXML
	void BackToUserHome(ActionEvent event) throws IOException {
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

	void CVVEdit(ActionEvent event) {
		String cvv = CVV.getText();
		if (cvv.length() != 3 || (!cvv.matches("[0-9]+"))) {
			JOptionPane.showMessageDialog(null, "Please enter correct cvv number ");
		} else {
			CVV.setText(cvv);
			String message = "Edit#" + "CVV#" + MyEmail + "#" + cvv;
			Connect.client.handleMessageFromClientUI(message);
			if ("Editing Done".equals(Connect.client.servermsg))
				JOptionPane.showMessageDialog(null, "Editing Done Successfully ");
			else if ("Editing Failed!".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Editing Failed! ");
			}
		}
	}

	@FXML
	void EmailEdit(ActionEvent event) {
		String newEmail = Email.getText();
		if (!(newEmail.contains("@hotmail.com")) && !(newEmail.contains("@gmail.com"))) {
			JOptionPane.showMessageDialog(null, "Please enter correct mail ");
		} else {
			Email.setText(newEmail);

			String message = "Edit#" + "Email#" + MyEmail + "#" + newEmail;
			MyEmail = newEmail;
			Connect.client.handleMessageFromClientUI(message);
			if ("Editing Done".equals(Connect.client.servermsg))
				JOptionPane.showMessageDialog(null, "Editing Done Successfully ");
			else if ("Editing Failed!".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Editing Failed! ");
			}
		}

	}

	@FXML
	void FirstNameEdit(ActionEvent event) {
		String firstname = FirstName.getText();
		FirstName.setText(firstname);
		String message = "Edit#" + "FirstName#" + MyEmail + "#" + firstname;
		Connect.client.handleMessageFromClientUI(message);
		if ("Editing Done".equals(Connect.client.servermsg))
			JOptionPane.showMessageDialog(null, "Editing Done Successfully ");
		else if ("Editing Failed!".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "Editing Failed! ");
		}
	}

	@FXML
	void IdEdit(ActionEvent event) {
		String id = ID.getText();
		if (id.length() != 9 || (!id.matches("[0-9]+"))) {
			JOptionPane.showMessageDialog(null, "Please enter correct id ");
		} else {
			ID.setText(id);
			String message = "Edit#" + "ID#" + MyEmail + "#" + id;
			Connect.client.handleMessageFromClientUI(message);
			if ("Editing Done".equals(Connect.client.servermsg))
				JOptionPane.showMessageDialog(null, "Editing Done Successfully ");
			else if ("Editing Failed!".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Editing Failed! ");
			}
		}
	}

	@FXML
	void LastNameEdit(ActionEvent event) {
		String last = LastName.getText();

		LastName.setText(last);
		String message = "Edit#" + "LastName#" + MyEmail + "#" + last;
		Connect.client.handleMessageFromClientUI(message);
		if ("Editing Done".equals(Connect.client.servermsg))
			JOptionPane.showMessageDialog(null, "Editing Done Successfully ");
		else if ("Editing Failed!".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "Editing Failed! ");
		}
	}

	@FXML
	void PasswordEdit(ActionEvent event) {
		String pass = Password.getText();

		Password.setText(pass);
		String message = "Edit#" + "password#" + MyEmail + "#" + pass;
		Connect.client.handleMessageFromClientUI(message);
		if ("Editing Done".equals(Connect.client.servermsg))
			JOptionPane.showMessageDialog(null, "Editing Done Successfully ");
		else if ("Editing Failed!".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "Editing Failed! ");
		}
	}

	@FXML
	void TelEdit(ActionEvent event) {
		String tel = Tel.getText();
		if (tel.length() != 10 || !(tel.matches("(05[0-9]+)"))) {
			JOptionPane.showMessageDialog(null, "Please enter correct phone number ");
		} else {
			Tel.setText(tel);
			String message = "Edit#" + "Tel#" + MyEmail + "#" + tel;
			Connect.client.handleMessageFromClientUI(message);
			if ("Editing Done".equals(Connect.client.servermsg))
				JOptionPane.showMessageDialog(null, "Editing Done Successfully ");
			else if ("Editing Failed!".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Editing Failed! ");
			}
		}

	}

	@FXML
	void VisaDateEdit(ActionEvent event) {
		String date = VisaDate.getText();
		if (date.length() != 5 || (!date.matches("(1[0-2]|0[1-9])/(2[0-9])"))) {
			JOptionPane.showMessageDialog(null, "Please enter correct date number ");
		} else {
			VisaDate.setText(date);
			String message = "Edit#" + "Date#" + MyEmail + "#" + date;
			Connect.client.handleMessageFromClientUI(message);
			if ("Editing Done".equals(Connect.client.servermsg))
				JOptionPane.showMessageDialog(null, "Editing Done Successfully ");
			else if ("Editing Failed!".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Editing Failed! ");
			}
		}

	}

	@FXML
	void VisaNumEdit(ActionEvent event) {
		String Visa = VisaNum.getText();
		if (Visa.length() != 16 || (!Visa.matches("[0-9]+"))) {
			JOptionPane.showMessageDialog(null, "Please enter correct visa number ");
		} else {
			VisaNum.setText(Visa);
			String message = "Edit#" + "VisaNum#" + MyEmail + "#" + Visa;
			Connect.client.handleMessageFromClientUI(message);
			if ("Editing Done".equals(Connect.client.servermsg))
				JOptionPane.showMessageDialog(null, "Editing Done Successfully ");
			else if ("Editing Failed!".equals(Connect.client.servermsg)) {
				JOptionPane.showMessageDialog(null, "Editing Failed! ");
			}
		}

	}

	public void setinfo(String[] text) {
		Email.setText(text[0]);
		Password.setText(text[1]);
		FirstName.setText(text[2]);
		LastName.setText(text[3]);
		Tel.setText(text[4]);
		VisaNum.setText(text[5]);
		CVV.setText(text[6]);
		VisaDate.setText(text[7]);
		ID.setText(text[8]);

	}

	public void SeTEmail(String theEmail) {
		// TODO Auto-generated method stub
		MyEmail = theEmail;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
