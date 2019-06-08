package models;

public class Employee extends Client{
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
	private String EmployeeNum;
	private String Job;
	
	
	public String getEmployeeNum() {
		return EmployeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		EmployeeNum = employeeNum;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}
	

}
