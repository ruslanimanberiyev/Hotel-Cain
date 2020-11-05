package kz.edu.nu.cs.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/booking")
public class BookingService {

    public BookingService() {
    }

    @GET
    public Response makeBooking(@QueryParam("roomNumber") Integer roomNumber, @QueryParam("hotelID") Integer hotelID, @QueryParam("guestID") Integer guestID, @QueryParam("date") String date) {
    	Gson gson = new Gson();
        String json;
        /*
		try {
			makeQuery(roomNumber, hotelID, guestID, date);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
        json = gson.toJson("success", String.class);
        return Response.ok(json).build();
    }
    
    private void makeQuery(Integer roomNumber, Integer hotelID, Integer guestID, String date) throws Exception {
    	
    	MakeBooking dao = new MakeBooking();
        dao.addReservation(roomNumber, hotelID, guestID, date);
    }
}
