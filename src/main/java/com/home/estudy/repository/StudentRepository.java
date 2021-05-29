package com.home.estudy.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.home.estudy.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByName(String name);
}