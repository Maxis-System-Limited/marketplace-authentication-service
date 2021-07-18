package com.maxis.user.application.port.in;

import com.maxis.user.adapter.out.model.mongo.UserDataModel;
import com.maxis.user.adapter.out.model.mongo.UserResponseModel;
import com.maxis.user.domain.UserDomain;

public interface UserInputInterface {
	UserResponseModel<UserDataModel> saveUser(UserDomain UserDomain);

	UserResponseModel<UserDataModel> getAllUser();

	UserResponseModel<UserDataModel> getUserById(String id);
}
