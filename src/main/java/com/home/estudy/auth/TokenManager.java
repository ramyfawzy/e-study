package com.home.estudy.auth;

/**
 * @author Ramy Ibrahim
 *
 */
public class TokenManager {

	private static volatile TokenManager tokenManager;

	public ClientAccessToken clientAccessToken;

	private TokenManager() {
		if (tokenManager != null) {
			throw new RuntimeException("Use getInstance() method to get the single instance of this Singelton class.");
		}
		if (clientAccessToken == null)
			clientAccessToken = new ClientAccessToken();
	}

	public static TokenManager getInstance() {
		if (tokenManager == null) {
			synchronized (TokenManager.class) {
				if (tokenManager == null)
					tokenManager = new TokenManager();
			}
		}

		return tokenManager;
	}

	public ClientAccessToken getClientAccessToken() {
		return clientAccessToken;
	}

	public void setClientAccessToken(ClientAccessToken clientAccessToken) {
		this.clientAccessToken = clientAccessToken;
	}

}
