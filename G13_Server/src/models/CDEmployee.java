package models;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Blob;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class CDEmployee extends Employee{

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

public static  int SignIn(String email,String pass) {
	Connection conn = null;
	Statement stmt = null;
	String useremail = null;
	String userpass = null;
	int access=0;
	///***sql***///
	try {
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
//**whire sql here**//
		PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM EmployeeCard WHERE Email=?");
		prep_stmt.setString(1, email);
		ResultSet rs = prep_stmt.executeQuery();
		while (rs.next()) {
			useremail = rs.getString("Email");
			userpass = rs.getString("password");
			access=rs.getInt("Checkaccess");
		}
		rs.close();
		prep_stmt.close();
	    if (useremail == null) {
			return 1;

		} else if (useremail != null) {
			if (!(userpass.equals(pass))) {
                   return 2;
			} else {
				if(access==0) {
				PreparedStatement prep_stmt1 = conn.prepareStatement("UPDATE EmployeeCard SET Checkaccess = ? WHERE Email=?");
				prep_stmt1.setInt(1, 1);
				prep_stmt1.setString(2, useremail);
				prep_stmt1.executeUpdate();
				prep_stmt1.close();
				conn.close();
                 return 0;
				}
				else
				{
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
	return 3;
	
}

public static  boolean Signout(String email) {
	Connection conn = null;
	Statement stmt = null;
	String useremail = null;
	int access=0;
	///***sql***///
	try {
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
//**whire sql here**//
		PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM EmployeeCard WHERE Email=?");
		prep_stmt.setString(1, email);
		ResultSet rs = prep_stmt.executeQuery();
		while (rs.next()) {
			useremail = rs.getString("Email");
			access=rs.getInt("Checkaccess");
		}
		rs.close();
		prep_stmt.close();
				if(access==1) {
				PreparedStatement prep_stmt1 = conn.prepareStatement("UPDATE EmployeeCard SET Checkaccess = ? WHERE Email=?");
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
	return false;
	
}

// Function to convert an Input Stream to String in Java
public static String getString(InputStream in) throws IOException
{
	Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
	BufferedReader br = new BufferedReader(reader);

	StringBuilder sb = new StringBuilder();
	String line;

	while ((line = br.readLine()) != null) {
		sb.append(line);
		sb.append('\n');
	}
	br.close();

	return sb.toString();
}
public static String showfirstmap()
{
	Connection conn = null;
	Statement stmt = null;
	Image image = null;
	String desc = null;
	String Mapid = null;
	String res=null;

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
		if (MapId.isEmpty() == false) {

			Mapid = MapId.get(0);
			prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
			prep_stmt.setString(1, Mapid);
			rs = prep_stmt.executeQuery();
			while (rs.next()) {
				Blob blob = (Blob) rs.getBlob("Im");
				InputStream in = blob.getBinaryStream();
				res=getString(in);
//			
//				BufferedImage im = ImageIO.read(in);
//				image = SwingFXUtils.toFXImage(im, null);
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
}




