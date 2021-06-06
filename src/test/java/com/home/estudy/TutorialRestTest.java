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
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.home.estudy.auth.RequestHandlerInterceptor;
import com.home.estudy.entity.Tutorial;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TutorialRestTest {

	@Autowired
	private TestRestTemplate restTemplate;

	private static final String TEST_FIND_BY_ID_SQL = "INSERT INTO tutorial (id,description,published,title) VALUES (default,'Andra Pradesh', 1,'Elmo7n');";

//	@Sql(statements = TEST_FIND_BY_ID_SQL)
	@Test
	@DisplayName("/tutorials/{id} GET tutorial exists")
	void testFindById() {

		String title = "Mollis Integer Tincidunt Corp.";
		URI targetUrl = UriComponentsBuilder.fromUriString("/api/tutorials").path("/").path("{id}").build(128L);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", RequestHandlerInterceptor.refreshClientAccessToken());
		HttpEntity<Tutorial> request = new HttpEntity<Tutorial>(null, headers);
//		Tutorial tutorial = this.restTemplate.getForObject(targetUrl, Tutorial.class);
		ResponseEntity<Tutorial> tutorial = this.restTemplate.exchange(targetUrl, HttpMethod.GET ,request, Tutorial.class);
		assertEquals(tutorial.getBody().getTitle(), title);
	}

	@Test
	@Transactional
	@DisplayName("/tutorials POST create new")
	void testPostCreateNewTutorial() {
		Tutorial tutorial = new Tutorial("Desc", true, "Title");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", RequestHandlerInterceptor.refreshClientAccessToken());
		HttpEntity<Tutorial> request = new HttpEntity<Tutorial>(tutorial, headers);
		URI targetUrl = UriComponentsBuilder.fromUriString("/api/tutorials").path("/").build().toUri();
		Tutorial created = this.restTemplate.postForObject(targetUrl, request, Tutorial.class);
		assertEquals(created.getTitle(), tutorial.getTitle());
	}

	@Test
	@DisplayName("/tutorials POST get token")
	void testPostGetToken() {
		RequestHandlerInterceptor.refreshClientAccessToken();
	}
}
