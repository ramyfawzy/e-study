package com.home.estudy.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@NamedEntityGraph(name = "tutorial.student.graph", attributeNodes = @NamedAttributeNode(value = "students", subgraph = "students"), 
	subgraphs = @NamedSubgraph(name = "students", attributeNodes = @NamedAttributeNode("name")))
@Entity
@Table
public class Tutorial implements Serializable {

	private static final long serialVersionUID = -2924200699742004758L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "published")
	private boolean published;

	@Column(name = "title")
	private String title;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "tutorial_student", joinColumns = @JoinColumn(name = "tutorial_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
	private Set<Student> students = new HashSet<>();

	public Tutorial() {

	}

	public Tutorial(String description, boolean published, String title) {
		this.description = description;
		this.published = published;
		this.title = title;
	}

	public void addStudent(Student student) {
		this.students.add(student);
		student.getTutorials().add(this);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
		student.getTutorials().remove(this);
	}

	public void removeStudents() {
		Iterator<Student> iterator = this.students.iterator();

		while (iterator.hasNext()) {
			Student student = iterator.next();

			student.getTutorials().remove(this);
			iterator.remove();
		}
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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tutorial [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", published=").append(published).append("]");
		return builder.toString();
	}

}
