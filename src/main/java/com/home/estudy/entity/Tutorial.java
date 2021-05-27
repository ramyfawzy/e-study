package com.home.estudy.entity;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "description")
	private String description;

	@Column(name = "published")
	private boolean published;

	@Column(name = "title")
	private String title;

	public Tutorial() {

	}

	public Tutorial(String description, boolean published, String title) {
		this.description = description;
		this.published = published;
		this.title = title;
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

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Tutorial)) {
			return false;
		}
		Tutorial other = (Tutorial) obj;
		return id == other.id && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tutorial [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", published=").append(published).append("]");
		return builder.toString();
	}

}
