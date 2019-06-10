package models;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class City {
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
	/*
	 * private String CityName; private String CountryName; private int CityId;
	 * private Vector<Path> CityPaths; private Vector<InterestingPlace>
	 * CityInterestingPlace; private Vector<Map> CityMaps;
	 */
	
	
	public static boolean CreateCity(String CityName,String CityId,String MapId,String desc,String path) {
					Connection conn = null;
		    Statement stmt = null;
            String CityID=null;
            String MapID=null;

			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();

		          /****write sql here****/
				if(CityName==null|| CityId==null|| MapId==null||desc==null || path==null)
				{
					JOptionPane.showMessageDialog(null, "One or more files are empty!! ");
					return false;
				}
				else
				{
					String sql = "SELECT * FROM City WHERE CityId="+CityId;
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						CityID=rs.getString("CityId");
					}
					rs.close();
					stmt.close();
					/****check if the city exist****/
					if (CityID==null) {

							/****insert new city to city table****/
							PreparedStatement prep_stmt = conn.prepareStatement(
								"INSERT INTO City "+ " VALUES( ?, ?,?,?)");
						prep_stmt.setString(1, CityId);
						prep_stmt.setString(2, CityName);
						prep_stmt.setString(3, "1000");
						prep_stmt.setString(4, "1000");
						prep_stmt.executeUpdate();
						prep_stmt.close();
						/****create new city table in database****/
						stmt = conn.createStatement();
						String createTableGifts = "CREATE TABLE "+CityName +
						"(CityId VARCHAR(40), MapId VARCHAR(40),image VARCHAR(400),PRIMARY KEY (MapId))";
						//execute create statement
						stmt.executeUpdate(createTableGifts);
						stmt.close();
						/****put date in new city****/
						prep_stmt = conn.prepareStatement(
								"INSERT INTO "+CityName + " VALUES(?, ?, ?)");
						prep_stmt.setString(1, CityId);
						prep_stmt.setString(2, MapId);
						prep_stmt.setString(3,path);
						prep_stmt.executeUpdate();
prep_stmt.close();
							/****put the image in maps****/
							prep_stmt = conn.prepareStatement(
									"INSERT INTO Map " + " VALUES(?, ?, ?, ?,?,?)");
							prep_stmt.setString(1, MapId);
							prep_stmt.setString(2,path);
							prep_stmt.setString(3,desc);
							prep_stmt.setInt(4,0);
							prep_stmt.setString(5,CityId);
							File file=new File(path);
							FileInputStream fis=new FileInputStream(file);
							prep_stmt.setBinaryStream(6,fis,(int)file.length());
							prep_stmt.executeUpdate();
							prep_stmt.close();
							return true;

					}
					else {
						JOptionPane.showMessageDialog(null, "City exists!! ");
						return false;
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
		return false;
			
	}
/*******************************************************/
	
	
	
    public static int ShowPrice(String CityId) {
    	Connection conn = null;
    	Statement stmt = null;
    	String CityID = null;
    	int Price=0;
    	//int NewPrice=0;
    	
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

	          /****write sql here****/
			if(CityId==null)
			{
				JOptionPane.showMessageDialog(null, "You Must Enter CityId!! ");
				return -1;
			}
			else
			{
				String sql = "SELECT * FROM City WHERE CityId="+CityId;
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					CityID=rs.getString("CityId");
				}
				rs.close();
				stmt.close();
				/****check if the city exist****/
				if (CityID!=null) {

						/****insert new city to city table****/
						PreparedStatement prep_stmt = conn.prepareStatement(
							"Select Price From City WHERE CityId=?");
					prep_stmt.setString(1, CityId);
					ResultSet rs1 = prep_stmt.executeQuery();
					while (rs1.next()) {
						Price = rs1.getInt("Price");
						
					}
					rs1.close();
					prep_stmt.close();
					return Price;


				}
				else {
					JOptionPane.showMessageDialog(null, "There is no such city id  ");
					return -1;
				}
				
				
			}
			
		}catch (SQLException se) {
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
		return -1;
    }
    

}
	

	


