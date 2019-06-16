/**
 * Sample Skeleton for 'EmployeeSeeMaps.fxml' Controller Class
 */

package application;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
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

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class EmployeeSeeMaps implements Initializable {
///// *******databasee******////
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
	//// *******************************************////
	String MyEmail = null;
	static int place = 0;

	@FXML // fx:id="SearchMapId"
	private TextField SearchMapId; // Value injected by FXMLLoader

	@FXML // fx:id="image"
	private ImageView image; // Value injected by FXMLLoader

	@FXML // fx:id="imageview"
	private ImageView imageview; // Value injected by FXMLLoader

	@FXML // fx:id="Maptext"
	private TextArea Maptext; // Value injected by FXMLLoader

	@FXML
	void SearchMapId(ActionEvent event) {

	}

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeHomePage.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		employeeHomePage employee = loader.getController();
		Image im = new Image("images/background.jpg");
		employee.setimage(im);
		employee.set(MyEmail);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
	}

	@FXML
	void prev(ActionEvent event) {
		Connection conn = null;
		Statement stmt = null;
		Image image = null;
		String desc = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// **whire sql here**//
			List<String> MapId = new ArrayList<String>();
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map ");
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				MapId.add(rs.getString("MapId"));
			}
			rs.close();
			prep_stmt.close();
			place--;
			if (place < 0) {
				place = 0;
			} else {
				String Mapid = MapId.get(place);
				prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
				prep_stmt.setString(1, Mapid);
				rs = prep_stmt.executeQuery();
				while (rs.next()) {
					Blob blob = (Blob) rs.getBlob("Im");
					InputStream in = blob.getBinaryStream();
					BufferedImage im = ImageIO.read(in);
					image = SwingFXUtils.toFXImage(im, null);
					PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
					prep_stmt1.setString(1, rs.getString("CityId"));
					ResultSet rs1 = prep_stmt1.executeQuery();
					String CityName = null;
					while (rs1.next()) {
						CityName = rs1.getString("CityName");
					}
					rs1.close();
					prep_stmt1.close();
					desc = "This map is map of " + CityName + " \n" + "This map is " + rs.getString("description")
							+ " map.";
				}
				prep_stmt.close();
				rs.close();
				conn.close();
				imageview.setImage(image);
				Maptext.setText(desc);
				SearchMapId.setText(MapId.get(place));

			}

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("SQLException: " + se.getMessage());
			System.out.println("SQLState: " + se.getSQLState());
			System.out.println("VendorError: " + se.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	@FXML
	void next(ActionEvent event) {
		Connection conn = null;
		Statement stmt = null;
		Image image = null;
		String desc = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// **whire sql here**//
			List<String> MapId = new ArrayList<String>();
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map ");
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				MapId.add(rs.getString("MapId"));
			}
			rs.close();
			prep_stmt.close();
			place++;
			if (place > MapId.size() - 1) {
				place = MapId.size() - 1;
			} else {
				String Mapid = MapId.get(place);
				prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
				prep_stmt.setString(1, Mapid);
				rs = prep_stmt.executeQuery();
				while (rs.next()) {
					Blob blob = (Blob) rs.getBlob("Im");
					InputStream in = blob.getBinaryStream();
					BufferedImage im = ImageIO.read(in);
					image = SwingFXUtils.toFXImage(im, null);
					PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
					prep_stmt1.setString(1, rs.getString("CityId"));
					ResultSet rs1 = prep_stmt1.executeQuery();
					String CityName = null;
					while (rs1.next()) {
						CityName = rs1.getString("CityName");
					}
					rs1.close();
					prep_stmt1.close();
					desc = "This map is map of " + CityName + " \n" + "This map is " + rs.getString("description")
							+ " map.";
				}
				prep_stmt.close();
				rs.close();
				conn.close();
				imageview.setImage(image);
				Maptext.setText(desc);
				SearchMapId.setText(MapId.get(place));
			}
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("SQLException: " + se.getMessage());
			System.out.println("SQLState: " + se.getSQLState());
			System.out.println("VendorError: " + se.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	@FXML
	void showmap(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void setim(Image im) {
		imageview.setImage(im);
	}

	public void setMapId(String desc) {
		Maptext.setText(desc);
	}

	public void settext(String Map) {
		SearchMapId.setText(Map);

	}

	public void SeTEmail(String theEmail) {
		// TODO Auto-generated method stub
		MyEmail = theEmail;
	}

	public void setimage(Image im) {
		image.setImage(im);
	}

}
