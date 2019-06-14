package models;


import java.io.File;
import java.util.Iterator;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
public class CDManager {

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
	
	public static boolean acceptVersion() {
		
		Connection conn = null;
	    Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
	//**whire sql here**//
			
			
			List<String> cities_with_new_version = new ArrayList<String>(); 
	
			Hashtable< String,Integer> map_ids_database = new Hashtable<String,Integer>();
			
			Hashtable< String,String> mapImgs_forEach_lastMap = new Hashtable<String,String>();
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map ");
			ResultSet rs = prep_stmt.executeQuery();
			Hashtable< String,String> mapIds_forEach_city = new Hashtable<String,String>();
			while (rs.next()) {
				//Set<String> keys = map_ids_database.keySet();
				
			    String temp=rs.getString("MapId");
			    if(map_ids_database.containsKey(temp.split(":")[0]+":"+temp.split(":")[1] )) {
			    	if( Integer.parseInt(temp.split(":")[2])>map_ids_database.get(temp.split(":")[0]+":"+temp.split(":")[1])) {
			    		map_ids_database.put(temp.split(":")[0]+":"+temp.split(":")[1], Integer.parseInt(temp.split(":")[2]));
			    		mapImgs_forEach_lastMap.put(temp.split(":")[0]+":"+temp.split(":")[1],rs.getString("image"));
			    	}
			    }
			    else {
			    	map_ids_database.put(temp.split(":")[0]+":"+temp.split(":")[1], Integer.parseInt(temp.split(":")[2]));
			    	mapImgs_forEach_lastMap.put(temp.split(":")[0]+":"+temp.split(":")[1],rs.getString("image"));
			    }
			}
			rs.close();
			prep_stmt.close();
			
			Set<String> keys = map_ids_database.keySet();
	        
	        for(String key: keys){
	        	if(key!=null) {
	        	//String cityId=key.split(":")[0];
	        	//String mapIds=key.split(":")[1];
	        	mapIds_forEach_city.put(key.split(":")[0],( mapIds_forEach_city.get(key.split(":")[0])+","+key.split(":")[1])); 
	            //System.out.println("Value of "+key+" is: "+hm.get(key));
	        }
	        }
	        
	         
	        keys = mapIds_forEach_city.keySet();
	        
	        
	        /******now start while in all the cities*******/
	        for(String k: keys){
	        	String cityId=k;
	        	String cityTableName="";
	        	PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM City WHERE CityId= ?"); 
	        	prep_stmt1.setString(1, cityId);
	        	ResultSet rs1 = prep_stmt1.executeQuery();
				while (rs1.next()) {
					cityTableName=rs1.getString("CityName");
				}
				rs1.close();
				prep_stmt1.close();
				if(cityTableName.equals("")) {
					return false;
				}
				else {
					
					
					 Statement stmt2 = null;
					 stmt2 = conn.createStatement();
					String sql = "SELECT * FROM "+ cityTableName;
					ResultSet rs2 = stmt2.executeQuery(sql);
					Hashtable< String,Integer> mapIdsForCurrentCityTable = new Hashtable< String,Integer>(); 
					while (rs2.next()) {						
						String map_id_of_city=rs2.getString("MapId");
						//String tmp_last_cityAmap=map_id_of_city.split(":")[0]+":"+map_id_of_city.split(":")[1];
						//int tmp_last_versionNum=map_ids_database.get(tmp_last_cityAmap);
						mapIdsForCurrentCityTable.put(map_id_of_city.split(":")[0]+":"+map_id_of_city.split(":")[1], Integer.parseInt( map_id_of_city.split(":")[2]));

					}
						
					rs2.close();
					stmt2.close();
					String[] mapIdsForCurrentCityinMap=(mapIds_forEach_city.get(k)).split(",");
					List<String> versionNumsCurrentCityTable = new ArrayList<String>(); 
					
					Set<String> city_keys = mapIdsForCurrentCityTable.keySet();
					for(String key:city_keys) {
						versionNumsCurrentCityTable.add(key.split(":")[1]);
					}

					
					for(int i=0;i<mapIdsForCurrentCityinMap.length;i++) {

						if(!mapIdsForCurrentCityinMap[i].equals("null")) {
							
							String tmp_last_cityAmap=cityId+":"+mapIdsForCurrentCityinMap[i];
							int tmp_last_versionNum=map_ids_database.get(tmp_last_cityAmap);
							if(versionNumsCurrentCityTable.contains(mapIdsForCurrentCityinMap[i])) {
							
							if(mapIdsForCurrentCityTable.get(tmp_last_cityAmap)<tmp_last_versionNum) {
								
								cities_with_new_version.add(tmp_last_cityAmap.split(":")[0]);
									PreparedStatement prep_stmt3 = conn.prepareStatement("UPDATE "+cityTableName+" SET image = ? WHERE MapId=?");
									//prep_stmt3.setString(1, cityTableName);
									prep_stmt3.setString(1, mapImgs_forEach_lastMap.get(tmp_last_cityAmap));
									prep_stmt3.setString(2, tmp_last_cityAmap+":"+mapIdsForCurrentCityTable.get(tmp_last_cityAmap));
									prep_stmt3.executeUpdate();
									prep_stmt3.close();
									
									PreparedStatement prep_stmt4 = conn.prepareStatement("UPDATE "+cityTableName+" SET MapId = ? WHERE MapId=?");
									//prep_stmt4.setString(1, cityTableName);
									prep_stmt4.setString(1, (tmp_last_cityAmap+":"+Integer.toString(tmp_last_versionNum)));
									prep_stmt4.setString(2, tmp_last_cityAmap+":"+mapIdsForCurrentCityTable.get(tmp_last_cityAmap));
									prep_stmt4.executeUpdate();
									prep_stmt4.close();	
								}								
								
							}
							else {
								
								cities_with_new_version.add(tmp_last_cityAmap.split(":")[0]);
								PreparedStatement prep_stmt5 = conn.prepareStatement(
										"INSERT INTO "+ cityTableName+" VALUES( ?, ?,?)");
								//prep_stmt5.setString(1, cityTableName);
								prep_stmt5.setString(1, cityId);
								prep_stmt5.setString(2, tmp_last_cityAmap+":"+tmp_last_versionNum);
								prep_stmt5.setString(3, mapImgs_forEach_lastMap.get(tmp_last_cityAmap));
								prep_stmt5.executeUpdate();
								prep_stmt5.close();								
								
							}

							
						}
					}
				}
				
				//DELETE FROM `Path` WHERE `Path`.`PathId` = \'2\'”
	        }
	        /****end of while of the cities in the database***/
	        //now delete every thing in maps of last version
	        //and delete it if it in tables InterestingPlace and paths
			List<String> map_ids_MapTable = new ArrayList<String>(); 
			//List<String> keys_AllMap = new ArrayList<String>(); 
			Statement stmt6 = null;
			stmt6 = conn.createStatement();
			String sql6 = "SELECT * FROM Map";
			ResultSet rs6 = stmt6.executeQuery(sql6);
     		while (rs6.next()) {
     			String temp=rs6.getString("MapId");
     			map_ids_MapTable.add(temp);
     			//keys_AllMap.add(temp.split(":")[0]+":"+temp.split(":")[1]);
     			//map_ids_MapTable.put(temp.split(":")[0]+":"+temp.split(":")[1], Integer.parseInt(temp.split(":")[2]));
			}
			rs6.close();
			stmt6.close();
			
			/*********************/
		    List<String> map_ids_PathTable = new ArrayList<String>();
			//List<String>  keys_AllPath = new ArrayList<String>();
				Statement stmt8 = null;
				stmt8 = conn.createStatement();
				String sql8 = "SELECT * FROM Path";
				ResultSet rs8 = stmt8.executeQuery(sql8);
	     		while (rs8.next()) {
	     			String temp=rs8.getString("MapId");
	     			map_ids_PathTable.add(temp);
	     			//keys_AllPath.add(temp.split(":")[0]+":"+temp.split(":")[1]);
				}
				rs8.close();
				stmt8.close();				
				
				/*****************/
				List<String> map_ids_PlacesTable = new ArrayList<String>();
				//List<String>  keys_AllPlaces = new ArrayList<String>();				
				 
					Statement stmt9 = null;
					stmt9 = conn.createStatement();
					String sql9 = "SELECT * FROM InterstingPlace";
					ResultSet rs9 = stmt9.executeQuery(sql9);
		     		while (rs9.next()) {
		     			String temp=rs9.getString("MapId");
		     			map_ids_PlacesTable.add(temp);
		     			//keys_AllPlaces.add(temp.split(":")[0]+":"+temp.split(":")[1]);
		     			//map_ids_PlacesTable.put(temp.split(":")[0]+":"+temp.split(":")[1], Integer.parseInt(temp.split(":")[2]));
					}
					rs9.close();
					stmt9.close();				

					
					/*****************/
			Set<String> keys_Yes = map_ids_database.keySet();
			for (int i = 0; i < map_ids_MapTable.size(); i++) {
			 //for(String key: keys_AllMap){
				String temp=map_ids_MapTable.get(i);
				String key=temp.split(":")[0]+":"+temp.split(":")[1];
				 if(keys_Yes.contains(key) && map_ids_database.get(key)>Integer.parseInt(temp.split(":")[2])) {

						PreparedStatement prep_stmt7 = conn.prepareStatement(
								"DELETE FROM Map WHERE MapId= ? ");
						//prep_stmt5.setString(1, cityTableName);
						prep_stmt7.setString(1, temp);
						prep_stmt7.executeUpdate();
						prep_stmt7.close();
				 }
				 
			 }
			 

				// for(String key: keys_AllPath){
					 for (int i = 0; i <map_ids_PathTable.size(); i++) {	
							String temp=map_ids_PathTable.get(i);
							String key=temp.split(":")[0]+":"+temp.split(":")[1];						 
					// if(keys_Yes.contains(key) && map_ids_database.get(key)>map_ids_PathTable.get(key)) {
					 if(keys_Yes.contains(key) && map_ids_database.get(key)>Integer.parseInt(temp.split(":")[2])) {
							PreparedStatement prep_stmt7 = conn.prepareStatement(
									"DELETE FROM Path WHERE MapId= ? ");
							//prep_stmt5.setString(1, cityTableName);
							prep_stmt7.setString(1, temp);
							prep_stmt7.executeUpdate();
							prep_stmt7.close();
					 }
					 
				 }
				 
				// for(String key: keys_AllPlaces){
					 for (int i = 0; i <map_ids_PlacesTable.size(); i++) {	
							String temp=map_ids_PlacesTable.get(i);
							String key=temp.split(":")[0]+":"+temp.split(":")[1];						 
					 if(keys_Yes.contains(key) && map_ids_database.get(key)>Integer.parseInt(temp.split(":")[2])) {					 
					
							PreparedStatement prep_stmt7 = conn.prepareStatement(
									"DELETE FROM InterstingPlace WHERE MapId= ? ");
							//prep_stmt5.setString(1, cityTableName);
							prep_stmt7.setString(1, temp);
							prep_stmt7.executeUpdate();
							prep_stmt7.close();
					 }
					 
				 }
					 
						/*********************/
                // check if there is remove path from the employees
							Statement stmt10 = null;
							stmt10= conn.createStatement();
							String sql10 = "SELECT * FROM Path";
							ResultSet rs10 = stmt10.executeQuery(sql10);
				     		while (rs10.next()) {
				     			int remove=rs10.getInt("remove");
				     			String MapId=rs10.getString("MapId");
				     			String CityId="";				     			
				     			String CityName="";
				     			
				     			
				     			if(remove==1) {	     				
				     				
				     				
				     				PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
				     				prep_stmt1.setString(1, MapId);
				     				ResultSet rs1 = prep_stmt1.executeQuery();
				     				while (rs1.next()) {
				     					CityId = rs1.getString("CityId");
				     					if (CityId == null)
				     						return false;
				     					else {
				     						PreparedStatement prep_stmt2 = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
				     						prep_stmt2.setString(1, CityId);
				     						ResultSet rs2 = prep_stmt2.executeQuery();
				     						while (rs2.next()) {
				     							CityName = rs2.getString("CityName");
				     							if (CityName == null)
				     								return false;
				     							else {
				     								String sql = "SELECT * FROM " + CityName;
				     								ResultSet rs3 = stmt.executeQuery(sql);
				     								while (rs3.next()) {
				     									PreparedStatement prep_stmt4 = conn.prepareStatement("DELETE FROM "+CityName+" WHERE MapId=?");
				     									prep_stmt4.setString(1, MapId);
				     									prep_stmt4.executeUpdate();
				     									prep_stmt4.close();
				     								}
				     			
				     								rs3.close();
				     								stmt.close();
				     							}
				     			
				     						}
				     						rs2.close();
				     						prep_stmt2.close();
				     			
				     						PreparedStatement prep_stmt5 = conn.prepareStatement("DELETE FROM Map WHERE MapId=?");
				     						prep_stmt5.setString(1, MapId);
				     						prep_stmt5.executeUpdate();
				     						prep_stmt5.close();
				     						prep_stmt5 = conn.prepareStatement("DELETE FROM Path WHERE MapId=?");
				     						prep_stmt5.setString(1, MapId);
				     						prep_stmt5.executeUpdate();
				     						prep_stmt5.close();
				     			
				     					}
				     				}
				     				rs1.close();
				     				prep_stmt1.close();
				     				return true;
				     			}

				     		
				     		}
							rs10.close();
							stmt10.close();	
							
		/***********send to users there is new version**************/
							

		return true;
			}
			
		 catch (SQLException se) {
			se.printStackTrace();
			System.out.println("SQLException: " + se.getMessage());
			System.out.println("SQLState: " + se.getSQLState());
			System.out.println("VendorError: " + se.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return true;
		
				
	}

	/*
	
	public boolean versionApp(Version version) {
		
	}
	
	public void changePrice(int NewPrice, int CityId, int VresionNum, int UserType, boolean Permission ) {
		
	}
	
	public boolean getPermission(CompanyManager company_manager, int CityId, int VersionNum, int UserType, int NewPrice) {
		
	}
	
	public void seeReports(String CityName) {
		
	}
	*/


}
