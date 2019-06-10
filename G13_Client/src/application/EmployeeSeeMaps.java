
/**
 * Sample Skeleton for 'EmployeeSeeMaps.fxml' Controller Class
 */

package application;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import com.mysql.cj.jdbc.Blob;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EmployeeSeeMaps {
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
    @FXML // fx:id="SearchMapId"
    private TextField SearchMapId; // Value injected by FXMLLoader

    @FXML // fx:id="imageview"
    private ImageView imageview; // Value injected by FXMLLoader

    @FXML // fx:id="Maptext"
    private TextArea Maptext; // Value injected by FXMLLoader

    @FXML
    void SearchMapId(ActionEvent event) {
    	
    		Connection conn = null;
    	    Statement stmt = null;
    	    Image image=null;
    	    String desc=null;

    		try {
    			Class.forName(JDBC_DRIVER);
    			conn = DriverManager.getConnection(DB_URL, USER, PASS);
    	//**whire sql here**//
    			if(SearchMapId.getText()==null)
    			{
    				return;
    			}
    			else
    			{
    			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
    			prep_stmt.setString(1, SearchMapId.getText());
    			ResultSet rs = prep_stmt.executeQuery();
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
    			imageview.setImage(image);
				Maptext.setText(desc);
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
    		
    	
//    	String message = "EmployeeSeeMapId," + SearchMapId.getText();
//		Connect.client.handleMessageFromClientUI(message);
//		String[] input = Connect.client.servermsg.split("@");
//		if ("MapPath".equals(input[0])) {
//			if (input[1] != null) {
//				String imagepath=input[1];
//					Image image = new Image("file:" + imagepath);
//					imageview.setImage(image);
//					Maptext.setText(input[2]);
//				}
//			}
		}

    

    @FXML
    void back(ActionEvent event) throws IOException {
    	Parent pane = FXMLLoader.load(getClass().getResource("EmployeeHomePage.fxml"));
		Scene log = new Scene(pane);
		Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_Stage.setScene(log);
		app_Stage.show();
    }

}



