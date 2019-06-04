package models;

import java.util.Vector;

public class CityReport {
	
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
	private int CityId;
	private int NumOneTimePurchase;
	private int NumOfClients;
	private int NumUpdates;
	private int NumOfViews;
	private int NumOfDownloads;
	private Vector<Client> ExistingClients;
	
	
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public int getCityId() {
		return CityId;
	}
	public void setCityId(int cityId) {
		CityId = cityId;
	}
	public int getNumOneTimePurchase() {
		return NumOneTimePurchase;
	}
	public void setNumOneTimePurchase(int numOneTimePurchase) {
		NumOneTimePurchase = numOneTimePurchase;
	}
	public int getNumOfClients() {
		return NumOfClients;
	}
	public void setNumOfClients(int numOfClients) {
		NumOfClients = numOfClients;
	}
	public int getNumUpdates() {
		return NumUpdates;
	}
	public void setNumUpdates(int numUpdates) {
		NumUpdates = numUpdates;
	}
	public int getNumOfViews() {
		return NumOfViews;
	}
	public void setNumOfViews(int numOfViews) {
		NumOfViews = numOfViews;
	}
	public int getNumOfDownloads() {
		return NumOfDownloads;
	}
	public void setNumOfDownloads(int numOfDownloads) {
		NumOfDownloads = numOfDownloads;
	}
	public Vector<Client> getExistingClients() {
		return ExistingClients;
	}
	public void setExistingClients(Vector<Client> existingClients) {
		ExistingClients = existingClients;
	}
	

}
