package application;
//This file contains material supporting section 3.7 of the textbook:

//"Object Oriented Software Engineering" and is issued under the open-source
//license found at www.lloseng.com 

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ocsf.server.*;
import common.*;
import models.*;
import models.Map;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer {
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

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF serverUI;

//Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public EchoServer(int port) {
		super(port);
	}

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port     The port number to connect on.
	 * @param serverUI The interface type variable.
	 */
	Connection conn = null;

	public EchoServer(int port, ChatIF serverUI) throws IOException {
		super(port);
		this.serverUI = serverUI;
		/// ***sql***///
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//**whire sql here**//
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("SQLException: " + se.getMessage());
			System.out.println("SQLState: " + se.getSQLState());
			System.out.println("VendorError: " + se.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if (msg.toString().startsWith("#login ")) {
			if (client.getInfo("loginID") != null) {
				try {
					client.sendToClient("You are already logged in.");
				} catch (IOException e) {
				}
				return;
			}
			client.setInfo("loginID", msg.toString().substring(7));
		} else {
			if (client.getInfo("loginID") == null) {
				try {
					client.sendToClient("You need to login before you can chat.");
					client.close();
				} catch (IOException e) {
				}
				return;
			}
			String email = null;
			String pass = null;
			String firstname = null;
			String lastname = null;
			String tel = null;
			String visa = null;
			String cvv = null;
			String date = null;
			String id = null;
			String CityName = null;
			String CityId = null;
			String MapId = null;
			String description = null;
			String path = null;
			String PlaceName = null;
			String PlaceId = null;
			String PathId=null;
			String res=null;
			String show=null;
			String[] detail = ((String) msg).split("#");
//			String[] search = ((String) msg).split("%");

			String command = detail[0];
			switch (command) {
			case "SignUp":
				email = detail[1];
				pass = detail[2];
				firstname = detail[3];
				lastname = detail[4];
				tel = detail[5];
				visa = detail[6];
				cvv = detail[7];
				date = detail[8];
				id = detail[9];

				if (Person.register(firstname, lastname, tel, email, pass, visa, cvv, date, id) == true) {
					this.handleMessageFromServerUI("SignUp");
					break;

				} else {
					this.handleMessageFromServerUI("SignUpFailed");
					break;
				}

			case "SignIn":
				email = detail[1];
				pass = detail[2];
				if (email.contains("@map.co.il") || email.contains("@mapcd.co.il") || email.contains("@mapcdm.co.il")) {
					if (CDEmployee.SignIn(email, pass) == 0) {
						this.handleMessageFromServerUI("SignIn");
						break;

					} else if (CDEmployee.SignIn(email, pass) == 1) {
						this.handleMessageFromServerUI("NotFoundEmail");
						break;

					} else if (CDEmployee.SignIn(email, pass) == 2) {
						this.handleMessageFromServerUI("NotFoundPass");
						break;

					} else {
						this.handleMessageFromServerUI("SignFailed");
						break;

					}
				} else {
					if (Client.SignIn(email, pass) == 0) {
						this.handleMessageFromServerUI("SignIn");
						break;
					} else if (Client.SignIn(email, pass) == 1) {
						this.handleMessageFromServerUI("NotFoundEmail");
						break;

					} else if (Client.SignIn(email, pass) == 2) {
						this.handleMessageFromServerUI("NotFoundPass");
						break;

					} else if (Client.SignIn(email, pass) == 3) {
						this.handleMessageFromServerUI("SignFailed");
						break;

					}
				}
			case "Userlogout":
				email = detail[1];
				if (Client.Signout(email) == true) {
					this.handleMessageFromServerUI("Userlogout");
					break;

				}
				break;
			case "Employeelogout":
				email = detail[1];
				if (CDEmployee.Signout(email) == true) {
					this.handleMessageFromServerUI("Employeelogout");
					break;
				}

				break;

			case "Create":
				CityName = detail[1];
				CityId = detail[2];
				MapId = detail[3];
				description = detail[4];
				path = detail[5];
				if (City.CreateCity(CityName, CityId, MapId, description, path) == true) {
					this.handleMessageFromServerUI("Created");

					break;
				} else {
					this.handleMessageFromServerUI("NotCreated");
					break;
				}
			case "AddMap":
				CityId = detail[1];
				MapId = detail[2];
				description = detail[3];
				path = detail[4];
				if (Map.AddMapToCity(CityId, MapId, description, path) == true) {
					this.handleMessageFromServerUI("AddMap");
					break;
				} else {
					this.handleMessageFromServerUI("NotAdd");
					break;

				}
//			case "EmployeeSeeMapId":
//				MapId = detail[1];
//				String mypath = "MapPath@" + Map.showmap(MapId);
//				this.handleMessageFromServerUI(mypath);
//				break;
			case "AddPlace":
				PlaceName = detail[1];
				PlaceId = detail[2];
				description = detail[3];
				MapId = detail[4];
				CityId = detail[5];
				path = detail[6];
				if (InterestingPlace.AddIntersistingPlace(PlaceName, PlaceId, description, MapId, CityId,
						path) == true) {
					this.handleMessageFromServerUI("AddPlace");
					break;

				} else {
					this.handleMessageFromServerUI("NotAdd");
					break;
				}
			case "EditPlaceDisc":
				PlaceId = detail[1];
				description = detail[2];
				if (InterestingPlace.EditPlaceDesc(description, PlaceId) == true) {
					this.handleMessageFromServerUI("EditPlaceDisc");
					break;

				} else {
					this.handleMessageFromServerUI("NotEditPlaceDisc");
					break;

				}
			case "EditPlaceName":
				PlaceId = detail[1];
				PlaceName = detail[2];
				if (InterestingPlace.EditPlaceName(PlaceName, PlaceId) == true) {
					this.handleMessageFromServerUI("EditPlaceName");

					break;

				} else {
					this.handleMessageFromServerUI("NotEditPlaceName");

					break;

				}
			case "EditPlaceId":
				PlaceId = detail[1];
				String Placenewid = detail[2];
				if (InterestingPlace.EditPlaceId(PlaceId, Placenewid) == true) {
					this.handleMessageFromServerUI("EditPlaceId");

				} else {
					this.handleMessageFromServerUI("NotEditPlaceId");

					break;

				}
			case "RemovePlace":
				PlaceId = detail[1];
				if (InterestingPlace.RemovePlace(PlaceId) == true) {
					this.handleMessageFromServerUI("Removed Place");
					break;

				} else {
					this.handleMessageFromServerUI("Removing Place Failed!!");
					break;

				}
			case "AddPath":
				MapId=detail[1];
				PathId=detail[2];
				description=detail[3];
				path=detail[4];
				if (Path.AddPath(PathId,MapId,description,path) == true) {
					this.handleMessageFromServerUI("addPath");
					break;

				} else {
					this.handleMessageFromServerUI("NotAddPath");
					break;

				}
			case "EditPath":

				PathId=detail[1];
				path=detail[2];
				if (Path.EditPath(PathId,path) == true) {
					this.handleMessageFromServerUI("AddPath");
					break;

				} else {
					this.handleMessageFromServerUI("NotAddPath");
					break;
				}
				
			case "EditPathdisc":
				PathId=detail[1];
				description=detail[2];
				if (Path.Editdesc(PathId,description) == true) {
					this.handleMessageFromServerUI("editPathdisc");
					break;

				} else {
					this.handleMessageFromServerUI("NotEditPathdisc");
					break;
				}
			case "RemovePath":
				PathId=detail[1];
				if (Path.RemovePath(PathId) == true) {
					this.handleMessageFromServerUI("removing path done");

					break;
				} else {
					this.handleMessageFromServerUI("removing path failed!!");
					break;
				}

//			case "saveMap":
//				path = detail[1];
//				if (Client.savemap(path) == true) {
//					JOptionPane.showMessageDialog(null, "download Finished Successfully");
//					break;
//				}
//				break;

			case "Purchase" :
				CityId= detail[1];
				email=detail[2];
				 String type=detail[3];
				if (Client.Purchase( CityId,email, type) == true) {
					this.handleMessageFromServerUI("Purchased");

					break;
				} else {
					this.handleMessageFromServerUI("CantPurchase");
					break;
				}
			case "ShowPreviousCity" :
		 	res=Client.ShowPreviousCity();
				if (res != null) {
					 show="ShowPreviousCity@"+ res;
					this.handleMessageFromServerUI(show );

					break;
				} else {
					this.handleMessageFromServerUI("CantShowPrevoiousCity");
					break;
				}
			case "ShowNextCity" :
				res=Client.ShowNextCity();
				if (res != null) {
					 show="ShowNextCity@"+  res;
					this.handleMessageFromServerUI(show);

					break;
				} else {
					this.handleMessageFromServerUI("CantShowNextCity");
					break;
				}
			case "show" :
			res=Client.ShowFirstCity();
				if (res != null) {
					 show="show@"+ res;
					this.handleMessageFromServerUI(show );

					break;
				} else {
					this.handleMessageFromServerUI("CantShowCity");
					break;
				}
				
			case"download":
				CityId=detail[1];
				path=detail[2];
		      email=detail[3];
		      int result=Client.download(CityId,path,email);
			if (result==2) {
				 String down="download";
				this.handleMessageFromServerUI(down);

				break;
			}
			else if (result==1) {
				 String down="onetimedownloadFaild";
				this.handleMessageFromServerUI(down);

				break;
			}else {
				this.handleMessageFromServerUI("Cantdownload");
				break;
			}
			case "PersonalInformation":
				email=detail[1];
				res=Client.PersonalInformation(email);
			if(res!=null) {
					String FINAL="PersonalInformation<"+res;
					this.handleMessageFromServerUI(FINAL);
					break;
			}
			break;
			case "showsearch":
	        String s=detail[1];
	        res=Client.search(s);
	    	if(res!=null) {
				this.handleMessageFromServerUI(res);
				break;
		}
		break;
			case "Edit":
			String	InfoKind=detail[1];
			email = detail[2];
        	 String	toEdit=detail[3];
				if (Client.EditPersonalInformation(InfoKind,email,toEdit) == true) {
					this.handleMessageFromServerUI("Editing Done");
					break;
				} else {
					this.handleMessageFromServerUI("Editing Failed!");
					break;
				}
			case "GetName":
				email = detail[1];
				String Result="GetName#"+Client.getname(email);
						this.handleMessageFromServerUI(Result);
						break;
			
		case "showPrice":
			CityId=detail[1];
			int price=City.ShowPrice(CityId);
			if(price!=-1) {
				String msgprice="showPrice@"+Integer.toString(price);
				this.handleMessageFromServerUI(msgprice);
				break;				
			}
			else {
				this.handleMessageFromServerUI("dontShowPrice@");
				break;
			}
			
		case "finishAcceptPrice":
			
			String [] cityIdsToChange=detail[1].split(",");
			if(cityIdsToChange.length!=0) {
				if (City.acceptPriceManager(cityIdsToChange) == true) {
					this.handleMessageFromServerUI("acceptPriceFromManager");
	                break;
				} else {
					this.handleMessageFromServerUI("NotacceptPriceFromManager");

					break;

				}				
			}
            

		case"sendPriceToManager":
			CityId=detail[1];
			int NewPrice=Integer.parseInt(detail[2]);
			if (City.sendPriceToManager(CityId,NewPrice) == true) {
				this.handleMessageFromServerUI("sendPriceToManager");
                break;
			} else {
				this.handleMessageFromServerUI("NotsendPriceToManager");

				break;

			}
			
		case "AcceptPrice":
			String resPrice=CompanyManager.showFirstPrice();
			if(resPrice!=null) {
				String showPrice="show@"+resPrice;
				this.handleMessageFromServerUI(showPrice);
				break;
				
			} else {
				this.handleMessageFromServerUI("Can't Show Price");
				break;
			}

		case "ShowPreviousCityPrice":
			resPrice=CompanyManager.ShowPreviousCity();
				if (resPrice != null) {
				String 	showPrice="ShowPreviousCity@"+ resPrice;
					this.handleMessageFromServerUI(showPrice );

					break;
				} else {
					this.handleMessageFromServerUI("CantShowPrevoiousCity");
					break;
				}
				
		case "ShowNextCityPrice":
			resPrice=CompanyManager.ShowNextCity();
			if (resPrice != null) {
				String showPrice="ShowNextCityPrice@"+  resPrice;
				this.handleMessageFromServerUI(showPrice);

				break;
			} else {
				this.handleMessageFromServerUI("CantShowNextCity");
				break;
			}
			
		case "CDacceptVersion":
			List<String> users_with_new_version = new ArrayList<String>();
			String msgg;
			users_with_new_version=CDManager.acceptVersion();
			if(users_with_new_version.size()!=0) {
			       msgg="CDacceptVersion@";
			      Iterator<String> iterator = users_with_new_version.iterator();
			      while(iterator.hasNext()) {
			    	  msgg=msgg+iterator.next()+",";
			        
			      }
				this.handleMessageFromServerUI(msgg);
                break;
			} else {
				msgg="NotCDacceptVersion";
				this.handleMessageFromServerUI(msgg);

				break;

			}
		case "sub":
			email=detail[1];
			String check=Client.checktime(email);
			
			if (check!=null) {
				String ms="Expired@"+check;
				this.handleMessageFromServerUI(ms);

				break;
			} else {
				this.handleMessageFromServerUI("NotExpired@");
				break;
			}

		}
		}
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI
	 */
	public void handleMessageFromServerUI(String message) {
		if (message.charAt(0) == '#') {
			runCommand(message);
		} else {
			// send message to clients
			serverUI.display(message);
			this.sendToAllClients(message);
		}
	}

	/**
	 * This method executes server commands.
	 *
	 * @param message String from the server console.
	 */
	private void runCommand(String message) {
		// run commands
		// a series of if statements

		if (message.equalsIgnoreCase("#quit")) {
			quit();
		} else if (message.equalsIgnoreCase("#stop")) {
			stopListening();
		} else if (message.equalsIgnoreCase("#close")) {
			try {
				close();
			} catch (IOException e) {
			}
		} else if (message.toLowerCase().startsWith("#setport")) {
			if (getNumberOfClients() == 0 && !isListening()) {
				// If there are no connected clients and we are not
				// listening for new ones, assume server closed.
				// A more exact way to determine this was not obvious and
				// time was limited.
				int newPort = Integer.parseInt(message.substring(9));
				setPort(newPort);
				// error checking should be added
				serverUI.display("Server port changed to " + getPort());
			} else {
				serverUI.display("The server is not closed. Port cannot be changed.");
			}
		} else if (message.equalsIgnoreCase("#start")) {
			if (!isListening()) {
				try {
					listen();
				} catch (Exception ex) {
					serverUI.display("Error - Could not listen for clients!");
				}
			} else {
				serverUI.display("The server is already listening for clients.");
			}
		} else if (message.equalsIgnoreCase("#getport")) {
			serverUI.display("Currently port: " + Integer.toString(getPort()));
		}
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	/**
	 * Run when new clients are connected. Implemented by Benjamin Bergman, Oct 22,
	 * 2009.
	 *
	 * @param client the connection connected to the client
	 */
	protected void clientConnected(ConnectionToClient client) {
		// display on server and clients that the client has connected.
//		String msg = "A Client has connected";
//		System.out.println(msg);
//		this.sendToAllClients(msg);
	}

	/**
	 * Run when clients disconnect. Implemented by Benjamin Bergman, Oct 22, 2009
	 *
	 * @param client the connection with the client
	 */
	synchronized protected void clientDisconnected(ConnectionToClient client) {
		// display on server and clients when a user disconnects
		String msg = client.getInfo("loginID").toString() + " has disconnected";

		System.out.println(msg);
		this.sendToAllClients(msg);
	}

	/**
	 * Run when a client suddenly disconnects. Implemented by Benjamin Bergman, Oct
	 * 22, 2009
	 *
	 * @param client    the client that raised the exception
	 * @param Throwable the exception thrown
	 */
	synchronized protected void clientException(ConnectionToClient client, Throwable exception) {
		String msg = client.getInfo("loginID").toString() + " has disconnected";

		System.out.println(msg);
		this.sendToAllClients(msg);
	}

	/**
	 * This method terminates the server.
	 */
	public void quit() {
		try {
			close();
		} catch (IOException e) {
		}
		System.exit(0);
	}

//Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *        is entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
//End of EchoServer class