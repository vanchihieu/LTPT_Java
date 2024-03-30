package app;

import java.util.List;

import dao.HotelDao;
import entity.Room;

public class AddRoomTask {
	public static void main(String[] args) {
		HotelDao hotelDao = new HotelDao();
		Room room = new Room("Phong ngu thuong gia", "VIP","Good",4, false,List.of("tivi","ao"));
		boolean addRoom = hotelDao.addRoom("house2", room);
		System.out.println(addRoom);
	}
}
