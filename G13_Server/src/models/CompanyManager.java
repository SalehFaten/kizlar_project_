package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

public class CompanyManager {

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
	
	static int place = 0;
	public static String showFirstPrice(){
		
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
				if(!rs.getString("Price").equals(rs.getString("newprice"))) {
					City.add(rs.getString("CityName") +","+rs.getString("Price")+","+ rs.getString("newprice")+","+rs.getString("CityId"));
				}
				
			}
			rs.close();
			prep_stmt.close();
			conn.close();

			if(City.size()>0) {
			String cityName = City.get(0);
			TheResultText = cityName + "@";
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
				if(!rs.getString("Price").equals(rs.getString("newprice"))){
					City.add(rs.getString("CityName") +","+rs.getString("Price")+","+ rs.getString("newprice")+","+rs.getString("CityId"));
				}
			}
			rs.close();
			prep_stmt.close();
			
			place--;
			if (place < 0) {
				place = 0;
			} else {
				String cityName = City.get(place);
				TheResultText = cityName + "@";
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
				if(!rs.getString("Price").equals(rs.getString("newprice"))) {
					City.add(rs.getString("CityName") +","+rs.getString("Price")+","+ rs.getString("newprice")+","+rs.getString("CityId"));
				}
			}
			rs.close();
			prep_stmt.close();
			place++;
			if (place > City.size() - 1) {
				place = City.size() - 1;
			} else {
				String cityName = City.get(place);
				TheResultText = cityName + "@";
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

	
	

}
