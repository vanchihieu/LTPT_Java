package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Treatment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1731000363014848045L;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	
	private String description;
	
	public Treatment() {
	}

	public Treatment(Doctor doctor, Date startDate, Date endDate, String description) {
		super();
		this.doctor = doctor;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Treatment [doctor=" + doctor.getId() + ", startDate=" + startDate + ", endDate=" + endDate + ", description="
				+ description + "]";
	}
	
}