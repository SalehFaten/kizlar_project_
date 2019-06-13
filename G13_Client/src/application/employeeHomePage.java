/**
 * Sample Skeleton for 'employeeHomePage.fxml' Controller Class
 */

package application;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Blob;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class employeeHomePage implements Initializable {
	 /////*******databasee******////
	static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	// update USER, PASS and DB URL according to credentials provided by the
	// website:
	// https://remotemysql.com/
	// in future get those hardcoede string into separated config file.
	static private final String DB = "0ajpgfocAS";
	static private final String DB_URL = "jdbc:mysql://remotemysql.com/" + DB + "?useSSL=false";
	static private final String USER = "0ajpgfocAS";
	static private final String PASS = "bxsdfZ2BQu";
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;
	////*******************************************////

	@FXML // fx:id="employeeSeeMaps"
	private Button employeeSeeMaps; // Value injected by FXMLLoader

	@FXML // fx:id="addInterestingPlace"
	private Button addInterestingPlace; // Value injected by FXMLLoader

	@FXML // fx:id="createCity"
	private Button createCity; // Value injected by FXMLLoader

	@FXML // fx:id="editPath"
	private Button editPath; // Value injected by FXMLLoader

	@FXML // fx:id="back"
	private Button back; // Value injected by FXMLLoader

	@FXML // fx:id="editInterestingPlace"
	private Button editInterestingPlace; // Value injected by FXMLLoader

	@FXML // fx:id="addMapToCity"
	private Button addMapToCity; // Value injected by FXMLLoader

	@FXML // fx:id="addPath"
	private Button addPath; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private Label email; // Value injected by FXMLLoader
    
	@FXML // fx:id="outEmploeeHome"
	private Button outEmploeeHome; // Value injected by FXMLLoader
	  @FXML // fx:id="image"
	    private ImageView image; // Value injected by FXMLLoader
	 public void setimage(Image im) {
			image.setImage(im);
	    }
	@FXML
	void addPath(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("addpath.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		AddPath addp = loader.getController();
    	Image im= new Image("images/background.jpg");
		addp.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();

	}

	@FXML
	void editPath(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPath.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		EditPath ep = loader.getController();
    	Image im= new Image("images/background.jpg");
		ep.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane = FXMLLoader.load(getClass().getResource("EditPath.fxml")); Scene
		 * log = new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
	}

	@FXML
	void addInterestingPlace(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddIneristingPlace.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		AddIneristingPlace ap = loader.getController();
    	Image im= new Image("images/background.jpg");
		ap.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane =
		 * FXMLLoader.load(getClass().getResource("AddIneristingPlace.fxml")); Scene log
		 * = new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
	}

	@FXML
	void editInterestingPlace(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("editInteristingPlace.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		EditInteristingPlace epl = loader.getController();
    	Image im= new Image("images/background.jpg");
		epl.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
	
	}

	@FXML
	void createCity(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("Createcity.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		CreateCity CC = loader.getController();
    	Image im= new Image("images/background.jpg");
		CC.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
	}

	@FXML
	void employeeSeeMaps(ActionEvent event) throws IOException {
	 	Connection conn = null;
	    Statement stmt = null;
	    Image image=null;
	    String desc=null;
	    String Mapid=null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
	//**whire sql here**//
			List<String> MapId = new ArrayList<String>();
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map ");
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				MapId.add(rs.getString("MapId"));
			}
			rs.close();
			prep_stmt.close();
			if(MapId.isEmpty()==false) {
				
		     Mapid=MapId.get(0);
			 prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
			prep_stmt.setString(1, Mapid);
			 rs = prep_stmt.executeQuery();
			while (rs.next()) {
				Blob blob = (Blob) rs.getBlob("Im");  
				InputStream in = blob.getBinaryStream();  
				BufferedImage im = ImageIO.read(in);
				 image =SwingFXUtils.toFXImage(im, null) ; 
				PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
				prep_stmt1.setString(1, rs.getString("CityId"));
				ResultSet rs1 = prep_stmt1.executeQuery();
				String CityName=null;
				while (rs1.next()) {
					CityName=rs1.getString("CityName");
				}
				rs1.close();
				prep_stmt1.close();
			 desc= "This map is map of "+CityName+ " \n" +"This map is "+rs.getString("description")+ " map.";
			}
			prep_stmt.close();
			rs.close();
			conn.close();
	

			}
			
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("SQLException: " + se.getMessage());
			System.out.println("SQLState: " + se.getSQLState());
			System.out.println("VendorError: " + se.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeSeeMaps.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		EmployeeSeeMaps SEE = loader.getController();
    	Image im= new Image("images/background.jpg");
		SEE.setimage(im);
		SEE.setMapId(desc);
		SEE.settext(Mapid);
		SEE.setim(image);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane =
		 * FXMLLoader.load(getClass().getResource("EmployeeSeeMaps.fxml")); Scene log =
		 * new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */
	}

	@FXML
	void addMapToCity(ActionEvent event) throws IOException {
	 	FXMLLoader loader = new FXMLLoader(getClass().getResource("addMapToCity.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		AddMapToCity ATC = loader.getController();
    	Image im= new Image("images/background.jpg");
		ATC.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
		/*
		 * Parent pane = FXMLLoader.load(getClass().getResource("addMapToCity.fxml"));
		 * Scene log = new Scene(pane); Stage app_Stage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow(); app_Stage.setScene(log);
		 * app_Stage.show();
		 */

	}

	@FXML
	void LogOut(ActionEvent event) throws IOException {
		
		String message = "Employeelogout," + email.getText();
		Connect.client.handleMessageFromClientUI(message);
		if(Connect.client.servermsg!=null && "Employeelogout".equals(Connect.client.servermsg)) {
		JOptionPane.showMessageDialog(null, "You are logout ");
	   	FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
				AnchorPane root = (AnchorPane) loader.load();
				Controller home = loader.getController();
		    	Image im= new Image("images/background.jpg");
				home.setimage(im);
				Scene regist = new Scene(root);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(regist);
				app_stage.show();
		
		}
	}

	@FXML
	void back(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void set(String text) {
		email.setText(text);
	}

}
