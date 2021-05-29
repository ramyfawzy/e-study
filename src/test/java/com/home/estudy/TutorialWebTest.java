package com.home.estudy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class TutorialWebTest {
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetById2xxResponse() throws Exception {
		mvc.perform(get("/api/tutorials/1")).andExpect(status().is2xxSuccessful());
	}

}
