package com.maxis.security.adapter.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxis.common.GeneralConstants;
import com.maxis.common.ResponseObject;
import com.maxis.security.adapter.in.model.AuthenticationRequest;
import com.maxis.security.adapter.out.model.view.AuthenticationResponse;
import com.maxis.security.adapter.out.model.view.AuthenticationResult;
import com.maxis.security.application.port.in.AuthenticationInterface;
import com.maxis.user.adapter.out.model.mongo.UserDataModel;
import com.maxis.user.adapter.out.model.mongo.UserResponseModel;
import com.maxis.user.application.port.in.UserInputInterface;

@RestController
@RequestMapping("/oauth")
public class AuthenticationController {

	@Autowired
	private AuthenticationInterface authenticationManager;
	@Autowired
	private UserInputInterface userService;

	@PostMapping(value = "/login")
	public ResponseObject<AuthenticationResult> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		ResponseObject<AuthenticationResult> authenticationResult = new ResponseObject<AuthenticationResult>();

		AuthenticationResult response = new AuthenticationResult();

		try {

			if (authenticationManager.isAuthenticated(authenticationRequest.getUsername(),
					authenticationRequest.getPassword())) {

				AuthenticationResponse authRes = authenticationManager
						.createAuthenticationToken(authenticationRequest.getUsername());

				UserResponseModel<UserDataModel> userData = userService
						.getUserById(authenticationRequest.getUsername());

				response.setAuthResponse(authRes);
				response.setCode(GeneralConstants.LOGIN_RESPONSE_MESSAGE_SUCCESS_CODE);
				response.setResult(GeneralConstants.LOGIN_RESPONSE_MESSAGE_SUCCESS_RESULT);
				response.setMessage(GeneralConstants.LOGIN_RESPONSE_MESSAGE_SUCCESS_MESSAGE);
				response.setUserName(userData.response.get(0).getName());
				response.setUserId(userData.response.get(0).getUserId());
				response.setTanentId(userData.response.get(0).getTanentId());
				response.setUserPhone(userData.response.get(0).getPhoneNumber());
				response.setUserImage(userData.response.get(0).getPhoto());

				authenticationResult.result = response;

			} else {
				response.setAuthResponse(new AuthenticationResponse("", ""));
				response.setCode(GeneralConstants.LOGIN_RESPONSE_MESSAGE_FAILURE_CODE);
				response.setResult(GeneralConstants.LOGIN_RESPONSE_MESSAGE_FAILURE_RESULT);
				response.setMessage(GeneralConstants.LOGIN_RESPONSE_MESSAGE_FAILURE_MESSAGE);

				authenticationResult.result = response;

			}

		} catch (Exception e) {
			response.setAuthResponse(new AuthenticationResponse("", ""));
			response.setCode(GeneralConstants.LOGIN_RESPONSE_MESSAGE_FAILURE_CODE);
			response.setResult(GeneralConstants.LOGIN_RESPONSE_MESSAGE_FAILURE_RESULT);
			response.setMessage(GeneralConstants.LOGIN_RESPONSE_MESSAGE_FAILURE_MESSAGE);

			authenticationResult.result = response;
		}

		return authenticationResult;

	}

	@GetMapping("/check_token")
	public ResponseObject<Boolean> checkTokenValidity(String idToken, String userId) {
		ResponseObject<Boolean> response = new ResponseObject<Boolean>();
		boolean result = authenticationManager.isAuthenticationTokenValid(idToken, userId);
		response.result = result;
		return response;
	}

	@GetMapping(value = "/idToken-by-refreshToken")
	public ResponseObject<AuthenticationResult> createAuthenticationTokenByRefreshToken(String refreshToken,
			String userId) throws Exception {

		ResponseObject<AuthenticationResult> authenticationResult = new ResponseObject<AuthenticationResult>();

		AuthenticationResult response = new AuthenticationResult();

		try {

			UserResponseModel<UserDataModel> userData = userService.getUserById(userId);
			if (userData.response.size() >= 0) {

				AuthenticationResponse authRes = authenticationManager
						.createAuthenticationTokenByRefreshToken(refreshToken, userId, userData);

				response.setAuthResponse(authRes);
				response.setCode(GeneralConstants.NEWTOKEN_RESPONSE_MESSAGE_SUCCESS_CODE);
				response.setResult(GeneralConstants.NEWTOKEN_RESPONSE_MESSAGE_SUCCESS_RESULT);
				response.setMessage(GeneralConstants.NEWTOKEN_RESPONSE_MESSAGE_SUCCESS_MESSAGE);
				response.setUserName(userData.response.get(0).getName());
				response.setUserId(userData.response.get(0).getUserId());
				response.setTanentId(userData.response.get(0).getTanentId());
				response.setUserPhone(userData.response.get(0).getPhoneNumber());
				response.setUserImage(userData.response.get(0).getPhoto());

				authenticationResult.result = response;

			} else {
				response.setAuthResponse(new AuthenticationResponse("", ""));
				response.setCode(GeneralConstants.NEWTOKEN_RESPONSE_MESSAGE_FAILURE_CODE);
				response.setResult(GeneralConstants.NEWTOKEN_RESPONSE_MESSAGE_FAILURE_RESULT);
				response.setMessage(GeneralConstants.NEWTOKEN_RESPONSE_MESSAGE_FAILURE_MESSAGE);

				authenticationResult.result = response;

			}

		} catch (Exception e) {
			response.setAuthResponse(new AuthenticationResponse("", ""));
			response.setCode(GeneralConstants.NEWTOKEN_RESPONSE_MESSAGE_FAILURE_CODE);
			response.setResult(GeneralConstants.NEWTOKEN_RESPONSE_MESSAGE_FAILURE_RESULT);
			response.setMessage(GeneralConstants.NEWTOKEN_RESPONSE_MESSAGE_FAILURE_MESSAGE);

			authenticationResult.result = response;
		}

		return authenticationResult;

	}

}
