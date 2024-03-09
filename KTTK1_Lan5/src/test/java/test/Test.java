package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;

import entities.Host;
import entities.Listing;
import entities.Review;

public class Test {
	@org.junit.Test
	public void testFirstListingHostName() {
		List<Listing> listingList = new ArrayList<>();
		
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
		
		listingList.add(listing);
		
		// listing 2
		Listing listing1 = new Listing();
		listing1.setId(1);
		listing1.setListingUrl("https://www.airbnb.com/rooms/10059872");
		listing1.setName("Soho Cozy, Spacious and Convenient");
		listing1.setSummary(
				"Clean, fully furnish, Spacious 1 bedroom flat just off the escalator in Mid Levels. 2 minutes From Soho Bar and Restaurants. Located in a quiet alley 1 minute from Sun Yat Sen");
		listing1.setMinimumNights(4);
		listing1.setMaximumNights(20);
		listing1.setBedrooms(1);
		listing1.setBeds(2);
		listing1.setAmenities(Arrays.asList("Air conditioning", "Kitchen", "Smoking allowed", "Doorman", "Elevator"));
		listing1.setPrice(699.00);

		Host host1 = new Host();
		host1.setHostId(51624384);
		host1.setHostUrl("https://www.airbnb.com/users/show/51624384");
		host1.setHostName("Hieu");
		host1.setHostVerifications(Arrays.asList("email", "phone", "reviews", "jumio", "government_id"));
		listing1.setHost(host1);

		Review review1 = new Review();
		review1.setId(56904633);
		review1.setDate("2015-12-19");
		review1.setListingId(10059872);
		review1.setReviewerId(5302612);
		review1.setReviewerName("Octavio");
		review1.setComments("The host canceled this reservation 11 days before arrival. This is an automated posting.");
		listing1.setReviews(Collections.singletonList(review1));
		
		listingList.add(listing1);
		// test
		String expectedHostName = "Giovanni";
		String actualHostName = listingList.get(0).getHost().getHostName();
		Assert.assertEquals(expectedHostName, actualHostName);
	}

//	private Listing createFirstListing() {
//		// Tạo đối tượng Listing với dữ liệu tương ứng
//		Listing listing = new Listing();
//		listing.setId(1);
//		listing.setListingUrl("https://www.airbnb.com/rooms/10059872");
//		listing.setName("Soho Cozy, Spacious and Convenient");
//		listing.setSummary(
//				"Clean, fully furnish, Spacious 1 bedroom flat just off the escalator in Mid Levels. 2 minutes From Soho Bar and Restaurants. Located in a quiet alley 1 minute from Sun Yat Sen");
//		listing.setMinimumNights(4);
//		listing.setMaximumNights(20);
//		listing.setBedrooms(1);
//		listing.setBeds(2);
//		listing.setAmenities(Arrays.asList("Air conditioning", "Kitchen", "Smoking allowed", "Doorman", "Elevator"));
//		listing.setPrice(699.00);
//
//		Host host = new Host();
//		host.setHostId(51624384);
//		host.setHostUrl("https://www.airbnb.com/users/show/51624384");
//		host.setHostName("Giovanni");
//		host.setHostVerifications(Arrays.asList("email", "phone", "reviews", "jumio", "government_id"));
//		listing.setHost(host);
//
//		Review review = new Review();
//		review.setId(56904633);
//		review.setDate("2015-12-19");
//		review.setListingId(10059872);
//		review.setReviewerId(5302612);
//		review.setReviewerName("Octavio");
//		review.setComments("The host canceled this reservation 11 days before arrival. This is an automated posting.");
//		listing.setReviews(Collections.singletonList(review));
//
//		return listing;
//	}

}
