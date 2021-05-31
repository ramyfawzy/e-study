package com.home.estudy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.home.estudy.entity.Student;
import com.home.estudy.entity.Tutorial;
import com.home.estudy.repository.TutorialRepository;

@Service
public class TutorialService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TutorialService.class);
	
	private TutorialRepository tutorialRepository;
	private StudentService studentService;

	@Autowired
	EntityManager entityManager;

	@Autowired
	public TutorialService(TutorialRepository tutorialRepository, StudentService studentService) {
		this.tutorialRepository = tutorialRepository;
		this.studentService = studentService;
	}

	public List<Tutorial> findAllTutorials() {
		LOGGER.debug("Finding all tutorials ...");
		return tutorialRepository.findAll();
	}

	public Optional<Tutorial> findById(long id) {
		return tutorialRepository.findById(id);
	}

	@Transactional
	public Optional<Tutorial> findByIdFetchStudents(long id) {
		/* EntityGraph Fetch */
		EntityGraph<?> graph = entityManager.getEntityGraph("tutorial.student.graph");
		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", graph);
//	      hints.put("javax.persistence.fetchgraph", graph);
		Tutorial tutorial = entityManager.find(Tutorial.class, id, hints);

		return tutorial == null || tutorial.getId() == null ? Optional.empty() : Optional.of(tutorial);
	}

	public List<Tutorial> findByPublished(boolean published) {
		return tutorialRepository.findByPublished(published);
	}

	public List<Tutorial> findByPublishedPageable(boolean published, int fromPage, int pageSize) {
		Pageable pageable = PageRequest.of(fromPage, pageSize);
		return tutorialRepository.findByPublished(published, pageable);
	}

	public List<Tutorial> findByTitleContaining(String title) {
		LOGGER.debug("Finding tutorials by title {}", title);
		return tutorialRepository.findByTitleContaining(title);
	}

	@Transactional
	public Tutorial save(Tutorial tutorial) {
		return tutorialRepository.save(tutorial);
	}

	@Transactional
	public void delete(Tutorial tutorial) {
		tutorialRepository.delete(tutorial);
	}

	@Transactional
	public void deleteById(long id) {
		tutorialRepository.deleteById(id);
	}

	@Transactional
	public void deleteAll() {
		tutorialRepository.deleteAllInBatch();
	}

	@Transactional
	public Optional<Tutorial> addStudentToTutorial(Long tutorialId, Long studentId) {
		Optional<Tutorial> tutorial = findById(tutorialId);
		if (tutorial.isEmpty()) {
			return Optional.empty();
		}
		Optional<Student> student = studentService.findById(studentId);
		if (student.isEmpty()) {
			return Optional.empty();
		}
		tutorial.get().addStudent(student.get());
//		tutorialRepository.flush();
		return tutorial;
	}

}
