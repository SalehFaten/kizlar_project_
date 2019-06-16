/**
 * Sample Skeleton for 'Control.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Controller implements Initializable {
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

//	  static ChatClient client=null;
//	ServerConsole server=null;
//	private AnchorPane rootPane;
	@FXML // fx:id="search"
	private TextField search; // Value injected by FXMLLoader

	@FXML // fx:id="login"
	private Button login; // Value injected by FXMLLoader

	@FXML // fx:id="register"
	private Button register; // Value injected by FXMLLoader

	@FXML // fx:id="image"
	private ImageView image; // Value injected by FXMLLoader
	static List<String> users_with_new_version;
	
	public void setarrayversion(List<String> list) {
		  users_with_new_version=list;
			
	    }
	public void setimage(Image im) {
		image.setImage(im);
	}

	@FXML
	void log(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		signin sign = loader.getController();
		Image im = new Image("images/background.jpg");
		sign.setimage(im);
		sign.setarrayversion(users_with_new_version);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
	}

	@FXML
	void Search(ActionEvent event) throws IOException {

		String m = search(search.getText());
		String[] result = m.split("@");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Psearch.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Psearch psearch1 = loader.getController();
		Image im = new Image("images/background.jpg");
		psearch1.setimage(im);
		if ("PublicSearch".equals(result[0])) {
			for (int i = 1; i < result.length; i++) {
				psearch1.set(result[i]);
			}

		}
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();
	}

	@FXML
	void regist(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("regist.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		regist signup = loader.getController();
		Image im = new Image("images/background.jpg");
		signup.setimage(im);
		Scene regist = new Scene(root);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(regist);
		app_stage.show();

	}

	public static String search(String searchcity) {
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		List<String> out = new ArrayList<String>();

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();

			/**** write sql here ****/
			if (searchcity == null) {
				return null;
			} else {
				String sql = "SELECT CityName FROM City ";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (searchcity.toUpperCase().contains(rs.getString("CityName").toUpperCase())) {
						out.add(rs.getString("CityName"));
					}
				}
				rs.close();
				sql = "SELECT * FROM Map ";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (rs.getString("description").toUpperCase().contains(searchcity.toUpperCase())
							|| searchcity.toUpperCase().contains(rs.getString("description").toUpperCase())) {
						PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
						prep_stmt.setString(1, rs.getString("CityId"));
						ResultSet rs1 = prep_stmt.executeQuery();
						while (rs1.next()) {
							if (!out.contains(rs1.getString("CityName"))) {
								out.add(rs1.getString("CityName"));
							}
						}
						prep_stmt.close();
						rs1.close();
					}

				}
				rs.close();
				sql = "SELECT * FROM InterstingPlace";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (rs.getString("description").toUpperCase().contains(searchcity.toUpperCase())
							|| searchcity.toUpperCase().contains(rs.getString("description").toUpperCase())
							|| searchcity.toUpperCase().contains(rs.getString("PlaceName").toUpperCase())) {
						PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
						prep_stmt.setString(1, rs.getString("CityId"));
						ResultSet rs1 = prep_stmt.executeQuery();
						while (rs1.next()) {
							if (!out.contains(rs1.getString("CityName"))) {
								out.add(rs1.getString("CityName"));
							}
						}
						prep_stmt.close();
						rs1.close();

					}

				}
				rs.close();
				sql = "SELECT * FROM Path";
				rs = stmt.executeQuery(sql);
				String MapID=null;
				while (rs.next()) {
					if (rs.getString("description").toUpperCase().contains(searchcity.toUpperCase())
							|| searchcity.toUpperCase().contains(rs.getString("description").toUpperCase())) {
						MapID=rs.getString("MapId");
					}
					sql = "SELECT * FROM Map WHERE MapId="+MapID;
					ResultSet rs1 = stmt1.executeQuery(sql);
					while(rs1.next()) {
				
						PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
						prep_stmt.setString(1, rs.getString("CityId"));
						ResultSet rs2 = prep_stmt.executeQuery();
						while (rs2.next()) {
							if (!out.contains(rs1.getString("CityName"))) {
								out.add(rs1.getString("CityName"));
							}
						}
						prep_stmt.close();
						rs2.close();
					}
					rs1.close();
					}
				rs.close();
			
				
//				String[] type = new String[100];
				int j = 0;
				String result = "PublicSearch" + "@";
				if (!out.equals(null)) {
					Iterator iterator = out.iterator();
					while (iterator.hasNext()) {
						int countmap = 0;
						String city = (String) iterator.next();
						sql = "SELECT * FROM  " + city;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							countmap++;
						}
						rs.close();
						int countpath=0;
						sql = "SELECT * FROM  Path";
						ResultSet rs1= stmt.executeQuery(sql);
						while (rs1.next()) {
							sql = "SELECT * FROM  "+city;
							ResultSet rs2 = stmt1.executeQuery(sql);
							while(rs2.next()) {
							if(rs1.getString("MapId")==rs2.getString("MapId"))
							{
								countpath++;
							}
							}
							rs2.close();
						}
						rs1.close();
						int countp = 0;
						sql = "SELECT * FROM  InterstingPlace " ;
						ResultSet rs3 = stmt.executeQuery(sql);
						while (rs3.next()) {
							sql = "SELECT * FROM  "+city;
							ResultSet rs4 = stmt1.executeQuery(sql);
							while(rs3.next())
							{
								if (rs3.getString("CityId")==rs4.getString("CityId"))
								{
									countp++;
								}
							}
							rs4.close();
						}
						rs3.close();
						
//						sql = "SELECT * FROM  " + city;
//						rs = stmt.executeQuery(sql);
//						while (rs.next()) {
//							PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
//							prep_stmt.setString(1, rs.getString("MapId"));
//							ResultSet rs1 = prep_stmt.executeQuery();
//							while (rs1.next()) {
//								String desc = rs1.getString("description");
//								if (type[0] == null) {
//									type[j] = desc;
//									j++;
//								} else if (type[0] != null && !type[j - 1].equals(desc)) {
//									type[j] = desc;
//									j++;
//								}
//							}
//							rs1.close();
//
//							prep_stmt.close();
//						}
						result = result + "The City " + city + " has: " + countmap + " maps " +" and: "+ countpath + " paths " + "and: " + countp + " places.";
//						+  "and has many types: ";
//						for (int m = 0; m < type.length; m++) {
//							if (type[m] != null) {
//								result = result + type[m] + ",";
//							}
//						}
						result = result + "@";
//						for (int m = 0; m < type.length; m++) {
//							type[m] = null;
//						}
//						rs.close();
					}
					stmt.close();
					stmt1.close();
					out.clear();
					return result;

				} else {
					return null;
				}
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
				if (stmt1 != null)
					stmt1.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
