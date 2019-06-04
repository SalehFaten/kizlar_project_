package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Client extends Person {
	 /////*******databasee******////
		static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

		// update USER, PASS and DB URL according to credentials provided by the
		// website:
		// https://remotemysql.com/
		// in future get those hardcoede string into separated config file.
		static private final String DB = "gVwEbvpoL3";
		static private final String DB_URL = "jdbc:mysql://remotemysql.com/" + DB + "?useSSL=false";
		static private final String USER = "gVwEbvpoL3";
		static private final String PASS = "PyIl4PPKot";
		/**
		 * The default port to listen on.
		 */
		final public static int DEFAULT_PORT = 5555;
		////*******************************************////
	private String UserName;
	private String Password;
	private int UserType;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getUserType() {
		return UserType;
	}
	public void setUserType(int userType) {
		UserType = userType;
	}
	/*
	public Version purchaceMap(int UserType, String cityName) {
		
	}*/
	public static  boolean SignIn(String email,String pass) {
		Connection conn = null;
		Statement stmt = null;
		String useremail = null;
		String userpass = null;
		///***sql***///
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
			}
			if (email == null || pass == null) {
				JOptionPane.showMessageDialog(null, "One or more files are empty!! ");
				return false;
			} else if (useremail == null) {
				JOptionPane.showMessageDialog(null, "You are not registed !!");
				return false;

			} else if (useremail != null) {
				if (!(userpass.equals(pass))) {
					JOptionPane.showMessageDialog(null, "You entered uncorrect password !!");
                       return false;
				} else {
                     return true;
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
		return false;
		
	}

}
