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
	
	private String FirstName;
	private String LastName;
	private String Tel;
	private String Email;
	private String visa;
	private String cvv;
	private String date;
	private String id;
	private String password;
	private boolean Regestered;
	
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
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getvisa() {
		return visa;
	}
	public void setvisa(String Visa) {
		visa =Visa;
	}
	public String getcvv() {
		return cvv;
	}
	public void setcvv(String CVV) {
		visa =CVV;
	}
	public String getdate() {
		return date;
	}
	public void setdate(String Date) {
		date =Date;
	}
	public String getid() {
		return visa;
	}
	public void setid(String Id) {
		id =Id;
	}
	public boolean isRegestered() {
		return Regestered;
	}
	public void setRegestered(boolean regestered) {
		Regestered = regestered;
	}
	
	
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
			if (email.equals("") || pass.equals("") || firstname.equals("") || lastname.equals("")
					|| tel.equals("") || visa.equals("") || cvv.equals("") || date.equals("")
					|| id.equals("")) {
				JOptionPane.showMessageDialog(null, "One or more files are empty!! ");
				return false;

			} else if (Email.contains(email)) {
				JOptionPane.showMessageDialog(null, "You are already registed ");
				return false;

			} else if (!(email.contains("@hotmail.com")) && !(email.contains("@gmail.com"))) {

				JOptionPane.showMessageDialog(null, "Please enter correct mail ");
				return false;

			} else if (tel.length() != 10 || (!tel.matches("[0-9]+"))) {
				JOptionPane.showMessageDialog(null, "Please enter correct phone number ");
				return false;

			} else if (visa.length() != 16 || (!visa.matches("[0-9]+"))) {
				JOptionPane.showMessageDialog(null, "Please enter correct visa number ");
				return false;

			} else if (cvv.length() != 3 || (!cvv.matches("[0-9]+"))) {
				JOptionPane.showMessageDialog(null, "Please enter correct cvv number ");
				return false;

			} else if (id.length() != 9 || (!id.matches("[0-9]+"))) {
				JOptionPane.showMessageDialog(null, "Please enter correct id ");
                return false; 
				
			} else if (date.length() != 5 || (!date.matches("(1[0-2]|0[1-9])/(2[0-9])"))) {
				JOptionPane.showMessageDialog(null, "Please enter correct date number ");
				return false;

			} else {
				PreparedStatement prep_stmt = conn.prepareStatement(
						"INSERT INTO CustomerCard " + "VALUES(?, ?, ?,?, ?, ?, ?, ?, ?, '1')");
				prep_stmt.setString(1, email);
				prep_stmt.setString(2, pass);
				prep_stmt.setString(3, firstname);
				prep_stmt.setString(4, lastname);
				prep_stmt.setString(5, tel);
				prep_stmt.setString(6, visa);
				prep_stmt.setString(7, cvv);
				prep_stmt.setString(8, date);
				prep_stmt.setString(9, id);
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
		return false;
	}
	
	
	
	
	

}
