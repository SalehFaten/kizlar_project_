package application;
//This file contains material supporting section 3.7 of the textbook:

//"Object Oriented Software Engineering" and is issued under the open-source
//license found at www.lloseng.com 

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.util.*;
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
			String[] detail = ((String) msg).split(",");
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
					JOptionPane.showMessageDialog(null, "You SignUp successfully ");
					break;

				} else {
					JOptionPane.showMessageDialog(null, "Your SignUp failed !!");
					break;

				}

			case "SignIn":
				email = detail[1];
				pass = detail[2];
				if (email.contains("@map.co.il") || email.contains("@mapcd.co.il") || email.contains("@mapcdm.co.il")) {
					if (CDEmployee.SignIn(email, pass) == true) {
						this.handleMessageFromServerUI("SignIn");
						break;

					}
//					else
//					{
//						JOptionPane.showMessageDialog(null, "You are connected from another device !!");
//						break;
//
//					}
				} else {
					if (Client.SignIn(email, pass) == true) {
						this.handleMessageFromServerUI("SignIn");
						break;
					}
//				else
//				{
//					JOptionPane.showMessageDialog(null, "You are connected from another device !!");
//					break;
//
//				}
				}
			case "userlogout":
				email = detail[1];
				if (Client.Signout(email) == true) {
					JOptionPane.showMessageDialog(null, "You are logout ");
					break;

				}
				break;
			case "Employeelogout":
				email = detail[1];
				if (CDEmployee.Signout(email) == true) {
					JOptionPane.showMessageDialog(null, "You are logout ");
					break;

				}

				break;
			case "PublicSearch":
				String search = detail[1];
				String res = Catalog.search(search);
				if (res != null) {
					this.handleMessageFromServerUI(res);
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
					JOptionPane.showMessageDialog(null, "City Created Successfully!! ");
					break;

				} else {
					JOptionPane.showMessageDialog(null, "City can't created!! ");
					break;

				}
			case "AddMap":
				CityId = detail[1];
				MapId = detail[2];
				description = detail[3];
				path = detail[4];
				if (Map.AddMapToCity(CityId, MapId, description, path) == true) {
					JOptionPane.showMessageDialog(null, "Adding Map Finished Successfully");
					break;
				} else {
					JOptionPane.showMessageDialog(null, "cann't add map to city!! ");
					break;

				}
			case "EmployeeSeeMapId":
				MapId = detail[1];
				String mypath = "MapPath@" + Map.showmap(MapId);
				this.handleMessageFromServerUI(mypath);
				break;
			case "AddPlace":
				PlaceName = detail[1];
				PlaceId = detail[2];
				description = detail[3];
				MapId = detail[4];
				CityId = detail[5];
				path = detail[6];
				if (InterestingPlace.AddIntersistingPlace(PlaceName, PlaceId, description, MapId, CityId,
						path) == true) {
					JOptionPane.showMessageDialog(null, "Adding InterstingPlace Finished Successfully");
					break;

				} else {
					JOptionPane.showMessageDialog(null, "cann't add place to map!! ");
					break;

				}
			case "EditPlaceDisc":
				PlaceId = detail[1];
				description = detail[2];
				if (InterestingPlace.EditPlaceDesc(description, PlaceId) == true) {
			JOptionPane.showMessageDialog(null, "Edit Place description Finished Successfully");
			break;

				} else {
					JOptionPane.showMessageDialog(null, "cann't edit !! ");
					break;

				}
			case "EditPlaceName":
				PlaceId = detail[1];
				PlaceName= detail[2];
				if (InterestingPlace.EditPlaceName(PlaceName, PlaceId) == true) {
					JOptionPane.showMessageDialog(null, "Edit Place name Finished Successfully");
					break;

				} else {
					JOptionPane.showMessageDialog(null, "cann't edit !! ");
					break;

				}
			case "EditPlaceId":
				PlaceId = detail[1];
			String	Placenewid= detail[2];
				if (InterestingPlace.EditPlaceName(PlaceId,Placenewid) == true) {
					JOptionPane.showMessageDialog(null, "Edit Place id Finished Successfully");
				} else {
					JOptionPane.showMessageDialog(null, "cann't edit !! ");
					break;

				}
			case "RemovePlace":
				PlaceId = detail[1];
					if (InterestingPlace.RemovePlace(PlaceId) == true) {
						JOptionPane.showMessageDialog(null, "Remove Place Finished Successfully");
						break;

					} else {
						JOptionPane.showMessageDialog(null, "cann't remove !! ");
						break;

					}
			case "AddPath":
				break;
			case "EditPath":
				break;
			case "EditPathdisc":
				break;

			}
//			System.out.println("Message received: " + msg + " from \"" + client.getInfo("loginID") + "\" " + client);
//   this.sendToAllClients(client.getInfo("loginID") + "> " + msg);
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