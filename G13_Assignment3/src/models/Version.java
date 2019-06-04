package models;

import java.util.Vector;

public class Version {
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
	private int VersionNum;
	private int CityId;
	private int PriceOnePurchase;
	private int PriceForClient;
	private boolean ApprovedVersion;
	private  Vector<Map> VersionMaps;
	
	public int getVersionNum() {
		return VersionNum;
	}
	public void setVersionNum(int versionNum) {
		VersionNum = versionNum;
	}
	public int getCityId() {
		return CityId;
	}
	public void setCityId(int cityId) {
		CityId = cityId;
	}
	public int getPriceOnePurchase() {
		return PriceOnePurchase;
	}
	public void setPriceOnePurchase(int priceOnePurchase) {
		PriceOnePurchase = priceOnePurchase;
	}
	public int getPriceForClient() {
		return PriceForClient;
	}
	public void setPriceForClient(int priceForClient) {
		PriceForClient = priceForClient;
	}
	public boolean isApprovedVersion() {
		return ApprovedVersion;
	}
	public void setApprovedVersion(boolean approvedVersion) {
		ApprovedVersion = approvedVersion;
	}
	public Vector<Map> getVersionMaps() {
		return VersionMaps;
	}
	public void setVersionMaps(Vector<Map> versionMaps) {
		VersionMaps = versionMaps;
	}
	/*
	public void setTotalPrice(int newPrice, int UserType) {
		
	}
	
	public Version SearchVersion(int VersionNum, int CityId) {
		
	}
	*/

}
