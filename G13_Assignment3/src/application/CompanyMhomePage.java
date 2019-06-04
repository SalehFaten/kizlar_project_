/**
 * Sample Skeleton for 'CompanyMhomePage.fxml' Controller Class
 */

package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CompanyMhomePage {

    @FXML // fx:id="seeUserReports"
    private Button seeUserReports; // Value injected by FXMLLoader

    @FXML // fx:id="acceptPrice"
    private Button acceptPrice; // Value injected by FXMLLoader

    @FXML // fx:id="CMacceptVersion"
    private Button CMacceptVersion; // Value injected by FXMLLoader

    @FXML // fx:id="outCDMHome"
    private Button outCDMHome; // Value injected by FXMLLoader

    @FXML
    void acceptPrice(ActionEvent event) {

    }

    @FXML
    void seeUserReports(ActionEvent event) {

    }

    @FXML
    void CMacceptVersion(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) throws IOException {
    	  Parent pane= FXMLLoader.load(getClass().getResource("CDMhomePage.fxml"));
          Scene log=new Scene(pane);
          Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
          app_Stage.setScene(log);
          app_Stage.show();
    }

    @FXML
    void LogOut(ActionEvent event) throws IOException {
    	  Parent pane= FXMLLoader.load(getClass().getResource("HomePage.fxml"));
          Scene log=new Scene(pane);
          Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
          app_Stage.setScene(log);
          app_Stage.show();
    }

}
