/**
 * Sample Skeleton for 'purchase.fxml' Controller Class
 */

package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class purchase implements Initializable{

    @FXML // fx:id="back_purchase"
    private Button back_purchase; // Value injected by FXMLLoader

    @FXML // fx:id="purchase"
    private Button purchase; // Value injected by FXMLLoader

    @FXML // fx:id="search_map"
    private TextField search_map; // Value injected by FXMLLoader

    @FXML // fx:id="purchaseOf_page_purchase"
    private AnchorPane purchaseOf_page_purchase; // Value injected by FXMLLoader

    @FXML
    void purchase(ActionEvent event) {

    }

    @FXML
    void search_map(ActionEvent event) {

    }

    @FXML
    void back_purchase(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
