package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("connect.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();
		Connect connect = loader.getController();
    	Image im= new Image("images/background.jpg");
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
