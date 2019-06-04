package models;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CompanyManager {

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
	///***sql***///
//	try {
//		Class.forName(JDBC_DRIVER);
//		conn = DriverManager.getConnection(DB_URL, USER, PASS);
////**whire sql here**//
//
//		}
//	} catch (SQLException se) {
//		se.printStackTrace();
//		System.out.println("SQLException: " + se.getMessage());
//		System.out.println("SQLState: " + se.getSQLState());
//		System.out.println("VendorError: " + se.getErrorCode());
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	
}
