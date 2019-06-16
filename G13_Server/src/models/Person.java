package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Person {	
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
	
	public static  boolean register(String firstname, String lastname, String tel, String email,String pass, String visa,String cvv,String date,String id){
		Connection conn = null;
		Statement stmt = null;
		List<String> Email = new ArrayList<String>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM CustomerCard";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Email.add(rs.getString("Email"));
			}

			 if (Email.contains(email)) {
				return false;

			} else {
				PreparedStatement prep_stmt = conn.prepareStatement(
						"INSERT INTO CustomerCard " + "VALUES(?, ?, ?,?, ?, ?, ?, ?, ?,?)");
				prep_stmt.setString(1, email);
				prep_stmt.setString(2, pass);
				prep_stmt.setString(3, firstname);
				prep_stmt.setString(4, lastname);
				prep_stmt.setString(5, tel);
				prep_stmt.setString(6, visa);
				prep_stmt.setString(7, cvv);
				prep_stmt.setString(8, date);
				prep_stmt.setString(9, id);
				prep_stmt.setInt(10, 0);
				prep_stmt.executeUpdate();
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
	
	
	
	

}
