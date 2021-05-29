package com.home.estudy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.util.UriComponentsBuilder;

import com.home.estudy.entity.Tutorial;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TutorialRestTest {

	@Autowired
	private TestRestTemplate restTemplate;

	private static final String TEST_FIND_BY_ID_SQL = "INSERT INTO tutorial (id,description,published,title) VALUES (default,'Andra Pradesh', 1,'Elmo7n');";

	@Test
	@Sql(statements = TEST_FIND_BY_ID_SQL)
	@DisplayName("/tutorials/{id} GET tutorial exists")
	void testFindById() {

		String title = "Elmo7n";
		URI targetUrl = UriComponentsBuilder.fromUriString("/api/tutorials").path("/").path("{id}").build(129L);

		Tutorial tutorial = this.restTemplate.getForObject(targetUrl, Tutorial.class);
		assertEquals(tutorial.getTitle(), title);
	}

}
