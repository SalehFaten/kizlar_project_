/**
 * Sample Skeleton for 'AcceptPrice.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AcceptPrice implements Initializable {

	@FXML // fx:id="image"
	private ImageView image; // Value injected by FXMLLoader

	@FXML // fx:id="cityName"
	private TextField cityName; // Value injected by FXMLLoader

	@FXML // fx:id="acceptPrice"
	private CheckBox acceptPrice; // Value injected by FXMLLoader

	@FXML // fx:id="currentPrice"
	private TextField currentPrice; // Value injected by FXMLLoader

	@FXML // fx:id="dontAcceptPrice"
	private CheckBox dontAcceptPrice; // Value injected by FXMLLoader

	@FXML // fx:id="newPrice"
	private TextField newPrice; // Value injected by FXMLLoader

	static String CityId;
	static List<String> name = new ArrayList<String>();

	public void setimage(Image im) {
		image.setImage(im);
	}
	String MyEmail = null;
	public void SeTEmail(String theEmail) {
		// TODO Auto-generated method stub
		MyEmail = theEmail;
	}
	public void setCityId(String city) {
		CityId = city;
	}

	public void SetcityName(String city_Name) {
		cityName.setText(city_Name);
		// InformationView.appendText("\n");
	}

	public void SetcurrentPrice(String current_Price) {
		currentPrice.setText(current_Price);
		// InformationView.appendText("\n");
	}

	public void SetnewPrice(String new_Price) {
		newPrice.setText(new_Price);
		// InformationView.appendText("\n");
	}


	@FXML
	void prevCityPrice(ActionEvent event) {
		String message = "ShowPreviousCityPrice#";
		Connect.client.handleMessageFromClientUI(message);
		String[] TheMsg = Connect.client.servermsg.split("@");
		if ("ShowPreviousCity".equals(TheMsg[0])) {
			if ((TheMsg[1] == null)) {
				JOptionPane.showMessageDialog(null, "Showing Previous One Failed ! ");
			} else {
				String[] res = TheMsg[1].split(",");
				// String[] TheText=TheMsg[2].split("#");
				SetcityName(res[0]);
				SetcurrentPrice(res[1]);
				SetnewPrice(res[2]);
				CityId = res[3];
				if (acceptPrice.isSelected() && dontAcceptPrice.isSelected()) {
					JOptionPane.showMessageDialog(null, "You have to chose just one !");

				} else if (acceptPrice.isSelected()) {
					if (!name.contains(CityId)) {
						name.add(CityId);
					}
				} else if (dontAcceptPrice.isSelected()) {
					if (name.contains(CityId)) {
						name.remove(CityId);
					}
				}

			}
		} else if ("CantShowPrevoiousCity".equals(TheMsg[0])) {
			JOptionPane.showMessageDialog(null, "Showing Previous One Failed ! ");
			if (acceptPrice.isSelected() && dontAcceptPrice.isSelected()) {
				JOptionPane.showMessageDialog(null, "You have to chose just one !");

			} else if (acceptPrice.isSelected()) {
				if (!name.contains(CityId)) {
					name.add(CityId);
				}
			} else if (dontAcceptPrice.isSelected()) {
				if (name.contains(CityId)) {
					name.remove(CityId);
				}
			}
		}

	}

	@FXML
	void nextCityPrice(ActionEvent event) {
		String message = "ShowNextCityPrice#";
		Connect.client.handleMessageFromClientUI(message);
		String[] TheMsg = Connect.client.servermsg.split("@");
		if ("ShowNextCityPrice".equals(TheMsg[0])) {
			String[] res = TheMsg[1].split(",");
			// String[] TheText=TheMsg[2].split("#");
			SetcityName(res[0]);
			SetcurrentPrice(res[1]);
			SetnewPrice(res[2]);
			CityId = res[3];
//        		name.add(CityId);
			if (acceptPrice.isSelected() && dontAcceptPrice.isSelected()) {
				JOptionPane.showMessageDialog(null, "You have to chose just one !");

			} else if (acceptPrice.isSelected()) {
				if (!name.contains(CityId)) {
					name.add(CityId);
				}
			} else if (dontAcceptPrice.isSelected()) {
				if (name.contains(CityId)) {
					name.remove(CityId);
				}
			}

		} else if ("CantShowNextCity".equals(TheMsg[0])) {
			JOptionPane.showMessageDialog(null, "Showing Next One Failed !");
			if (acceptPrice.isSelected() && dontAcceptPrice.isSelected()) {
				JOptionPane.showMessageDialog(null, "You have to chose just one !");

			} else if (acceptPrice.isSelected()) {
				if (!name.contains(CityId)) {
					name.add(CityId);
				}
			} else if (dontAcceptPrice.isSelected()) {
				if (name.contains(CityId)) {
					name.remove(CityId);
				}
			}

		}

	}

	@FXML
	void finishAcceptPrice(ActionEvent event) throws IOException {
		if (acceptPrice.isSelected() && dontAcceptPrice.isSelected()) {
			JOptionPane.showMessageDialog(null, "You have to chose just one !");

		} else if (acceptPrice.isSelected()) {
			if (!name.contains(CityId)) {
				name.add(CityId);
			}
		} else if (dontAcceptPrice.isSelected()) {
			if (!name.contains(CityId)) {
				name.add(CityId);
			}
		}
		String message = "finishAcceptPrice#";
		Iterator<String> iterator = name.iterator();
		while (iterator.hasNext()) {
			message = message + "," + iterator.next();

		}
		Connect.client.handleMessageFromClientUI(message);
		if ("acceptPriceFromManager".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "Accepting new prices finished successfully");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CompanyMhomePage.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			CompanyMhomePage CM = loader.getController();
			Image im = new Image("images/background.jpg");
			CM.setimage(im);
			CM.set(MyEmail);
			Scene regist = new Scene(root);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(regist);
			app_stage.show();
		} else if ("NotacceptPriceFromManager".equals(Connect.client.servermsg)) {
			JOptionPane.showMessageDialog(null, "Cann't Accept new prices");
		}

	}

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CompanyMhomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		CompanyMhomePage CM = loader.getController();
		Image im = new Image("images/background.jpg");
		CM.setimage(im);
		CM.set(MyEmail);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
