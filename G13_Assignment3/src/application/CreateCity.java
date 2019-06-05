/**
 * Sample Skeleton for 'CreateCity.fxml' Controller Class
 */

package application;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCity {

    @FXML // fx:id="Path"
    private Button Path; // Value injected by FXMLLoader

    @FXML // fx:id="path"
    private TextField path; // Value injected by FXMLLoader

    @FXML // fx:id="CityId1"
    private TextField CityName; // Value injected by FXMLLoader

    @FXML // fx:id="CityId"
    private TextField CityId; // Value injected by FXMLLoader

    @FXML // fx:id="VersionNum"
    private TextField VersionNum; // Value injected by FXMLLoader

    @FXML // fx:id="description"
    private TextField description; // Value injected by FXMLLoader

    @FXML // fx:id="MapId"
    private TextField MapId; // Value injected by FXMLLoader

    @FXML
    void back(ActionEvent event) throws IOException {
    	   Parent pane= FXMLLoader.load(getClass().getResource("employeeHomePage.fxml"));
           Scene log=new Scene(pane);
           Stage app_Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           app_Stage.setScene(log);
           app_Stage.show();
    }

    @FXML
    void Create(ActionEvent event) throws IOException {
    	String message="Create,"+CityName.getText()+","+CityId.getText()+","+MapId.getText()+","+VersionNum.getText()+","+description.getText()+","+path.getText();
        Connect.client.handleMessageFromClientUI(message);
        if (Connect.client.servermsg.equals("Created")) {
			JOptionPane.showMessageDialog(null,  "City Created Successfully!! ");

        }

    }

    @FXML
    void Path_dir(ActionEvent event) {
   	 JFileChooser chooser=new JFileChooser();
	 chooser.showOpenDialog(null);
	 File f =chooser.getSelectedFile();
	 String filename=f.getAbsolutePath();
	 if(filename!= null) {
	 path.setText(filename);
	 }

    }

}
