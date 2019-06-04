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

public class Map {
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
	
	private String MapId;
	private int CityId;
	private String Description;
	private Vector<Path> MapPaths;
	private Vector<InterestingPlace> MapInterestingPlace;
	private Vector<Version> MapVersions;

	
	
	
	@SuppressWarnings("resource")
	public static boolean AddMapToCity(String CityId ,String MapId,String VersionNum, String description,String path)
	{	Connection conn = null;
	    Statement stmt = null;
	    String CityName=null;
	    String Mapid=null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
	//**whire sql here**//
			if(CityId ==null || MapId==null ||VersionNum==null|| description==null||path==null)
			{
				return false;
			}
			else
			{
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
			prep_stmt.setString(1, CityId);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
			CityName = rs.getString("CityName");
			}
			if(CityName!=null)
			{
				 prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
				prep_stmt.setString(1, MapId);
				rs = prep_stmt.executeQuery();
				while (rs.next()) {
				Mapid = rs.getString("MapId");
				}
				if (Mapid==null)
				{
					return false;
				}
				else
				{
					FileInputStream input=null;
					prep_stmt = conn.prepareStatement(
						"INSERT INTO "+CityName + " VALUES(?, ?, ?)");
				prep_stmt.setString(1, CityId);
				prep_stmt.setString(2, MapId);
//				prep_stmt.setString(3, path);
				File file= new File(path);
				input= new FileInputStream(file);
				prep_stmt.setBlob(3, input);
				prep_stmt.executeUpdate();
				
				return true;
					
					
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

		return false;
		
	}

//	public String getMapId() {
//		return MapId;
//	}
//	public void setMapId(String mapId) {
//		MapId = mapId;
//	}
//	public int getCityId() {
//		return CityId;
//	}
//	public void setCityId(int cityId) {
//		CityId = cityId;
//	}
//	public String getDescription() {
//		return Description;
//	}
//	public void setDescription(String description) {
//		Description = description;
//	}
//	public Vector<Path> getMapPaths() {
//		return MapPaths;
//	}
//	public void setMapPaths(Vector<Path> mapPaths) {
//		MapPaths = mapPaths;
//	}
//	public Vector<InterestingPlace> getMapInterestingPlace() {
//		return MapInterestingPlace;
//	}
//	public void setMapInterestingPlace(Vector<InterestingPlace> mapInterestingPlace) {
//		MapInterestingPlace = mapInterestingPlace;
//	}
//	public Vector<Version> getMapVersions() {
//		return MapVersions;
//	}
//	public void setMapVersions(Vector<Version> mapVersions) {
//		MapVersions = mapVersions;
//	}
//	
	

}
