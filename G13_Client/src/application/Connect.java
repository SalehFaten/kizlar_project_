/**
 * Sample Skeleton for 'connect.fxml' Controller Class
 */

package application;

import client.ChatClient;
import common.ChatIF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import client .*;
import common.ChatIF;
public class Connect  implements ChatIF, Initializable{
	  static ChatClient client=null;
//		static ServerConsole Server = null;

	  @FXML // fx:id="image"
	    private ImageView image; // Value injected by FXMLLoader

	    @FXML // fx:id="port"
	    private TextField port; // Value injected by FXMLLoader

	    @FXML // fx:id="host"
	    private TextField host; // Value injected by FXMLLoader

	  public void setimage(Image im) {
			image.setImage(im);
	    }

    @FXML
    void connectserver(ActionEvent event) {
		/*
		 * if(port.getText().equals("")) { JOptionPane.showMessageDialog(null,
		 * "Please Enter port");
		 * 
		 * } else if (!port.getText().equals("5555")) {
		 * JOptionPane.showMessageDialog(null, "Please Enter correct port");
		 * 
		 * } else { ServerConsole Server = new
		 * ServerConsole(Integer.parseInt(port.getText()));
		 * JOptionPane.showMessageDialog(null, "You are connected as server");
		 * 
		 * }
		 */

    }
@FXML
    void connectclient(ActionEvent event) throws IOException {


    	if(host.getText().equals(""))
    	{
        	JOptionPane.showMessageDialog(null, "Please Enter host");

    	}
    	else {
    	  client = new ChatClient("doha",host.getText(),5555,this);
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
			 * Parent pane= FXMLLoader.load(getClass().getResource("Homepage.fxml")); Scene
			 * regist=new Scene(pane); Stage
			 * app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			 * app_stage.setScene(regist); app_stage.show();
			 */
    	}
    }

	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
