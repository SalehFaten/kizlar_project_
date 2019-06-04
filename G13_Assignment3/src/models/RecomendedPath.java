package models;

import java.util.Vector;

public class RecomendedPath extends Path{
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
	private Vector<InterestingPlace> PlacesInPath;
	private Vector<String> RecomendedTimeOfPath;
	
	public Vector<InterestingPlace> getPlacesInPath() {
		return PlacesInPath;
	}
	public void setPlacesInPath(Vector<InterestingPlace> placesInPath) {
		PlacesInPath = placesInPath;
	}
	public Vector<String> getRecomendedTimeOfPath() {
		return RecomendedTimeOfPath;
	}
	public void setRecomendedTimeOfPath(Vector<String> recomendedTimeOfPath) {
		RecomendedTimeOfPath = recomendedTimeOfPath;
	}

}
