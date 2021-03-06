package com.home.estudy.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.home.estudy.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

	List<Tutorial> findByPublished(boolean published);
	
	List<Tutorial> findByPublished(boolean published, Pageable pageable);

	List<Tutorial> findByTitleContaining(String title);
}