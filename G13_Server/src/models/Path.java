package models;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Path {
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
		public static boolean AddPath(String PathId,String MapId,String desc,String path)
		{
			Connection conn = null;
		    Statement stmt = null;
		    String Cityname=null;
		    String Pathid=null;
		    String Mapid=null;
		    String newMapid=null;
		    String cityid=null;
		    String Desc=null;

			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if(MapId==null || PathId==null||desc==null||path==null)
				{
					return false;
				}
				else
				{
				PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Path WHERE PathId=?");
				prep_stmt.setString(1,PathId);
				ResultSet rs = prep_stmt.executeQuery();
				while (rs.next()) {
				 Pathid = rs.getString("PathId");

				}
				rs.close();
				prep_stmt.close();
				if(Pathid!=null)
				{
					return false;
				}else
				{
					 prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
					prep_stmt.setString(1,MapId);
					 rs = prep_stmt.executeQuery();
					while (rs.next()) {
					 Mapid = rs.getString("MapId");
					 cityid=rs.getString("CityId");
					Desc=rs.getString("description");


					}
					rs.close();
					prep_stmt.close();
				if (Mapid==null)
				{
					return false;
				}
				else
				{
					String[] split=Mapid.split(":");
					newMapid=split[0]+":"+split[1]+":"+Integer.toString(Integer.parseInt(split[2])+1);
					prep_stmt = conn.prepareStatement(
							"INSERT INTO Path" + " VALUES(?, ?, ?)");
					prep_stmt.setString(1, PathId);
					prep_stmt.setString(2, newMapid);
					prep_stmt.setString(3,desc);
					prep_stmt.executeUpdate();
					prep_stmt.close();
					/****put the map****/
					prep_stmt = conn.prepareStatement(
							"INSERT INTO Map " + " VALUES(?, ?, ?,?,?,?)");
					prep_stmt.setString(1, newMapid);
					prep_stmt.setString(2,path);
					prep_stmt.setString(3,Desc);
					prep_stmt.setInt(4,0);
					prep_stmt.setString(5,cityid);
					File file=new File(path);
					FileInputStream fis=new FileInputStream(file);
					prep_stmt.setBinaryStream(6,fis,(int)file.length());
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
		public static boolean EditPath(String PathId,String path)
		{
			Connection conn = null;
		    Statement stmt = null;
            String MapID=null;
            String Pathid=null;
			String MAP=null;


			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();

		          /****write sql here****/
				if(PathId==null || path==null)
				{
					return false;
				}
				else
				{
					String sql = "SELECT * FROM Path WHERE PathId="+PathId;
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						MapID=rs.getString("MapId");
						Pathid=rs.getString("PathId");

					}
					rs.close();
					stmt.close();
					/****check if the city exist****/
					if (Pathid==null||MapID==null) {
						return false;
					}
					else {
						PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
						prep_stmt.setString(1,MapID);
						 rs = prep_stmt.executeQuery();
						while (rs.next()) {
							MAP=rs.getString("MapId");
						}
						rs.close();
						prep_stmt.close();
						
						if(MAP!=null)
						{
							/****put the image in maps****/
						    prep_stmt = conn.prepareStatement("UPDATE Map SET Im = ? WHERE MapId=?");
							File file=new File(path);
							FileInputStream fis=new FileInputStream(file);
							prep_stmt.setBinaryStream(1,fis,(int)file.length());
							prep_stmt.setString(2,MAP);
							prep_stmt.executeUpdate();
							prep_stmt.close();
							return true;
						}
				
					else {
						return false;
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
			

		public static boolean Editdesc(String PathId,String desc)
		{
			
			Connection conn = null;
		    Statement stmt = null;
            String Pathid=null;


			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();

		          /****write sql here****/
				if(PathId==null || desc==null)
				{
					return false;
				}
				else
				{
					PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Path WHERE PathId=?");
					prep_stmt.setString(1,PathId);
					ResultSet rs = prep_stmt.executeQuery();
					while (rs.next()) {
						Pathid=rs.getString("PathId");

					}
					rs.close();
					prep_stmt.close();
					/****check if the city exist****/
					if (Pathid==null) {
						return false;
					}
					else {
						
							 prep_stmt = conn.prepareStatement("UPDATE Path SET description =?  WHERE PathId = ?");
							prep_stmt.setString(1,desc);
							prep_stmt.setString(2,Pathid);
							prep_stmt.executeUpdate();
							prep_stmt.close();
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
		public static boolean removepath(String PathId)
		{
			return false;
			
		}

}
