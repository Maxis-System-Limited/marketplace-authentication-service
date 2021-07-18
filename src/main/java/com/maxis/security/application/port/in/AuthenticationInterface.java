package com.maxis.security.application.port.in;

import com.maxis.security.adapter.out.model.view.AuthenticationResponse;
import com.maxis.user.adapter.out.model.mongo.UserDataModel;
import com.maxis.user.adapter.out.model.mongo.UserResponseModel;

public interface AuthenticationInterface {
	boolean isAuthenticated(String userId, String password);

	AuthenticationResponse createAuthenticationToken(String userId);

	AuthenticationResponse createAuthenticationTokenByRefreshToken(String refreshToken, String userId, UserResponseModel<UserDataModel> user);

	boolean isAuthenticationTokenValid(String idToken, String userId);
}
