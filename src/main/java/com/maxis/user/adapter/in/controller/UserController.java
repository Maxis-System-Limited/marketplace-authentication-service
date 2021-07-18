package com.maxis.user.adapter.in.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxis.common.GeneralConstants;
import com.maxis.common.ResponseObject;
import com.maxis.user.adapter.in.model.mongo.UserInputModel;
import com.maxis.user.adapter.out.model.mongo.UserDataModel;
import com.maxis.user.adapter.out.model.mongo.UserResponseModel;
import com.maxis.user.application.port.in.UserInputInterface;
import com.maxis.user.domain.UserDomain;

@RestController
@RequestMapping(value = "user", produces = "application/json")
public class UserController {
	private final UserInputInterface userInputInterface;

	@Autowired
	public UserController(UserInputInterface userInputInterface) {
		this.userInputInterface = userInputInterface;
	}

	@PostMapping
	public ResponseObject<UserResponseModel<UserDataModel>> saveUser(@RequestBody UserInputModel userInputData) {
		ResponseObject<UserResponseModel<UserDataModel>> response = new ResponseObject<UserResponseModel<UserDataModel>>();

		if (userInputData.getUserName() == null || userInputData.getPassword() == null) {
			UserResponseModel<UserDataModel> userDataResponse = new UserResponseModel<UserDataModel>();

			userDataResponse.status = GeneralConstants.USER_RESPONSE_MESSAGE_FAILURE_RESULT;
			userDataResponse.response = Arrays.asList(new UserDataModel());

			response.result = userDataResponse;

			return response;
		}

		UserDomain userDomain = new UserDomain(null, userInputData.getUserName(), userInputData.getPassword(),
				userInputData.getName(), userInputData.getTanentId(), userInputData.getPhoneNumber(),
				userInputData.getEmail(), userInputData.getPhoto());

		response.result = userInputInterface.saveUser(userDomain);

		return response;
	}

	@GetMapping
	public ResponseObject<UserResponseModel<UserDataModel>> getAllUser() {
		ResponseObject<UserResponseModel<UserDataModel>> response = new ResponseObject<UserResponseModel<UserDataModel>>();

		response.result = userInputInterface.getAllUser();

		return response;
	}

	@GetMapping("/{id}")
	public ResponseObject<UserResponseModel<UserDataModel>> getUserId(@PathVariable("id") String id) {
		ResponseObject<UserResponseModel<UserDataModel>> response = new ResponseObject<UserResponseModel<UserDataModel>>();

		if (id == null || id.isEmpty()) {
			UserResponseModel<UserDataModel> userDataResponse = new UserResponseModel<UserDataModel>();

			userDataResponse.status = GeneralConstants.USER_RESPONSE_MESSAGE_FAILURE_RESULT;
			userDataResponse.response = Arrays.asList(new UserDataModel());

			response.result = userDataResponse;

			return response;
		}

		response.result = userInputInterface.getUserById(id);

		return response;
	}
}
