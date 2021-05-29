package com.home.estudy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.estudy.entity.Student;
import com.home.estudy.entity.Tutorial;
import com.home.estudy.repository.TutorialRepository;

@Service
public class TutorialService {

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
		return tutorialRepository.findAll();
	}

	public Optional<Tutorial> findById(long id) {
		return tutorialRepository.findById(id);
	}

	@Transactional
	public Optional<Tutorial> findByIdFetchStudents(long id) {
		/* Inner Join */
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Tutorial> cq = cb.createQuery(Tutorial.class);
//		Root<Tutorial> root = cq.from(Tutorial.class);
//		Join<Tutorial, Student> student = root.join(Tutorial_.STUDENTS);
//		ParameterExpression<Long> tutorialId = cb.parameter(Long.class);
//		cq.where(cb.equal(student.get(Student_.ID), tutorialId));
//		TypedQuery<Tutorial> q = entityManager.createQuery(cq);
//		q.setParameter(tutorialId, id);
//		System.err.println(q.toString());
//		List<Tutorial> tutorials = q.getResultList();
		
		/* Left Outer Join */
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Tuple> cq = cb.createTupleQuery();
//		Root<Tutorial> root = cq.from(Tutorial.class);
//		Join<Object, Object> student = root.join(Tutorial_.STUDENTS, JoinType.LEFT);
//		cq.multiselect(root, student);
//		ParameterExpression<Long> tutorialId = cb.parameter(Long.class);
//		cq.where(cb.equal(student.get(Student_.ID), tutorialId));
//		TypedQuery<Tuple> q = entityManager.createQuery(cq);
//		q.setParameter(tutorialId, id);
//		System.err.println(q.toString());
//		List<Tuple> tutorials = q.getResultList();
		
		
		/* EntityGraph Fetch */
//		RootGraph<Tutorial> graph = GraphParser.parse(Tutorial.class, "students", entityManager);
//		EntityGraph<Tutorial> entityGraph = entityManager.createEntityGraph(Tutorial.class);
//		entityGraph.addAttributeNodes("description");
//		entityGraph.addAttributeNodes("title");
//		entityGraph.addAttributeNodes("students");
//		entityGraph.addSubgraph("students")
//		  .addAttributeNodes("name");
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Tutorial> cq = cb.createQuery(Tutorial.class);
//		Root<Tutorial> root = cq.from(Tutorial.class);
//		Join<Object, Object> student = root.join(Tutorial_.STUDENTS, JoinType.LEFT);
//		 
//		ParameterExpression<Long> tutorialId = cb.parameter(Long.class);
//		cq.where(cb.equal(student.get(Tutorial_.ID), tutorialId));
//		 
//		TypedQuery<Tutorial> q = entityManager.createQuery(cq);
//		q.setParameter(tutorialId, id);
//		List<Tutorial> tutorials = q.getResultList();
		
		
		EntityGraph<?> graph = entityManager.getEntityGraph("tutorials-students-graph");
	      Map<String, Object> properties = new HashMap<>();
//	      properties.put("javax.persistence.fetchgraph", graph);
	      properties.put("javax.persistence.loadgraph", graph);
	      Tutorial tutorial = entityManager.find(Tutorial.class, 1L, properties);
		System.err.println("id : "+entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(tutorial, "id"));
		System.err.println("description : "+entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(tutorial, "description"));
		System.err.println("published : "+entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(tutorial, "published"));
		System.err.println("title : "+entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(tutorial, "title"));
		System.err.println("students : "+entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(tutorial, "students"));
		var tutorials = new ArrayList<Tutorial>();
		return tutorials == null || tutorials.isEmpty() ? Optional.empty() : Optional.of(tutorials.get(0));
	}

	public List<Tutorial> findByPublished(boolean published) {
		return tutorialRepository.findByPublished(published);
	}

	public List<Tutorial> findByTitleContaining(String title) {
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
