package entity;

public class Section {
	private String sectionNo;
	private String semester;
	private String startDate;
	private String endDate;
	private String day;
	private String time;
	private String room;
	private int seats;
	private Instructor instructor;
	
	public Section() {
		// TODO Auto-generated constructor stub
	}

	public Section(String sectionNo, String semester, String startDate, String endDate, String day, String time,
			String room, int seats, Instructor instructor) {
		super();
		this.sectionNo = sectionNo;
		this.semester = semester;
		this.startDate = startDate;
		this.endDate = endDate;
		this.day = day;
		this.time = time;
		this.room = room;
		this.seats = seats;
		this.instructor = instructor;
	}

	public String getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "Section [sectionNo=" + sectionNo + ", semester=" + semester + ", startDate=" + startDate + ", endDate="
				+ endDate + ", day=" + day + ", time=" + time + ", room=" + room + ", seats=" + seats + ", instructor="
				+ instructor + "]";
	}
	
	
}
