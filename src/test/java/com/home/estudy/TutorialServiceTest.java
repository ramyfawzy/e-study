package com.home.estudy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicBoolean;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.home.estudy.service.TutorialService;

@ActiveProfiles("test")
@SpringBootTest
public class TutorialServiceTest {

	@Autowired
	private TutorialService tutorialService;

	@Test
	void testNormalLoadTutorial() {
		var tutorial = tutorialService.findById(1L);
		Assertions.assertThrows(LazyInitializationException.class, () -> {
			assertThat(tutorial.get().getStudents()).isNotEmpty();
		});
	}

	@Test
	void testFetchGraphLoadTutorial() {
		var tutorial = tutorialService.findByIdFetchStudents(1L);
		assertThat(new AtomicBoolean(true));
	}

}
