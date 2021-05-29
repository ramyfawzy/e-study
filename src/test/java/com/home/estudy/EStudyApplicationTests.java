package com.home.estudy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.home.estudy.controller.TutorialController;
import com.home.estudy.repository.TutorialRepository;

@ActiveProfiles("test")
@SpringBootTest
class EStudyApplicationTests {

	@Autowired
	private TutorialController tutorialController;

	@Test
	void testRestControllerInjected() {
		assertThat(tutorialController).isNotNull();
	}

}
