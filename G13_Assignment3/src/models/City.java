package models;

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
	static private final String DB = "gVwEbvpoL3";
	static private final String DB_URL = "jdbc:mysql://remotemysql.com/" + DB + "?useSSL=false";
	static private final String USER = "gVwEbvpoL3";
	static private final String PASS = "PyIl4PPKot";
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;
	////*******************************************////
	private String CityName;
	private String CountryName;
	private int CityId;
	private Vector<Path> CityPaths;
	private Vector<InterestingPlace> CityInterestingPlace;
	private Vector<Map> CityMaps;
	
	
	public static boolean CreateCity(String CityName,String CityId,String MapId,String VersionNum,String desc,String path) {
					Connection conn = null;
		    Statement stmt = null;
            String CityID=null;
            String MapID=null;

			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();

		          /****write sql here****/
				if(CityName==null|| CityId==null|| MapId==null|| VersionNum==null||desc==null || path==null)
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
					/****check if the city exist****/
					if (CityID==null) {
						/****check if the map is exist ****/
						 sql = "SELECT * FROM Map WHERE MapId="+MapId;
						 rs = stmt.executeQuery(sql);
						while (rs.next()) {
							MapID=rs.getString("MapId");
						}
						if (MapID==null)
						{
							JOptionPane.showMessageDialog(null, "Map  not exist!! ");
							return false;
						}
						else {
					/****insert new city to city table****/
						PreparedStatement prep_stmt = conn.prepareStatement(
							"INSERT INTO City "+ " VALUES( ?, ?)");
					prep_stmt.setString(1, CityId);
					prep_stmt.setString(2, CityName);
					prep_stmt.executeUpdate();
					
					/****create new city table in database****/
					stmt = conn.createStatement();
					String createTableGifts = "CREATE TABLE "+CityName +
					"(CityId VARCHAR(40), MapId VARCHAR(40),image VARCHAR(400))";
					//execute create statement
					stmt.executeUpdate(createTableGifts);
					
					/****put date in new city****/
					prep_stmt = conn.prepareStatement(
							"INSERT INTO "+CityName + " VALUES(?, ?, ?,?)");
					prep_stmt.setString(1, CityId);
					prep_stmt.setString(2, MapId);
					prep_stmt.setString(3,path);
					prep_stmt.setString(4,desc);
					prep_stmt.executeUpdate();

					/****put the image in maps****/
					prep_stmt = conn.prepareStatement(
							"UPDATE Map "+"SET image =? "+"WHERE MapId= ? ");
					prep_stmt.setString(1, path);
					prep_stmt.setString(2,MapId);
					prep_stmt.executeUpdate();
					return true;
					}
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
		return false;
			
	}
}
	
	/*
	 * public String getCityName() { return CityName; } public void
	 * setCityName(String cityName) { CityName = cityName; } public String
	 * getCountryName() { return CountryName; } public void setCountryName(String
	 * countryName) { CountryName = countryName; } public int getCityId() { return
	 * CityId; } public void setCityId(int cityId) { CityId = cityId; } public
	 * Vector<Path> getCityPaths() { return CityPaths; } public void
	 * setCityPaths(Vector<Path> cityPaths) { CityPaths = cityPaths; } public
	 * Vector<InterestingPlace> getCityInterestingPlace() { return
	 * CityInterestingPlace; } public void
	 * setCityInterestingPlace(Vector<InterestingPlace> cityInterestingPlace) {
	 * CityInterestingPlace = cityInterestingPlace; } public Vector<Map>
	 * getCityMaps() { return CityMaps; } public void setCityMaps(Vector<Map>
	 * cityMaps) { CityMaps = cityMaps; }
	 * 
	 */
	


