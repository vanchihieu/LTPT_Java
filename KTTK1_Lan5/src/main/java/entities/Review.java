package entities;

public class Review {
	private int id;
	private String date;
	private int listingId;
	private int reviewerId;
	private String reviewerName;
	private String comments;

	public Review() {
	}

	public Review(int id, String date, int listingId, int reviewerId, String reviewerName, String comments) {
		this.id = id;
		this.date = date;
		this.listingId = listingId;
		this.reviewerId = reviewerId;
		this.reviewerName = reviewerName;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getListingId() {
		return listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public int getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(int reviewerId) {
		this.reviewerId = reviewerId;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", date=" + date + ", listingId=" + listingId + ", reviewerId=" + reviewerId
				+ ", reviewerName=" + reviewerName + ", comments=" + comments + "]";
	}

}
