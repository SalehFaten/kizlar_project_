package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class InterestingPlace {
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
		
		
	public static boolean AddIntersistingPlace(String PlaceName,String PlaceId,String desc,String MapId,String CityId,String path)		
	{

		Connection conn = null;
	    Statement stmt = null;
	    String Cityname=null;
	    String Cityid=null;
	    String Mapid=null;
	    String newMapid=null;
	    String mapdesc=null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if(PlaceName==null||PlaceId==null||CityId ==null || MapId==null || desc==null||path==null)
			{
				return false;
			}
			else
			{
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
			prep_stmt.setString(1,MapId);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
			 Cityid = rs.getString("CityId");
			 Mapid = rs.getString("MapId");
			 mapdesc=rs.getString("description");

			}
			rs.close();
			prep_stmt.close();
			if(!MapId.equals(Mapid))
			{
				JOptionPane.showMessageDialog(null, "the map deosn't exists ");
				return false;
			}else
			{
			if (!CityId.equals(Cityid))
			{
	        	JOptionPane.showMessageDialog(null, "the map deosn't belong to this city ");
				return false;
			}
			else
			{
				String[] split=Mapid.split(":");
				newMapid=split[0]+":"+split[1]+":"+Integer.toString(Integer.parseInt(split[2])+1);
				prep_stmt = conn.prepareStatement(
						"INSERT INTO InterstingPlace" + " VALUES(?, ?, ?, ?, ?)");
				prep_stmt.setString(1, PlaceName);
				prep_stmt.setString(2, PlaceId);
				prep_stmt.setString(3,desc);
				prep_stmt.setString(4,newMapid);
				prep_stmt.setString(5,CityId);
				prep_stmt.executeUpdate();
				prep_stmt.close();
				/****put the map****/
				prep_stmt = conn.prepareStatement(
						"INSERT INTO Map " + " VALUES(?, ?, ?,?,?)");
				prep_stmt.setString(1, newMapid);
				prep_stmt.setString(2,path);
				prep_stmt.setString(3,mapdesc);
				prep_stmt.setInt(4,0);
				prep_stmt.setString(5,CityId);
				prep_stmt.executeUpdate();
				prep_stmt.close();
				conn.close();
	
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

	public static boolean EditPlaceName(String PlaceName,String PlaceId)		
	{

		Connection conn = null;
	    Statement stmt = null;
	    String Cityname=null;
	    String placeid=null;
	

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if(PlaceName==null||PlaceId==null)
			{
				return false;
			}
			else
			{
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM InterstingPlace WHERE PlaceId=?");
			prep_stmt.setString(1,PlaceId);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
			 placeid = rs.getString("PlaceId");
			
			}
			rs.close();
			prep_stmt.close();
			if(!PlaceId.equals(placeid))
			{
				JOptionPane.showMessageDialog(null, "the place deosn't exists ");
				return false;
			}else
			{
				PreparedStatement prep_stmt1 = conn.prepareStatement("UPDATE InterstingPlace SET PlaceName = ? WHERE PlaceId=?");
				prep_stmt1.setString(1, PlaceName);
				prep_stmt1.setString(2, PlaceId);
				prep_stmt1.executeUpdate();
				prep_stmt1.close();
				conn.close();
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
	public static boolean EditPlaceDesc(String PlaceDesc,String PlaceId)		
	{

		Connection conn = null;
	    Statement stmt = null;
	    String Cityname=null;
	    String placeid=null;
	

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if(PlaceDesc==null||PlaceId==null)
			{
				return false;
			}
			else
			{
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM InterstingPlace WHERE PlaceId=?");
			prep_stmt.setString(1,PlaceId);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
			 placeid = rs.getString("PlaceId");
			
			}
			rs.close();
			prep_stmt.close();
			if(!PlaceId.equals(placeid))
			{
				JOptionPane.showMessageDialog(null, "the place deosn't exists ");
				return false;
			}else
			{
				PreparedStatement prep_stmt1 = conn.prepareStatement("UPDATE InterstingPlace SET description = ? WHERE PlaceId=?");
				prep_stmt1.setString(1, PlaceDesc);
				prep_stmt1.setString(2, PlaceId);
				prep_stmt1.executeUpdate();
				prep_stmt1.close();
				conn.close();
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

	
	
	/////check it doesnt work
	public static boolean EditPlaceId(String PlaceId,String newid)		
	{

		Connection conn = null;
	    Statement stmt = null;
	    String placeid=null;
	

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if(PlaceId==null||newid==null)
			{
				return false;
			}
			else
			{
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM InterstingPlace WHERE PlaceId=?");
			prep_stmt.setString(1,PlaceId);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
			 placeid = rs.getString("PlaceId");
			
			}
			rs.close();
			prep_stmt.close();
			if(!PlaceId.equals(placeid))
			{
				JOptionPane.showMessageDialog(null, "the place deosn't exists ");
				return false;
			}else
			{
				PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM InterstingPlace WHERE PlaceId=?");
				prep_stmt1.setString(1,newid);
				 rs = prep_stmt1.executeQuery();
				while (rs.next()) {
				 placeid = rs.getString("PlaceId");
				
				}
				rs.close();
				prep_stmt1.close();
				if(newid.equals(placeid)) {
					JOptionPane.showMessageDialog(null, "the placeId exists to another place ");
					return false;
				}
				else
				{
				PreparedStatement prep_stmt2 = conn.prepareStatement("UPDATE InterstingPlace SET PlaceId = ? WHERE PlaceId=?");
				prep_stmt2.setString(1,newid);
				prep_stmt2.setString(2, PlaceId);
				prep_stmt2.executeUpdate();
				prep_stmt2.close();
				conn.close();
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
	public static boolean RemovePlace(String PlaceId)		
	{

		Connection conn = null;
	    Statement stmt = null;
	    String placeid=null;
	

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if(PlaceId==null)
			{
				return false;
			}
			else
			{
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM InterstingPlace WHERE PlaceId=?");
			prep_stmt.setString(1,PlaceId);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
			 placeid = rs.getString("PlaceId");
			
			}
			rs.close();
			prep_stmt.close();
			if(!PlaceId.equals(placeid))
			{
				JOptionPane.showMessageDialog(null, "the place deosn't exists ");
				return false;
			}else
			{
			//sql to remove place 
				conn.close();
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


