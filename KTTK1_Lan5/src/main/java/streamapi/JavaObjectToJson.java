package streamapi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import entities.Host;
import entities.Listing;
import entities.Review;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;

public class JavaObjectToJson {
	public static void main(String[] args) {

		Listing listing = createListingObject();
		
		try (FileWriter fileWriter = new FileWriter("data/VanChiHieu_21108211.json")) {
			// Tạo JsonGenerator để tạo tài liệu JSON và ghi vào file
			JsonGenerator jsonGenerator = Json.createGenerator(fileWriter);
//		try (JsonGenerator jsonGenerator = Json.createGenerator(new FileWriter("data/VanChiHieu_21108211.json"))) {
			jsonGenerator.writeStartArray().writeStartObject().write("id", listing.getId())
					.write("listingUrl", listing.getListingUrl()).write("name", listing.getName())
					.write("summary", listing.getSummary()).write("minimumNights", listing.getMinimumNights())
					.write("maximumNights", listing.getMaximumNights()).write("bedrooms", listing.getBedrooms())
					.write("beds", listing.getBeds()).writeStartArray("amenities");
			for (String amenitie : listing.getAmenities()) {
				jsonGenerator.write(amenitie);
			}
			jsonGenerator.writeEnd().write("price", listing.getPrice()).writeStartObject("host")
					.write("hostId", listing.getHost().getHostId()).write("hostUrl", listing.getHost().getHostUrl())
					.write("hostName", listing.getHost().getHostName()).writeStartArray("hostVerifications");
			for (String hostVerification : listing.getHost().getHostVerifications()) {
				jsonGenerator.write(hostVerification);
			}
			jsonGenerator.writeEnd().writeEnd().writeStartArray("reviews");

			for (Review review : listing.getReviews()) {
				jsonGenerator.writeStartObject().write("id", review.getId()).write("date", review.getDate())
						.write("listingId", review.getListingId()).write("reviewerId", review.getReviewerId())
						.write("reviewerName", review.getReviewerName()).write("comments", review.getComments())
						.writeEnd();
			}
			jsonGenerator.writeEnd().writeEnd().writeEnd();

			// Đóng JsonGenerator
			jsonGenerator.close();
			
			System.out.println("Tai lieu json da duoc ghi");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Listing createListingObject() {
		// Tạo đối tượng Listing với dữ liệu tương ứng
		Listing listing = new Listing();
		listing.setId(1);
		listing.setListingUrl("https://www.airbnb.com/rooms/10059872");
		listing.setName("Soho Cozy, Spacious and Convenient");
		listing.setSummary(
				"Clean, fully furnish, Spacious 1 bedroom flat just off the escalator in Mid Levels. 2 minutes From Soho Bar and Restaurants. Located in a quiet alley 1 minute from Sun Yat Sen");
		listing.setMinimumNights(4);
		listing.setMaximumNights(20);
		listing.setBedrooms(1);
		listing.setBeds(2);
		listing.setAmenities(Arrays.asList("Air conditioning", "Kitchen", "Smoking allowed", "Doorman", "Elevator"));
		listing.setPrice(699.00);

		Host host = new Host();
		host.setHostId(51624384);
		host.setHostUrl("https://www.airbnb.com/users/show/51624384");
		host.setHostName("Giovanni");
		host.setHostVerifications(Arrays.asList("email", "phone", "reviews", "jumio", "government_id"));
		listing.setHost(host);

		Review review = new Review();
		review.setId(56904633);
		review.setDate("2015-12-19");
		review.setListingId(10059872);
		review.setReviewerId(5302612);
		review.setReviewerName("Octavio");
		review.setComments("The host canceled this reservation 11 days before arrival. This is an automated posting.");
		listing.setReviews(Collections.singletonList(review));

		return listing;
	}
}
