package entity;

import java.util.Arrays;

public class Post {
	private int id;
	private String title;
	private String description;
	private String content;
	private String[] tags;

	public Post() {
	}

	public Post(int id, String title, String description, String content, String[] tags) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
				+ ", tags=" + Arrays.toString(tags) + "]";
	}

}
