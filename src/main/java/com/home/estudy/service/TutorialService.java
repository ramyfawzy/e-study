package com.home.estudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.estudy.entity.Tutorial;
import com.home.estudy.repository.TutorialRepository;

@Service
public class TutorialService {

	private TutorialRepository tutorialRepository;

	@Autowired
	public TutorialService(TutorialRepository tutorialRepository) {
		this.tutorialRepository = tutorialRepository;
	}

	public List<Tutorial> findAllTutorials() {
		return tutorialRepository.findAll();
	}

	public Optional<Tutorial> findById(long id) {
		return tutorialRepository.findById(id);
	}

	public List<Tutorial> findByPublished(boolean published) {
		return tutorialRepository.findByPublished(published);
	}

	public List<Tutorial> findByTitleContaining(String title) {
		return tutorialRepository.findByTitleContaining(title);
	}

	public Tutorial save(Tutorial tutorial) {
		return tutorialRepository.save(tutorial);
	}

	public void delete(Tutorial tutorial) {
		tutorialRepository.delete(tutorial);
	}

	public void deleteById(long id) {
		tutorialRepository.deleteById(id);
	}

	public void deleteAll() {
		tutorialRepository.deleteAll();
	}

}
