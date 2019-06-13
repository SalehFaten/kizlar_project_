package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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

}




