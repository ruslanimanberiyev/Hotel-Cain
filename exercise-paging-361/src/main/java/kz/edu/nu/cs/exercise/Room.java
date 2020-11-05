package kz.edu.nu.cs.exercise;

public class Room {
    private Integer floor;
    private Integer number;
    private Integer hotelID;
    private Integer size;
    private Integer capacity;
    private String features;
    private Integer price;

   Room(int floor, int roomNumber, int hotelId, int roomSize, int capacity, String features, int price) {
	   this.floor = floor;
	   this.number = roomNumber;
	   this.hotelID = hotelId;
	   this.size = roomSize;
	   this.capacity = capacity;
	   this.features = features;
	   this.price = price;
   }

}
