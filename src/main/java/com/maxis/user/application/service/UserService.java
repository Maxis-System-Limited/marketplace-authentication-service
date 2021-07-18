package com.maxis.user.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxis.user.adapter.out.model.mongo.UserDataModel;
import com.maxis.user.adapter.out.model.mongo.UserResponseModel;
import com.maxis.user.application.port.in.UserInputInterface;
import com.maxis.user.application.port.out.UserRepositoryInterface;
import com.maxis.user.domain.UserDomain;

@Service
public class UserService implements UserInputInterface {
	private final UserRepositoryInterface userRepository;

	@Autowired
	public UserService(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserResponseModel<UserDataModel> saveUser(UserDomain userDomain) {

		UserResponseModel<UserDataModel> userData = userRepository.getUserById(userDomain.getUserId());
		if (userData.response.get(0) == null) {
			return userRepository.saveUser(userDomain);
		} else {
			userDomain.setId(userData.response.get(0).getId());
			return userRepository.saveUser(userDomain);
		}
	}

	@Override
	public UserResponseModel<UserDataModel> getAllUser() {
		return userRepository.getAllUser();
	}

	@Override
	public UserResponseModel<UserDataModel> getUserById(String id) {
		return userRepository.getUserById(id);
	}

}
