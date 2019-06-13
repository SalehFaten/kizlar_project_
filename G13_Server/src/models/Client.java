package models;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Blob;

public class Client extends Person {
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
	static int place = 0;

	public static int SignIn(String email, String pass) {
		Connection conn = null;
		Statement stmt = null;
		String useremail = null;
		String userpass = null;
		int access = 0;
		/// ***sql***///
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//**whire sql here**//
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM CustomerCard WHERE Email=?");
			prep_stmt.setString(1, email);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				useremail = rs.getString("Email");
				userpass = rs.getString("password");
				access = rs.getInt("Checkaccess");

			}
			rs.close();
			prep_stmt.close();
			if (useremail == null) {
				return 1;

			} else if (useremail != null) {
				if (!(userpass.equals(pass))) {
					return 2;
				} else {
					if (access == 0) {
						PreparedStatement prep_stmt1 = conn
								.prepareStatement("UPDATE CustomerCard SET Checkaccess = ? WHERE Email=?");
						prep_stmt1.setInt(1, 1);
						prep_stmt1.setString(2, useremail);
						prep_stmt1.executeUpdate();
						prep_stmt1.close();
						conn.close();

						return 0;
					} else {
						return 3;
					}
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
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return 3;

	}

	public static boolean Signout(String email) {
		Connection conn = null;
		Statement stmt = null;
		String useremail = null;
		int access = 0;
		/// ***sql***///
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//**whire sql here**//
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM CustomerCard WHERE Email=?");
			prep_stmt.setString(1, email);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				useremail = rs.getString("Email");
				access = rs.getInt("Checkaccess");
			}
			rs.close();
			prep_stmt.close();
			if (access == 1) {
				PreparedStatement prep_stmt1 = conn
						.prepareStatement("UPDATE CustomerCard SET Checkaccess = ? WHERE Email=?");
				prep_stmt1.setInt(1, 0);
				prep_stmt1.setString(2, useremail);
				prep_stmt1.executeUpdate();
				prep_stmt1.close();
				conn.close();
				return true;
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
		return false;

	}

	public static boolean savemap(String pathtosave) {
		Connection conn = null;
		Statement stmt = null;
		/// ***sql***///
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			int i = 0;

			stmt = conn.createStatement();
			String sql = "SELECT * FROM Map";
			ResultSet rs = stmt.executeQuery(sql);

//		PreparedStatement prep_stmt=conn.prepareStatement("SELECT * FROM Map"); 
//		prep_stmt.setString(1, MapId);
//		ResultSet rs=prep_stmt.executeQuery();
			while (rs.next()) {
				File file = new File(pathtosave + "'\'" + "map" + Integer.toString(i) + ".jpg");
				FileOutputStream fos = new FileOutputStream(file);
				byte b[];
				Blob blob;
				blob = (Blob) rs.getBlob("Im");
				b = blob.getBytes(1, (int) blob.length());
				fos.write(b);
				fos.close();
				i++;
			}
			rs.close();
			stmt.close();
			return true;

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
		return false;

	}

	public static String ShowPreviousCity() {
		Connection conn = null;
		Statement stmt = null;
		String CityId = null;
		String TheResultText;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			stmt = conn.createStatement();
			List<String> City = new ArrayList<String>();
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City");
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				City.add(rs.getString("CityName"));
			}
			rs.close();
			prep_stmt.close();
			place--;
			if (place < 0) {
				place = 0;
			} else {
				String cityName = City.get(place);
				String sql = "SELECT * FROM "+cityName;
				 rs = stmt.executeQuery(sql);
				List<String> MapID = new ArrayList<String>();

				while (rs.next()) {
					MapID.add(rs.getString("MapId"));
					CityId = rs.getString("CityId");
				}
				rs.close();
				prep_stmt.close();
				TheResultText = CityId + "@";
				Iterator<String> iter = MapID.iterator();
				while (iter.hasNext()) {
					prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=? ");
					prep_stmt.setString(1, (String) iter.next());
					ResultSet rs1 = prep_stmt.executeQuery();
					while (rs1.next()) {
						TheResultText = TheResultText + rs1.getString("description") + "#";
					}
					rs1.close();

				}
				prep_stmt.close();
				rs.close();
				conn.close();

				return TheResultText;
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
		return null;

	}

	@SuppressWarnings("null")
	public static String ShowNextCity() {

		Connection conn = null;
		Statement stmt = null;
		String CityId = null;
		String TheResultText;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			List<String> City = new ArrayList<String>();
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City");
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				City.add(rs.getString("CityName"));
			}
			rs.close();
			prep_stmt.close();
			place++;
			if (place > City.size() - 1) {
				place = City.size() - 1;
			} else {
				List<String> MapID = new ArrayList<String>();
				String cityName = City.get(place);
				prep_stmt = conn.prepareStatement("SELECT * FROM " +cityName );
	         	 rs = prep_stmt.executeQuery();
				while (rs.next()) {
					MapID.add(rs.getString("MapId"));
					CityId = rs.getString("CityId");
				}
				rs.close();
				prep_stmt.close();
				TheResultText = CityId + "@";
				Iterator<String> iter = MapID.iterator();
				while (iter.hasNext()) {
					prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=? ");
					prep_stmt.setString(1, (String) iter.next());
					ResultSet rs1 = prep_stmt.executeQuery();
					while (rs1.next()) {
						TheResultText = TheResultText + rs1.getString("description") + "#";
					}
					rs1.close();

				}
				prep_stmt.close();
				rs.close();
				conn.close();
				return TheResultText;

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
		return null;
	}

	@SuppressWarnings("null")
	public static String ShowFirstCity() {

		Connection conn = null;
		Statement stmt = null;
		String CityId = null;
		String TheResultText;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			stmt = conn.createStatement();
			List<String> City = new ArrayList<String>();
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City");
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				City.add(rs.getString("CityName"));
			}
			rs.close();
			prep_stmt.close();
			String cityName = City.get(0);
			String sql = "SELECT * FROM "+cityName;
			 rs = stmt.executeQuery(sql);
			List<String> MapID = new ArrayList<String>();

			while (rs.next()) {
				MapID.add(rs.getString("MapId"));
				CityId = rs.getString("CityId");
			}
			rs.close();
			stmt.close();
			TheResultText = CityId + "@";
			Iterator<String> iter = MapID.iterator();
			while (iter.hasNext()) {
				prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=? ");
				prep_stmt.setString(1, (String) iter.next());
				ResultSet rs1 = prep_stmt.executeQuery();
				while (rs1.next()) {
					TheResultText = TheResultText + rs1.getString("description") + "#";
				}
				rs1.close();

			}
			prep_stmt.close();
			rs.close();
			conn.close();
			return TheResultText;

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
		return null;
	}

	public static boolean Purchase(String CityId, String Path) {
		Connection conn = null;
		Statement stmt = null;
		String CityName = null;

		/// ***sql***///
		try {
			int i = 0;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//**whire sql here**//
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
			prep_stmt.setString(1, CityId);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				CityName = rs.getString("CityName");
			}
			rs.close();
			prep_stmt.close();
			if (CityName == null)
				return false;
			else {
				PreparedStatement prep_stmt1 = conn.prepareStatement("Select * From CityName= ? ");
				prep_stmt1.setString(1, CityName);
				ResultSet rs1 = prep_stmt.executeQuery();
				while (rs1.next()) {
					File file = new File(Path + "'\'" + CityName + Integer.toString(i) + ".jpg");
					FileOutputStream fos = new FileOutputStream(file);
					byte b[];
					Blob blob;
					blob = (Blob) rs.getBlob("Im");
					b = blob.getBytes(1, (int) blob.length());
					fos.write(b);
					i++;
					fos.close();
				}

				rs1.close();
				prep_stmt.close();

				prep_stmt1.close();
				conn.close();
				return true;
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
		return false;
	}

}
