package com.home.estudy.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class Student implements Serializable {

	private static final long serialVersionUID = -6530257830467133866L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@JsonBackReference
	@ManyToMany(mappedBy = "students")
	private Set<Tutorial> tutorials = new HashSet<>();

	public Student() {
	}

	public Student(String name, Set<Tutorial> tutorials) {
		this.name = name;
		this.tutorials = tutorials;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Tutorial> getTutorials() {
		return tutorials;
	}

	public void setTutorials(Set<Tutorial> tutorials) {
		this.tutorials = tutorials;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student other = (Student) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id=").append(id).append(", name=").append(name).append(", tutorialsCount=")
				.append(tutorials != null ? tutorials.size() : 0).append("]");
		return builder.toString();
	}

}
