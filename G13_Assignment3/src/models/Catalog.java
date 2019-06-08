package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Catalog {
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
	/*
	 * private Vector<City> AllCities;
	 * 
	 * public Vector<> searchByCity(String CityName){
	 * 
	 * } public Vector<> searchByPlace(String PlaceName){
	 * 
	 * } public Vector<> searchByDescription(String Description){
	 * 
	 * }
	 */

	@SuppressWarnings("null")
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
					if (searchcity.contains(rs.getString("CityName"))) {
						out.add(rs.getString("CityName"));
					}
				}
				sql = "SELECT * FROM Map ";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (searchcity.contains(rs.getString("description"))) {
						PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
						prep_stmt.setString(1, rs.getString("CityId"));
						ResultSet rs1 = prep_stmt.executeQuery();
						while (rs1.next()) {
							if (!out.contains(rs1.getString("CityName"))) {
								out.add(rs1.getString("CityName"));
							}
						}
						prep_stmt.close();
					}

				}
				String[] type = new String[100];
				int j = 0;
				String result ="PublicSearch" + "@";
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
						sql = "SELECT * FROM  " + city;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
							prep_stmt.setString(1, rs.getString("MapId"));
							ResultSet rs1 = prep_stmt.executeQuery();
							while (rs1.next()) {
								String desc = rs1.getString("description");
								if (type[0] == null) {
									type[j] = desc;
									j++;
								} else if (type[0] != null && !type[j - 1].equals(desc)) {
									type[j] = desc;
									j++;
								}
							}
							rs1.close();
							result =result+ "The City " + city + " has: " + countmap + " maps " + "and has many types: ";
							for (int m = 0; m < type.length; m++) {
								if (type[m] != null) {
									result = result + type[m] + ",";
								} 
							}
							result=result+"."+"@";
							for (int m = 0; m < type.length; m++) {
								type[m] = null;
							}
							prep_stmt.close();
						}
						rs.close();
					}

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

}
