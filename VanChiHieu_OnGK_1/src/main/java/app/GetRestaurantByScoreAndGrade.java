package app;

import java.util.List;

import org.bson.Document;

import dao.RestaurantDao;
import entities.Restaurant;

public class GetRestaurantByScoreAndGrade {
	public static void main(String[] args) {
		RestaurantDao dao = new RestaurantDao();
		
		// restaurant
//		dao.getRestaurantByScoreAndGrade2().forEach(System.out::println);
		
		// document
		List<Document> result = dao.getRestaurantByScoreAndGrade();
		result.forEach(System.out::println);
	}
}
