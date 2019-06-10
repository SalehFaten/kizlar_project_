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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map {
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
	
	
	@SuppressWarnings("resource")
	public static boolean AddMapToCity(String CityId ,String MapId, String description,String path)
	{	
	
		Connection conn = null;
	    Statement stmt = null;
	    String CityName=null;
	    String Mapid=null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

	//**whire sql here**//
			if(CityId ==null || MapId==null || description==null||path==null)
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
			rs.close();
			prep_stmt.close();
			if(CityName!=null)
			{
				String map=null;
				stmt = conn.createStatement();
				String sql = "SELECT * FROM  "+CityName;
				ResultSet rs1 = stmt.executeQuery(sql);
				while (rs1.next()) {
					map=rs1.getString("MapId");
				}

				rs1.close();
				stmt.close();
				if (!MapId.equals(map)) {
					prep_stmt = conn.prepareStatement(
							"INSERT INTO "+CityName + " VALUES(?, ?, ?)");
					prep_stmt.setString(1, CityId);
					prep_stmt.setString(2, MapId);
					prep_stmt.setString(3,path);
					prep_stmt.executeUpdate();
					prep_stmt.close();

					/****put the image in maps****/
					prep_stmt = conn.prepareStatement(
							"INSERT INTO Map " + " VALUES(?, ?, ?,?,?,?)");
					prep_stmt.setString(1, MapId);
					prep_stmt.setString(2,path);
					prep_stmt.setString(3,description);
					prep_stmt.setInt(4,0);
					prep_stmt.setString(5,CityId);
					File file=new File(path);
					FileInputStream fis=new FileInputStream(file);
					prep_stmt.setBinaryStream(6,fis,(int)file.length());
					prep_stmt.executeUpdate();
					prep_stmt.close();
					conn.close();
					return true;
				}
				else 
				{
			return false;
				}
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

		return false;
		
	}
	@SuppressWarnings("null")
	public static String showmap(String MapId)
	{
		Connection conn = null;
	    Statement stmt = null;
	    String im=null;
	    String desc=null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
	//**whire sql here**//
			if(MapId==null)
			{
				return null ;
			}
			else
			{
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
			prep_stmt.setString(1, MapId);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
			 im= rs.getString("image");
				PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
				prep_stmt1.setString(1, rs.getString("CityId"));
				ResultSet rs1 = prep_stmt1.executeQuery();
				String CityName=null;
				while (rs1.next()) {
					CityName=rs1.getString("CityName");
				}
				rs1.close();
				prep_stmt1.close();
			 desc= "This map is map of "+CityName+ " \n" +"This map is "+rs.getString("description")+ " map.";
			}
			prep_stmt.close();
			rs.close();
			conn.close();
		return im=im+"@"+desc;	
			
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

		return im;
		
	}
//	public static String getInfoMap(String MapId) {
//
//		Connection conn = null;
//	    Statement stmt = null;
//	    String desc=null;
//
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//	//**whire sql here**//
//			if(MapId==null)
//			{
//				return null ;
//			}
//			else
//			{
//			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
//			prep_stmt.setString(1, MapId);
//			ResultSet rs = prep_stmt.executeQuery();
//			while (rs.next()) {
//				PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
//				prep_stmt1.setString(1, rs.getString("CityId"));
//				ResultSet rs1 = prep_stmt1.executeQuery();
//				String CityName=null;
//				while (rs1.next()) {
//					CityName=rs1.getString("CityName");
//				}
//				rs1.close();
//				prep_stmt1.close();
//			 desc= "This map is map of "+CityName+ " , this map is "+rs.getString("description")+ " map.";
//			}
//			prep_stmt.close();
//			rs.close();
//			conn.close();
//		return desc;	
//			
//			}
//			
//		} catch (SQLException se) {
//			se.printStackTrace();
//			System.out.println("SQLException: " + se.getMessage());
//			System.out.println("SQLState: " + se.getSQLState());
//			System.out.println("VendorError: " + se.getErrorCode());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if (stmt != null)
//					stmt.close();
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
//		}
//		return null;
//		
//	}
	/*
	 * public String getMapId() { return MapId; } public void setMapId(String mapId)
	 * { MapId = mapId; } public int getCityId() { return CityId; } public void
	 * setCityId(int cityId) { CityId = cityId; } public String getDescription() {
	 * return Description; } public void setDescription(String description) {
	 * Description = description; } public Vector<Path> getMapPaths() { return
	 * MapPaths; } public void setMapPaths(Vector<Path> mapPaths) { MapPaths =
	 * mapPaths; } public Vector<InterestingPlace> getMapInterestingPlace() { return
	 * MapInterestingPlace; } public void
	 * setMapInterestingPlace(Vector<InterestingPlace> mapInterestingPlace) {
	 * MapInterestingPlace = mapInterestingPlace; } public Vector<Version>
	 * getMapVersions() { return MapVersions; } public void
	 * setMapVersions(Vector<Version> mapVersions) { MapVersions = mapVersions; }
	 */	
	

}
