package entity;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Room {
	private String description;
	private String type;
	@BsonProperty("bed_options")
	private String bedOptions;
	@BsonProperty("sleeps_count")
	private int sleepsCount;
	@BsonProperty("smoke_allowed")
	private boolean smokeAllowed;
	private List<String> tags;
	public Room() {
		// TODO Auto-generated constructor stub
	}
	public Room(String description, String type, String bedOptions, int sleepsCount, boolean smokeAllowed,
			List<String> tags) {
		super();
		this.description = description;
		this.type = type;
		this.bedOptions = bedOptions;
		this.sleepsCount = sleepsCount;
		this.smokeAllowed = smokeAllowed;
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBedOptions() {
		return bedOptions;
	}
	public void setBedOptions(String bedOptions) {
		this.bedOptions = bedOptions;
	}
	public int getSleepsCount() {
		return sleepsCount;
	}
	public void setSleepsCount(int sleepsCount) {
		this.sleepsCount = sleepsCount;
	}
	public boolean isSmokeAllowed() {
		return smokeAllowed;
	}
	public void setSmokeAllowed(boolean smokeAllowed) {
		this.smokeAllowed = smokeAllowed;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Room [description=" + description + ", type=" + type + ", bedOptions=" + bedOptions + ", sleepsCount="
				+ sleepsCount + ", smokeAllowed=" + smokeAllowed + ", tags=" + tags + "]";
	}
	
}
