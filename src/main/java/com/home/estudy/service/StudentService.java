package com.home.estudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.estudy.entity.Student;
import com.home.estudy.repository.StudentRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	public Optional<Student> findById(long id) {
		return studentRepository.findById(id);
	}

	public List<Student> findByName(String name) {
		return studentRepository.findByName(name);
	}

	public Student save(Student tutorial) {
		return studentRepository.save(tutorial);
	}

	public void delete(Student tutorial) {
		studentRepository.delete(tutorial);
	}

	public void deleteById(long id) {
		studentRepository.deleteById(id);
	}

	public void deleteAll() {
		studentRepository.deleteAllInBatch();
	}

}
