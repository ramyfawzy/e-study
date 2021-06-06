package com.home.estudy.auth;

import java.io.IOException;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ramy Ibrahim
 *
 */
@Component
public class RequestHandlerInterceptor implements ClientHttpRequestInterceptor {

	public static final Logger LOGGER = LoggerFactory.getLogger(RequestHandlerInterceptor.class);

	private static final String TOKEN_URI = "http://localhost:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token";
	private static final String CLIENT_ID = "client_id";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String GRANT_TYPE = "grant_type";
	private static final String CLIENT_SECRET = "client_secret";
	private static final String AUTHORIZATION = "Authorization";

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		ClientHttpResponse response = execution.execute(request, body);
		if (HttpStatus.UNAUTHORIZED == response.getStatusCode()) {
			request.getHeaders().set(AUTHORIZATION, refreshClientAccessToken());
			// retry
			response = execution.execute(request, body);
		}
		return response;
	}

	public static String refreshClientAccessToken() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(CLIENT_ID, "e-study");
		map.add(USERNAME, "user1");
		map.add(PASSWORD, "user1");
		map.add(GRANT_TYPE, "password");
		map.add(CLIENT_SECRET, "a52d5648-d869-44dd-af44-39e635274ffe");
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map,
				headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ClientAccessToken> response = null;
		try {
			response = restTemplate.postForEntity(TOKEN_URI, requestEntity, ClientAccessToken.class);
			if (response == null || !response.getStatusCode().is2xxSuccessful()) {
				LOGGER.error("Exception while getting access token from Mondia API !!");
			}
		} catch (Exception e) {
			LOGGER.error("Exception while getting access token from Mondia API !!", e);
		}
		TokenManager tokenManager = TokenManager.getInstance();
		tokenManager.clientAccessToken = response.getBody();
		StringJoiner joiner = new StringJoiner(" ");
		joiner.add(tokenManager.clientAccessToken.getTokenType()).add(tokenManager.clientAccessToken.getAccessToken());
		return joiner.toString();
	}
}
