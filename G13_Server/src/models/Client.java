package models;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.mysql.cj.jdbc.Blob;

public class Client extends Person {
	///// *******databasee******////
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
	//// *******************************************////
	static int place = 0;
	static int id = 0;

	public static int SignIn(String email, String pass) {
		Connection conn = null;
		Statement stmt = null;
		String useremail = null;
		String userpass = null;
		int access = 0;
		/// ***sql***///
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//**whire sql here**//
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM CustomerCard WHERE Email=?");
			prep_stmt.setString(1, email);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				useremail = rs.getString("Email");
				userpass = rs.getString("password");
				access = rs.getInt("Checkaccess");

			}
			rs.close();
			prep_stmt.close();
			if (useremail == null) {
				return 1;

			} else if (useremail != null) {
				if (!(userpass.equals(pass))) {
					return 2;
				} else {
					if (access == 0) {
						PreparedStatement prep_stmt1 = conn
								.prepareStatement("UPDATE CustomerCard SET Checkaccess = ? WHERE Email=?");
						prep_stmt1.setInt(1, 1);
						prep_stmt1.setString(2, useremail);
						prep_stmt1.executeUpdate();
						prep_stmt1.close();
						conn.close();

						return 0;
					} else {
						return 3;
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
		return 3;

	}

	public static boolean Signout(String email) {
		Connection conn = null;
		Statement stmt = null;
		String useremail = null;
		int access = 0;
		/// ***sql***///
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//**whire sql here**//
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM CustomerCard WHERE Email=?");
			prep_stmt.setString(1, email);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				useremail = rs.getString("Email");
				access = rs.getInt("Checkaccess");
			}
			rs.close();
			prep_stmt.close();
			if (access == 1) {
				PreparedStatement prep_stmt1 = conn
						.prepareStatement("UPDATE CustomerCard SET Checkaccess = ? WHERE Email=?");
				prep_stmt1.setInt(1, 0);
				prep_stmt1.setString(2, useremail);
				prep_stmt1.executeUpdate();
				prep_stmt1.close();
				conn.close();
				return true;
			}
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
		return false;

	}

	public static boolean savemap(String pathtosave) {
		Connection conn = null;
		Statement stmt = null;
		/// ***sql***///
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			int i = 0;

			stmt = conn.createStatement();
			String sql = "SELECT * FROM Map";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				File file = new File(pathtosave + "'\'" + "map" + Integer.toString(i) + ".jpg");
				FileOutputStream fos = new FileOutputStream(file);
				byte b[];
				Blob blob;
				blob = (Blob) rs.getBlob("Im");
				b = blob.getBytes(1, (int) blob.length());
				fos.write(b);
				fos.close();
				i++;
			}
			rs.close();
			stmt.close();
			return true;

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
		return false;

	}

	public static String ShowPreviousCity() {
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		String CityId = null;
		String TheResultText;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			List<String> City = new ArrayList<String>();
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City");
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				City.add(rs.getString("CityName"));
			}
			rs.close();
			prep_stmt.close();
			place--;
			if (place < 0) {
				place = 0;
			} else {
				String cityName = City.get(place);
				int countmap=0;
				String sql = "SELECT * FROM " + cityName;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					countmap++;
					CityId = rs.getString("CityId");

				}
				rs.close();
				int countpath = 0;
				sql = "SELECT * FROM  Path";
				ResultSet rs1 = stmt.executeQuery(sql);
				while (rs1.next()) {
					sql = "SELECT * FROM  " + cityName;
					ResultSet rs2 = stmt2.executeQuery(sql);
					while (rs2.next()) {
						if (rs1.getString("MapId") == rs2.getString("MapId")) {
							countpath++;
						}
					}
					rs2.close();
					stmt2.close();
				}
				rs1.close();
				int countp = 0;
				sql = "SELECT * FROM  InterstingPlace ";
				ResultSet rs3 = stmt.executeQuery(sql);
				while (rs3.next()) {
					sql = "SELECT * FROM  " + cityName;
					ResultSet rs4 = stmt1.executeQuery(sql);
					while (rs3.next()) {
						if (rs3.getString("CityId") == rs4.getString("CityId")) {
							countp++;
						}
					}
					rs4.close();
					stmt1.close();
				}
				rs3.close();
				
				TheResultText = CityId + "@"+"The City " + cityName + " has: " + countmap + " maps " + " and: " + countpath
						+ " paths " + "and: " + countp + " places.";
				prep_stmt.close();
				rs.close();
//				String sql = "SELECT * FROM " + cityName;
//				rs = stmt.executeQuery(sql);
//				List<String> MapID = new ArrayList<String>();
//
//				while (rs.next()) {
//					MapID.add(rs.getString("MapId"));
//					CityId = rs.getString("CityId");
//				}
//				rs.close();
//				prep_stmt.close();
//				TheResultText = CityId + "@";
//				Iterator<String> iter = MapID.iterator();
//				while (iter.hasNext()) {
//					prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=? ");
//					prep_stmt.setString(1, (String) iter.next());
//					ResultSet rs1 = prep_stmt.executeQuery();
//					while (rs1.next()) {
//						TheResultText = TheResultText + rs1.getString("description") + "#";
//					}
//					rs1.close();
//
//				}
//				prep_stmt.close();
//				rs.close();
				stmt.close();
				conn.close();

				return TheResultText;
			}
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
		return null;

	}

	@SuppressWarnings("null")
	public static String ShowNextCity() {

		Connection conn = null;
		Statement stmt = null;
		Statement stmt1=null;
		Statement stmt2=null;
		String CityId = null;
		String TheResultText;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); 
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			List<String> City = new ArrayList<String>();
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City");
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				City.add(rs.getString("CityName"));
			}
			rs.close();
			prep_stmt.close();
			place++;
			if (place > City.size() - 1) {
				place = City.size() - 1;
			} else {
//				List<String> MapID = new ArrayList<String>();
				String cityName = City.get(place);
//				prep_stmt = conn.prepareStatement("SELECT * FROM " + cityName);
//				rs = prep_stmt.executeQuery();
//				while (rs.next()) {
//					MapID.add(rs.getString("MapId"));
//					CityId = rs.getString("CityId");
//				}
//				rs.close();
//				prep_stmt.close();
//				TheResultText = CityId + "@";
//				Iterator<String> iter = MapID.iterator();
//				while (iter.hasNext()) {
//					prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=? ");
//					prep_stmt.setString(1, (String) iter.next());
//					ResultSet rs1 = prep_stmt.executeQuery();
//					while (rs1.next()) {
//						TheResultText = TheResultText + rs1.getString("description") + "#";
//					}
//					rs1.close();
//
//				}
				int countmap=0;
				String sql = "SELECT * FROM " + cityName;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					countmap++;
					CityId = rs.getString("CityId");

				}
				rs.close();
				int countpath = 0;
				sql = "SELECT * FROM  Path";
				ResultSet rs1 = stmt.executeQuery(sql);
				while (rs1.next()) {
					sql = "SELECT * FROM  " + cityName;
					ResultSet rs2 = stmt2.executeQuery(sql);
					while (rs2.next()) {
						if (rs1.getString("MapId") == rs2.getString("MapId")) {
							countpath++;
						}
					}
					rs2.close();
					stmt2.close();
				}
				rs1.close();
				int countp = 0;
				sql = "SELECT * FROM  InterstingPlace ";
				ResultSet rs3 = stmt.executeQuery(sql);
				while (rs3.next()) {
					sql = "SELECT * FROM  " + cityName;
					ResultSet rs4 = stmt1.executeQuery(sql);
					while (rs3.next()) {
						if (rs3.getString("CityId") == rs4.getString("CityId")) {
							countp++;
						}
					}
					rs4.close();
					stmt1.close();
				}
				rs3.close();
				
				TheResultText = CityId + "@"+"The City " + cityName + " has: " + countmap + " maps " + " and: " + countpath
						+ " paths " + "and: " + countp + " places.";
				prep_stmt.close();
				rs.close();
				stmt.close();
				conn.close();
				return TheResultText;

			}
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
		return null;
	}

	@SuppressWarnings("null")
	public static String ShowFirstCity() {

		Connection conn = null;
		Statement stmt = null;
		Statement stmt1=null;
		Statement stmt2=null;
		String CityId = null;
		String TheResultText;
        int countmap=0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			List<String> City = new ArrayList<String>();
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City");
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				City.add(rs.getString("CityName"));
			}
			prep_stmt.close();
			if (City.size() > 0) {
				String cityName = City.get(0);
				String sql = "SELECT * FROM " + cityName;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					countmap++;
					CityId = rs.getString("CityId");

				}
				rs.close();
				int countpath = 0;
				sql = "SELECT * FROM  Path";
				ResultSet rs1 = stmt.executeQuery(sql);
				while (rs1.next()) {
					sql = "SELECT * FROM  " + cityName;
					ResultSet rs2 = stmt2.executeQuery(sql);
					while (rs2.next()) {
						if (rs1.getString("MapId") == rs2.getString("MapId")) {
							countpath++;
						}
					}
					rs2.close();
					stmt2.close();
				}
				rs1.close();
				int countp = 0;
				sql = "SELECT * FROM  InterstingPlace ";
				ResultSet rs3 = stmt.executeQuery(sql);
				while (rs3.next()) {
					sql = "SELECT * FROM  " + cityName;
					ResultSet rs4 = stmt1.executeQuery(sql);
					while (rs3.next()) {
						if (rs3.getString("CityId") == rs4.getString("CityId")) {
							countp++;
						}
					}
					rs4.close();
					stmt1.close();
				}
				rs3.close();
				
				TheResultText = CityId + "@"+"The City " + cityName + " has: " + countmap + " maps " + " and: " + countpath
						+ " paths " + "and: " + countp + " places.";
//				List<String> MapID = new ArrayList<String>();
//				while (rs.next()) {
//					MapID.add(rs.getString("MapId"));
//					CityId = rs.getString("CityId");
//				}
//				rs.close();
//				stmt.close();
//				TheResultText = CityId + "@";
//				Iterator<String> iter = MapID.iterator();
//				while (iter.hasNext()) {
//					prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=? ");
//					prep_stmt.setString(1, (String) iter.next());
//					ResultSet rs1 = prep_stmt.executeQuery();
//					while (rs1.next()) {
//						TheResultText = TheResultText + rs1.getString("description") + "#";
//					}
//					rs1.close();
//
//				}
				
				prep_stmt.close();
				stmt.close();
				conn.close();
				return TheResultText;
				
				
				
			}
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
		return null;
	}

	@SuppressWarnings("unused")
	public static int download(String CityId, String Path, String Email) {
		Connection conn = null;
		Statement stmt = null;
		String CityName = null;
		String type = null;
		String email = null;
		String Date = null;
		int Down = 0;
/// ***sql***///
		try {
			int i = 0;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			PreparedStatement prep_stmt3 = conn
					.prepareStatement("SELECT * FROM Purchase WHERE Email= ?" + " AND " + "CityId=?");
			prep_stmt3.setString(1, Email);
			prep_stmt3.setString(2, CityId);
			ResultSet rs3 = prep_stmt3.executeQuery();
			while (rs3.next()) {
				CityName = rs3.getString("CityId");
				email = rs3.getString("Email");
				type = rs3.getString("Type");
				Date = rs3.getString("Date");
				Down = rs3.getInt("downloads");
			}
			rs3.close();
			prep_stmt3.close();
			if (email == null || CityName == null) {
				return 0;
			} else {
				if (type.equals("onetime")) {
					if (Down == 1) {
						return 1;
					} else {
						LocalDate localdate = LocalDate.now();
						if (Date.equals(localdate.toString())) {
							PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
							prep_stmt.setString(1, CityId);
							ResultSet rs = prep_stmt.executeQuery();
							while (rs.next()) {
								CityName = rs.getString("CityName");
							}
							if (CityName == null) {
								return 0;
							} else {
								String sql = "SELECT MapId FROM " + CityName;
								ResultSet rs1 = stmt.executeQuery(sql);
								while (rs1.next()) {
									PreparedStatement prep_stmt2 = conn
											.prepareStatement("SELECT * FROM Map WHERE MapId= ? ");
									prep_stmt2.setString(1, rs1.getString("MapId"));
									ResultSet rs2 = prep_stmt2.executeQuery();
									while (rs2.next()) {
										File file = new File(Path + "\\" + CityName + Integer.toString(i) + ".jpg");
										FileOutputStream fos = new FileOutputStream(file);
										byte b[];
										Blob blob;
										blob = (Blob) rs2.getBlob("Im");
										b = blob.getBytes(1, (int) blob.length());
										fos.write(b);
										i++;
										fos.close();
									}
									rs2.close();
									prep_stmt2.close();

								}

								rs1.close();
								prep_stmt.close();
								rs.close();
								stmt.close();
								PreparedStatement prep_stmt4 = conn.prepareStatement(
										"UPDATE Purchase SET downloads= ? WHERE CityId=? AND Email=?");
								prep_stmt4.setInt(1, 1);
								prep_stmt4.setString(2, CityId);
								prep_stmt4.setString(3, email);
								prep_stmt4.executeUpdate();
								prep_stmt4.close();
								conn.close();
								return 2;
							}

						}

						else {
							return 1;
						}
					}
				} else if (type.equals("sub")) {
					LocalDate localdate = LocalDate.now();
					LocalDate CheckDate = LocalDate.parse(Date);
					LocalDate dateafter = CheckDate.plusMonths(6);
					if (dateafter.isBefore(localdate)) {
						return 0;
					} else {
						PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
						prep_stmt.setString(1, CityId);
						ResultSet rs = prep_stmt.executeQuery();
						while (rs.next()) {
							CityName = rs.getString("CityName");
						}
						if (CityName == null) {
							return 0;
						} else {
							String sql = "SELECT MapId FROM " + CityName;
							ResultSet rs1 = stmt.executeQuery(sql);
							while (rs1.next()) {
								PreparedStatement prep_stmt2 = conn
										.prepareStatement("SELECT * FROM Map WHERE MapId= ? ");
								prep_stmt2.setString(1, rs1.getString("MapId"));
								ResultSet rs2 = prep_stmt2.executeQuery();
								while (rs2.next()) {
									File file = new File(Path + "\\" + CityName + Integer.toString(i) + ".jpg");
									FileOutputStream fos = new FileOutputStream(file);
									byte b[];
									Blob blob;
									blob = (Blob) rs2.getBlob("Im");
									b = blob.getBytes(1, (int) blob.length());
									fos.write(b);
									i++;
									fos.close();
								}
								rs2.close();
								prep_stmt2.close();

							}

							rs1.close();
							prep_stmt.close();
							rs.close();
							stmt.close();
							PreparedStatement prep_stmt4 = conn
									.prepareStatement("UPDATE Purchase SET downloads= ? WHERE CityId=? AND Email=?");
							prep_stmt4.setInt(1, Down + 1);
							prep_stmt4.setString(2, CityId);
							prep_stmt4.setString(3, email);
							prep_stmt4.executeUpdate();
							prep_stmt4.close();
							conn.close();
							return 2;
						}
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
		return 0;

	}

	public static boolean Purchase(String CityId, String email, String type) {
		Connection conn = null;
		Statement stmt = null;
		String Email = null;
         String city=null;
         List<String> myDATE=new ArrayList<String>();
         String Type=null;
		/// ***sql***///
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//**whire sql here**//
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Purchase WHERE Email=? AND CityId=? AND Type=?");
			prep_stmt.setString(1, email);
			prep_stmt.setString(2, CityId);
			prep_stmt.setString(3, type);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				myDATE.add(rs.getString("Date"));		
			}
			rs.close();
			prep_stmt.close();
			if (myDATE.size()==0) {
			
				PreparedStatement prep_stmt1 = conn.prepareStatement("INSERT INTO Purchase VALUES(?, ?, ?, ?, ?,?)");
				LocalDate localdate = LocalDate.now();
				prep_stmt1.setInt(1, id);
				id = id + 1;
				prep_stmt1.setString(2, email);
				prep_stmt1.setString(3, CityId);
				prep_stmt1.setString(4, type);
				prep_stmt1.setString(5, localdate.toString());
				prep_stmt1.setInt(6, 0);
				prep_stmt1.executeUpdate();
				return true;
			} else {
				if(type.equals("onetime"))
				{
					LocalDate localdate = LocalDate.now();
					if(myDATE.contains(localdate.toString())) {
						return false;
					}
					else
					{
						PreparedStatement prep_stmt2 = conn.prepareStatement("INSERT INTO Purchase VALUES(?, ?, ?, ?, ?,?)");
						localdate = LocalDate.now();
						prep_stmt2.setInt(1, id);
						id = id + 1;
						prep_stmt2.setString(2, email);
						prep_stmt2.setString(3, CityId);
						prep_stmt2.setString(4, type);
						prep_stmt2.setString(5, localdate.toString());
						prep_stmt2.setInt(6, 0);
						prep_stmt2.executeUpdate();
					    prep_stmt2.close();
						return true;
					}
				}
				else if(type.equals("sub"))
				{
					Iterator it=myDATE.iterator();
					while(it.hasNext())
					{
					LocalDate localdate = LocalDate.now();
			        LocalDate CheckDate = LocalDate.parse((CharSequence) it.next());
					LocalDate dateafter  = CheckDate.plusMonths(6); 
					if(dateafter.isAfter(localdate))
					{
						return false;
					}
					}
						PreparedStatement prep_stmt2 = conn.prepareStatement("INSERT INTO Purchase VALUES(?, ?, ?, ?,?, ?)");
						LocalDate localdate= LocalDate.now();
						prep_stmt2.setInt(1, id);
						id = id + 1;
						prep_stmt2.setString(2, email);
						prep_stmt2.setString(3, CityId);
						prep_stmt2.setString(4, type);
						prep_stmt2.setString(5, localdate.toString());
						prep_stmt2.setInt(6, 0);
						prep_stmt2.executeUpdate();
					    prep_stmt2.close();
						return true;
					
				}

				return false;
			}
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
		return false;

	}

	public static String PersonalInformation(String MYText) {
		Connection conn = null;
		Statement stmt = null;
		String Email = MYText;
		String resultMsg = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			stmt = conn.createStatement();

			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM CustomerCard WHERE Email=?");
			prep_stmt.setString(1, Email);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {

				resultMsg = rs.getString("Email") + "#" + rs.getString("password") + "#" + rs.getString("FirstName")
						+ "#" + rs.getString("LastName") + "#" + rs.getString("Tel") + "#" + rs.getString("VisaNum")
						+ "#" + rs.getString("CVV") + "#" + rs.getString("Date") + "#" + rs.getString("ID");

			}
			prep_stmt.close();
			rs.close();
			conn.close();

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

	public static String search(String searchcity) {
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		List<String> out = new ArrayList<String>();

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();

			/**** write sql here ****/
			if (searchcity == null) {
				return null;
			} else {
				String sql = "SELECT CityName FROM City ";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (searchcity.toUpperCase().contains(rs.getString("CityName").toUpperCase())) {
						out.add(rs.getString("CityName"));
					}
				}
				rs.close();
				sql = "SELECT * FROM Map ";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (rs.getString("description").toUpperCase().contains(searchcity.toUpperCase())
							|| searchcity.toUpperCase().contains(rs.getString("description").toUpperCase())) {
						PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
						prep_stmt.setString(1, rs.getString("CityId"));
						ResultSet rs1 = prep_stmt.executeQuery();
						while (rs1.next()) {
							if (!out.contains(rs1.getString("CityName"))) {
								out.add(rs1.getString("CityName"));
							}
						}
						prep_stmt.close();
						rs1.close();
					}

				}
				rs.close();
				sql = "SELECT * FROM InterstingPlace";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (rs.getString("description").toUpperCase().contains(searchcity.toUpperCase())
							|| searchcity.toUpperCase().contains(rs.getString("description").toUpperCase())
							|| searchcity.toUpperCase().contains(rs.getString("PlaceName").toUpperCase())) {
						PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
						prep_stmt.setString(1, rs.getString("CityId"));
						ResultSet rs1 = prep_stmt.executeQuery();
						while (rs1.next()) {
							if (!out.contains(rs1.getString("CityName"))) {
								out.add(rs1.getString("CityName"));
							}
						}
						prep_stmt.close();
						rs1.close();

					}

				}
				rs.close();
				sql = "SELECT * FROM Path";
				rs = stmt.executeQuery(sql);
				String MapID = null;
				while (rs.next()) {
					if (rs.getString("description").toUpperCase().contains(searchcity.toUpperCase())
							|| searchcity.toUpperCase().contains(rs.getString("description").toUpperCase())) {
						MapID = rs.getString("MapId");
					}
					sql = "SELECT * FROM Map WHERE MapId=" + MapID;
					ResultSet rs1 = stmt1.executeQuery(sql);
					while (rs1.next()) {

						PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM City WHERE CityId=?");
						prep_stmt.setString(1, rs.getString("CityId"));
						ResultSet rs2 = prep_stmt.executeQuery();
						while (rs2.next()) {
							if (!out.contains(rs1.getString("CityName"))) {
								out.add(rs1.getString("CityName"));
							}
						}
						prep_stmt.close();
						rs2.close();
					}
					rs1.close();
				}
				rs.close();

//				String[] type = new String[100];
				int j = 0;
				String result = "ClientSearch" + "@";
				if (!out.equals(null)) {
					Iterator iterator = out.iterator();
					while (iterator.hasNext()) {
						int countmap = 0;
						String city = (String) iterator.next();
						sql = "SELECT * FROM  " + city;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							countmap++;
						}
						rs.close();
						int countpath = 0;
						sql = "SELECT * FROM  Path";
						ResultSet rs1 = stmt.executeQuery(sql);
						while (rs1.next()) {
							sql = "SELECT * FROM  " + city;
							ResultSet rs2 = stmt1.executeQuery(sql);
							while (rs2.next()) {
								if (rs1.getString("MapId") == rs2.getString("MapId")) {
									countpath++;
								}
							}
							rs2.close();
						}
						rs1.close();
						int countp = 0;
						sql = "SELECT * FROM  InterstingPlace ";
						ResultSet rs3 = stmt.executeQuery(sql);
						while (rs3.next()) {
							sql = "SELECT * FROM  " + city;
							ResultSet rs4 = stmt1.executeQuery(sql);
							while (rs3.next()) {
								if (rs3.getString("CityId") == rs4.getString("CityId")) {
									countp++;
								}
							}
							rs4.close();
						}
						rs3.close();

//						sql = "SELECT * FROM  " + city;
//						rs = stmt.executeQuery(sql);
//						while (rs.next()) {
//							PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Map WHERE MapId=?");
//							prep_stmt.setString(1, rs.getString("MapId"));
//							ResultSet rs1 = prep_stmt.executeQuery();
//							while (rs1.next()) {
//								String desc = rs1.getString("description");
//								if (type[0] == null) {
//									type[j] = desc;
//									j++;
//								} else if (type[0] != null && !type[j - 1].equals(desc)) {
//									type[j] = desc;
//									j++;
//								}
//							}
//							rs1.close();
//
//							prep_stmt.close();
//						}
						result = result + "The City " + city + " has: " + countmap + " maps " + " and: " + countpath
								+ " paths " + "and: " + countp + " places.";
//						+  "and has many types: ";
//						for (int m = 0; m < type.length; m++) {
//							if (type[m] != null) {
//								result = result + type[m] + ",";
//							}
//						}
						result = result + "#";
//						for (int m = 0; m < type.length; m++) {
//							type[m] = null;
//						}
//						rs.close();
					}
					stmt.close();
					stmt1.close();
					out.clear();
					return result;

				} else {
					return null;
				}
			}
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
				if (stmt1 != null)
					stmt1.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return null;

	}

	public static boolean EditPersonalInformation(String kind, String TheEmail, String MyText) {
		Connection conn = null;
		Statement stmt = null;
		String WhatToSet = MyText;// information
		String WhatToEdit = kind;// kind of information

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM CustomerCard WHERE Email=?");
			prep_stmt.setString(1, TheEmail);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				PreparedStatement prep_stmt2 = conn.prepareStatement("SELECT Email FROM CustomerCard ");
				ResultSet rs1 = prep_stmt2.executeQuery();
				while (rs1.next()) {
					if (WhatToSet.equals(rs1.getString("Email"))) {
						return false;
					}
				}
				PreparedStatement prep_stmt1 = conn
						.prepareStatement("UPDATE CustomerCard SET " + WhatToEdit + "=?" + " WHERE Email=?");
				prep_stmt1.setString(1, WhatToSet);
				prep_stmt1.setString(2, TheEmail);
				prep_stmt1.executeUpdate();
				prep_stmt1.close();
			}
			prep_stmt.close();
			rs.close();
			conn.close();

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
		return true;
	}

	public static String getname(String Email) {
		Connection conn = null;
		Statement stmt = null;
		String Name = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // **whire sql here**//
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM CustomerCard WHERE Email=?");
			prep_stmt.setString(1, Email);
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				Name = rs.getString("FirstName");
			}

			prep_stmt.close();
			rs.close();
			conn.close();

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
		return Name;

	}

	
	public static String checktime(String email) {
		Connection conn = null;
		Statement stmt = null;
		String Email = null;
         String city=null;
         List<String> myDATE=new ArrayList<String>();
         List<String> cityId=new ArrayList<String>();
         String Type=null;
		/// ***sql***///
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//**whire sql here**//
			PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM Purchase WHERE Email=? AND Type=?");
			prep_stmt.setString(1, email);
			prep_stmt.setString(2, "sub");
			ResultSet rs = prep_stmt.executeQuery();
			while (rs.next()) {
				myDATE.add(rs.getString("Date"));		
			}
			rs.close();
			prep_stmt.close();
			if (myDATE.size()==0) {
			 return null;
			} else {
					Iterator it=myDATE.iterator();
					while(it.hasNext())
					{
					LocalDate localdate = LocalDate.now();
			        LocalDate CheckDate = LocalDate.parse((CharSequence) it.next());
					LocalDate dateafter  = CheckDate.plusMonths(6); 
					LocalDate  ExpireDate = dateafter.minusDays(3); 
if(ExpireDate.equals(localdate))
{
	PreparedStatement prep_stmt1 = conn.prepareStatement("SELECT * FROM Purchase WHERE Email=? AND Type=? AND Date=?");
	prep_stmt1.setString(1, email);
	prep_stmt1.setString(2, "sub");
	prep_stmt1.setString(3, CheckDate.toString());
	ResultSet rs1 = prep_stmt1.executeQuery();
	while(rs1.next())
	{
		cityId.add(rs1.getString("CityId"));
	}
}


					
				}
			String msg="i@";
			Iterator check=myDATE.iterator();
			while(check.hasNext())
			{
				msg=msg+","+check.next();
			}
			return msg;
			}
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
		return null;
	}
	

}
