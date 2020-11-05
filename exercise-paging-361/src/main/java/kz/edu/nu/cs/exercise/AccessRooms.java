package kz.edu.nu.cs.exercise;

// Accesses a database with student and phone information

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccessRooms {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String host = "localhost:3306";
	final private String user = "ruslanimanberdiyev";
	final private String passwd = "123Ruslan$";
	final private String database = "";

	public void connectToDB() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://" + host + "/"
					+ database + "?" + "user=" + user + "&password=" + passwd);

		} catch (Exception e) {
			throw e;
		}
	}

	public List <Room> readRooms(String date, String destination, Integer occupancy) throws Exception {
		List <Room> rooms = new ArrayList<Room>();
		try {
	    	
	    	String tableRoom = "rooms";
	    	String tableReserv = "reservations";
	    	String tableHotel = "hotels";
	    	String query = "SELECT rooms.floor, rooms.number, rooms.hotelID, rooms.size, rooms.capacity, rooms.features, rooms.price from " + tableRoom + " as rooms," + tableReserv + " as reservs, " + tableHotel + "as hotels\n"
	    			+ " WHERE rooms.hotelID == hotels.ID && hotels.location == '" + destination + "' && rooms.size == " + occupancy + " && \n"
	    			+ " reservs.roomNumber == rooms.number && not (reservs.checkInDate >= " + date + " && reservs.checkOutDate <= " + date + ")";
			
	    	statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
	    	while (resultSet.next()) {
				 Integer floor = resultSet.getInt("floor");
			     Integer roomNumber = resultSet.getInt("number");
			     Integer hotelID = resultSet.getInt("hotelID");
			     Integer roomSize = resultSet.getInt("size");
			     Integer capacity = resultSet.getInt("capacity");
			     String features = resultSet.getString("features");
			     Integer price = resultSet.getInt("price");
				rooms.add(new Room(floor, roomNumber, hotelID, roomSize, capacity, features, price));
				/*
				System.out.println(String.format(
						"Id: %d name: %5s  phone: %5s", Id, name, phone));*/
			}
		} catch (Exception e) {
			throw e;
		}
		return rooms;
	}

	// You need to close the resultSet
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}