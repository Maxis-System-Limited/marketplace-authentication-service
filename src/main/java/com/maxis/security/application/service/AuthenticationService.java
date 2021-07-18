package com.maxis.security.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maxis.security.adapter.out.model.view.AuthenticationResponse;
import com.maxis.security.application.port.in.AuthenticationInterface;
import com.maxis.security.util.AuthenticationJwtUtil;
import com.maxis.user.adapter.out.model.mongo.UserDataModel;
import com.maxis.user.adapter.out.model.mongo.UserResponseModel;
import com.maxis.user.application.port.in.UserInputInterface;

@Service
public class AuthenticationService implements AuthenticationInterface {
	private final UserInputInterface userInterface;
	private final PasswordEncoder passwordEncoder;
	private AuthenticationJwtUtil authenticationTokenUtil;

	@Autowired
	public AuthenticationService(UserInputInterface userInterface, PasswordEncoder passwordEncoder,
			AuthenticationJwtUtil authenticationTokenUtil) {
		this.userInterface = userInterface;
		this.passwordEncoder = passwordEncoder;
		this.authenticationTokenUtil = authenticationTokenUtil;
	}

	@Override
	public boolean isAuthenticated(String userId, String password) {
		UserResponseModel<UserDataModel> user = userInterface.getUserById(userId);

		if (user.response.size() <= 0)
			return false;

		return passwordEncoder.matches(password, user.response.get(0).getPassword());
	}

	@Override
	public AuthenticationResponse createAuthenticationToken(String userId) {
		final String id_token = authenticationTokenUtil.createIdTokenRequest(userId);

		final String refresh_token = authenticationTokenUtil.createRefreshTokenRequestForIdToken(userId);

		AuthenticationResponse response = new AuthenticationResponse(id_token, refresh_token);

		long expires_in = (authenticationTokenUtil.extractExpiration(id_token).getTime()) - System.currentTimeMillis();

		response.setExpires_in(expires_in / 1000);

		return response;
	}

	@Override
	public AuthenticationResponse createAuthenticationTokenByRefreshToken(String refreshToken, String userId,
			UserResponseModel<UserDataModel> user) {

		if (authenticationTokenUtil.validateToken(refreshToken, user)) {
			final String id_token = authenticationTokenUtil.createIdTokenRequest(userId);

			final String refresh_token = authenticationTokenUtil.createRefreshTokenRequestForIdToken(userId);

			AuthenticationResponse response = new AuthenticationResponse(id_token, refresh_token);

			long expires_in = (authenticationTokenUtil.extractExpiration(id_token).getTime())
					- System.currentTimeMillis();

			response.setExpires_in(expires_in / 1000);

			return response;
		}
		return null;
	}

	@Override
	public boolean isAuthenticationTokenValid(String idToken, String userId) {
		UserResponseModel<UserDataModel> user = userInterface.getUserById(userId);

		if (user.response.size() <= 0)
			return false;

		return authenticationTokenUtil.validateToken(idToken, user);
	}

}
