package com.home.estudy.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ramy Ibrahim
 *
 */
public class ClientAccessToken {

	@JsonProperty(value = "access_token")
	private String accessToken;
	@JsonProperty(value = "expires_in")
	private Long expiresIn;
	@JsonProperty(value = "refresh_expires_in")
	private Long refreshExpiresIn;
	@JsonProperty(value = "refresh_token")
	private String refreshToken;
	@JsonProperty(value = "token_type")
	private String tokenType;
	@JsonProperty(value = "not_before_policy")
	private Long notBeforePolicy;
	@JsonProperty(value = "session_state")
	private String sessionState;
	@JsonProperty(value = "scope")
	private String scope;

	public ClientAccessToken() {
	}

	public ClientAccessToken(String accessToken, Long expiresIn, Long refreshExpiresIn, String refreshToken,
			String tokenType, Long notBeforePolicy, String sessionState, String scope) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.refreshExpiresIn = refreshExpiresIn;
		this.refreshToken = refreshToken;
		this.tokenType = tokenType;
		this.notBeforePolicy = notBeforePolicy;
		this.sessionState = sessionState;
		this.scope = scope;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Long getRefreshExpiresIn() {
		return refreshExpiresIn;
	}

	public void setRefreshExpiresIn(Long refreshExpiresIn) {
		this.refreshExpiresIn = refreshExpiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Long getNotBeforePolicy() {
		return notBeforePolicy;
	}

	public void setNotBeforePolicy(Long notBeforePolicy) {
		this.notBeforePolicy = notBeforePolicy;
	}

	public String getsessionState() {
		return sessionState;
	}

	public void setsessionState(String sessionState) {
		this.sessionState = sessionState;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientAccessToken [accessToken=").append(accessToken).append(", expiresIn=").append(expiresIn)
				.append(", refreshExpiresIn=").append(refreshExpiresIn).append(", refreshToken=").append(refreshToken)
				.append(", tokenType=").append(tokenType).append(", notBeforePolicy=").append(notBeforePolicy)
				.append(", sessionState=").append(sessionState).append(", scope=").append(scope).append("]");
		return builder.toString();
	}

}
