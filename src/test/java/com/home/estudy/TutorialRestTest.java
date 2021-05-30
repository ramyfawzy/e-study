package com.home.estudy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

//	@Sql(statements = TEST_FIND_BY_ID_SQL)
	@RepeatedTest(value = 1, name = "Custom name {currentRepetition}/{totalRepetitions}")
	@DisplayName("/tutorials/{id} GET tutorial exists")
	void testFindById(RepetitionInfo repetitionInfo) {

		String title = "Mollis Integer Tincidunt Corp.";
		URI targetUrl = UriComponentsBuilder.fromUriString("/api/tutorials").path("/").path("{id}").build(128L);

		Tutorial tutorial = this.restTemplate.getForObject(targetUrl, Tutorial.class);
		assertEquals(1, repetitionInfo.getTotalRepetitions());
		assertEquals(tutorial.getTitle(), title);
	}

	@Test
	@Transactional
	@DisplayName("/tutorials POST create new")
	void testPostCreateNewTutorial() {
		Tutorial tutorial = new Tutorial("Desc", true, "Title");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Tutorial> request = new HttpEntity<Tutorial>(tutorial, headers);
		URI targetUrl = UriComponentsBuilder.fromUriString("/api/tutorials").path("/").build().toUri();
		Tutorial created = this.restTemplate.postForObject(targetUrl, request, Tutorial.class);
		assertEquals(created.getTitle(), tutorial.getTitle());
	}

}
