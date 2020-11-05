package kz.edu.nu.cs.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/search")
public class SearchService {

    public SearchService() {
    }
    
    private List<Room> makeQuery(String date, String destination, Integer occupancy) throws Exception {
		AccessRooms dao = new AccessRooms();
        return dao.readRooms(date, destination, occupancy);
    }
    
    @GET
    public Response searchRooms(@QueryParam("date") String date, @QueryParam("destination") String destination, @QueryParam("occupancy") Integer occupancy) {
    	Gson gson = new Gson();
        String json;
        
        List<Room> rooms = new ArrayList<Room>();
		try {
			rooms = makeQuery(date, destination, occupancy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		json = gson.toJson(rooms);
       
        return Response.ok(json).build();
    }
    
    
}
