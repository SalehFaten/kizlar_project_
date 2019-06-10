package models;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Blob;

public class Client extends Person {
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
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM CustomerCard WHERE Email=?");
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
					PreparedStatement prep_stmt1 = conn.prepareStatement("UPDATE CustomerCard SET Checkaccess = ? WHERE Email=?");
					prep_stmt1.setInt(1, 1);
					prep_stmt1.setString(2, useremail);
					prep_stmt1.executeUpdate();
					prep_stmt1.close();
					conn.close();

                     return 0;
					}
					else {
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
		return 4;
		
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
		PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM CustomerCard WHERE Email=?");
		prep_stmt.setString(1, email);
		ResultSet rs = prep_stmt.executeQuery();
		while (rs.next()) {
			useremail = rs.getString("Email");
			access=rs.getInt("Checkaccess");
		}
		rs.close();
		prep_stmt.close();
				if(access==1) {
				PreparedStatement prep_stmt1 = conn.prepareStatement("UPDATE CustomerCard SET Checkaccess = ? WHERE Email=?");
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
public static  boolean savemap(String pathtosave) {
	Connection conn = null;
	Statement stmt = null;
	///***sql***///
	try {
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		int i=0;
	
		stmt = conn.createStatement();
		String sql = "SELECT * FROM Map";
		ResultSet rs = stmt.executeQuery(sql);
		
//		PreparedStatement prep_stmt=conn.prepareStatement("SELECT * FROM Map"); 
//		prep_stmt.setString(1, MapId);
//		ResultSet rs=prep_stmt.executeQuery();
		while(rs.next()){
			File file=new File(pathtosave+"'\'"+"map"+Integer.toString(i)+".jpg");
			FileOutputStream fos=new FileOutputStream(file);
			byte b[];
			Blob blob;
			blob=(Blob) rs.getBlob("Im");
			b=blob.getBytes(1,(int)blob.length());
			fos.write(b);
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
