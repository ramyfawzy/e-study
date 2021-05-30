package com.home.estudy;

import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
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
		assertThat(tutorial.get().getStudents()).isNotEmpty();
	}

	@RepeatedTest(value = 3, name = "Tutorials List Page {currentRepetition}/{totalRepetitions}")
	@DisplayName("Testing published/unpublished Tutorials list paging")
	void testFindTutorialsByPublishedPageable(RepetitionInfo repetitionInfo) {
		var tutorials = tutorialService.findByPublishedPageable(false, repetitionInfo.getCurrentRepetition() - 1, 30);
		switch (repetitionInfo.getCurrentRepetition() - 1) {
		case 0: {
			assertThat(tutorials.size()).isEqualTo(30);
			break;
		}
		case 1: {
			assertThat(tutorials.size()).isEqualTo(30);
			break;
		}
		case 2: {
			assertThat(tutorials.size()).isEqualTo(4);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + repetitionInfo.getCurrentRepetition());
		}
	}

}
