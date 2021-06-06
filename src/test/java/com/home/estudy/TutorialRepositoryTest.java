package com.home.estudy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.home.estudy.repository.TutorialRepository;

@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class TutorialRepositoryTest {

	@Autowired
	private TutorialRepository tutorialRepository;

	@BeforeAll
	private void testRepositoryInjected() {
		assertThat(tutorialRepository).isNotNull();
	}

	@Test
	void testfindAll128Tutorials() {
		var tutorials = tutorialRepository.findAll();
		assertThat(tutorials.size()).isEqualTo(128);
	}

}
