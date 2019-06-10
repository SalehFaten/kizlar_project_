package application;
	
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("connect.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();
		Connect connect = loader.getController();
    	Image im= new Image("images/connection.jpg");
		connect.setimage(im);
		Scene scene = new Scene( pane );
		// setting the stage
		primaryStage.setScene( scene );
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
