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

public class MakeBooking {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String host = "138.68.108.65";
	final private String user = "ruslanimanberdiyev";
	final private String passwd = "123Ruslan$";
	final private String database = "xxxxxxxxxxxxxxxxxxxxxx";

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

	public void addReservation(Integer roomNumber, Integer hotelID, Integer guestID, String date) throws Exception {
		try {
	    	
	    	String tableName = "reservations";
	    	String query = "INSERT INTO " + tableName + " (hotelID, guestID, roomNumber, checkInDate) \n" + " VALUES (" + hotelID + ", " + guestID + ", " + roomNumber + ", '" + date + "')";
			
	    	statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			
		} catch (Exception e) {
			throw e;
		}
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