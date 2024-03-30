package app;

import java.util.List;

import dao.HotelDao;
import entity.Address;
import entity.Hotel;
import entity.Room;

public class AddHotelTask {

	public static void main(String[] args) {
		HotelDao hotelDao = new HotelDao();
		Hotel hotel = new Hotel("house3","Nguyen Quoc Bao","Khach san va karaoke",23,
				new Address("12 Nguyen Van Bao","HCM","HCM", "678", "Viet Nam"),
				List.of(
						new Room("Phong ngu cao cap", "VIP","Good",4, false,List.of("tivi","ao")),
						new Room("Phong ngu binh dan", "NORMAL","OKE",3, true,List.of("tivi","ao"))
						));
		boolean addHotel = hotelDao.addHotel(hotel);
		System.out.println(addHotel);
	}

}
