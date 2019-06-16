package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	
	
	static public String getcityReport(String id,String d1, String d2) {

		
		Connection conn = null;
		Statement stmt = null;

		String resultMsg = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			stmt = conn.createStatement();

			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
			prep_stmt.setString(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			String cityName="";
			String CityViews="";
			while (rs.next()) {

				cityName=rs.getString("CityName"); 
				

			}
			prep_stmt.close();
			rs.close();
			//conn.close();
			int maps_num=0;
			if(!cityName.equals("")) {
				
				PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM"+cityName);
				
				ResultSet rs1 = prep_stmt1.executeQuery();
				while (rs1.next()) {
					maps_num++;
					
				}
				prep_stmt1.close();
				rs1.close();				
				
				
			}
			int count_one=0;
			int count_sub=0;
			int count_downloads=0;
			PreparedStatement prep_stmt2 = conn.prepareStatement("SELECT * FROM Purchase WHERE CityId=? AND Date BETWEEN "+d1+"AND"+d2);
			prep_stmt2.setString(1, id);
			ResultSet rs2 = prep_stmt2.executeQuery();
			while (rs2.next()) {
				if(rs2.getString("Type").equals("sub")) {
					count_sub++;
				}
				if(rs2.getString("Type").equals("onetime")) {
					count_one++;
				}
				count_downloads=count_downloads+rs2.getInt("downloads");
			}
			prep_stmt2.close();
			rs2.close();
			
			int countPurchaseAgain=0;
			PreparedStatement prep_stmt3 = conn.prepareStatement("SELECT * FROM Purchase WHERE CityId=? AND Date BETWEEN "+d1+" AND "+d2 +" AND Type= sub");
			prep_stmt3.setString(1, id);
			Hashtable<String,String> purchases= new Hashtable<String,String>();
			ResultSet rs3 = prep_stmt3.executeQuery();
			while (rs3.next()) {
				
				purchases.put(rs3.getString("Email"), purchases.get(rs3.getString("Email"))+","+rs3.getString("Date"));
				
			}
			prep_stmt3.close();
			rs3.close();
			
			Set<String> emails=purchases.keySet();
			for(String key:emails) {
				String[] dates=purchases.get(key).split(",");
				List<String> list_dates=new ArrayList<String>();
				for(int i=0;i<dates.length;i++) {
					list_dates.add(dates[i]);
				}
				Iterator<String> iterator= list_dates.iterator();
				while(iterator.hasNext()) {
					String tmp=iterator.next();
					LocalDate CheckDate=LocalDate.parse((CharSequence)tmp);
					LocalDate dateafter=CheckDate.plusMonths(6).plusDays(1);
					if(list_dates.contains(dateafter)){
						countPurchaseAgain++;
					}
				}
				
			}
			int count_views=0;
			PreparedStatement prep_stmt4 = conn.prepareStatement("SELECT * FROM Views WHERE CityId=? AND Date BETWEEN "+d1+" AND "+d2 );
			prep_stmt4.setString(1, id);
			ResultSet rs4 = prep_stmt4.executeQuery();
			while (rs4.next()) {
				count_views++;
				
			}
			resultMsg=cityName+":"+"num of maps= "+Integer.toString(maps_num)+
					",num of one time purchases= "+ Integer.toString(count_one)+
					",num of subscribe purchases= " +Integer.toString(count_sub)+
					",num of renew subscription= "+ Integer.toString(countPurchaseAgain) +
					", num of city views= "+Integer.toString(count_views)+
					",num of city downloads= "+Integer.toString(count_downloads);
			conn.close();
			return resultMsg;
			
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("SQLException: " + se.getMessage());
			System.out.println("SQLState: " + se.getSQLState());
			System.out.println("VendorError: " + se.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return resultMsg;
			
	}
	static public String getcitiesReport(String d1, String d2)
	{
	
		Connection conn = null;
		Statement stmt = null;

		String resultMsg = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			stmt = conn.createStatement();

			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City");
			
			ResultSet rs = prep_stmt.executeQuery();
             //String[] resultsMsg;
			while (rs.next()) {
				
				String res_city=getcityReport(rs.getString("CityId"),d1,d2);
				resultMsg =resultMsg +"$"+res_city;

			}
			prep_stmt.close();
			rs.close();
			conn.close();
		
				
		
		
			return resultMsg;
			
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("SQLException: " + se.getMessage());
			System.out.println("SQLState: " + se.getSQLState());
			System.out.println("VendorError: " + se.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return resultMsg;
			
	}
	
	static public List<String> getusersReport(String d1, String d2) {
		Connection conn = null;
		Statement stmt = null;

		List<String> ResMsg=new ArrayList<String>();

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			stmt = conn.createStatement();

			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM CustomerCard");
			
			ResultSet rs = prep_stmt.executeQuery();
             
			while (rs.next()) {
				
				String email=rs.getString("Email");
				PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM Purchase WHERE Email=? AND Date BETWEEN "+d1+" AND "+d2);
				prep_stmt1.setString(1, email);
				ResultSet rs1 = prep_stmt1.executeQuery();
				String user_reprt="";
				while (rs1.next()) {
					user_reprt=user_reprt+rs.getString("CityId")+","+rs.getString("Type")+","+rs.getString("Date");
					//ResMsg.add(rs.getString("CityId")+","+rs.getString("Type")+","+rs.getString("Date"));

				}
				rs1.close();
				prep_stmt1.close();				
				ResMsg.add(user_reprt);

			}
			prep_stmt.close();
			rs.close();
			conn.close();
		
				
		
		
			return ResMsg;
			
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("SQLException: " + se.getMessage());
			System.out.println("SQLState: " + se.getSQLState());
			System.out.println("VendorError: " + se.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return ResMsg;
					
	}
	
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
