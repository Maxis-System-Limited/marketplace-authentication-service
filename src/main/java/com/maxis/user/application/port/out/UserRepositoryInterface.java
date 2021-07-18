package com.maxis.user.application.port.out;

import com.maxis.user.adapter.out.model.mongo.UserDataModel;
import com.maxis.user.adapter.out.model.mongo.UserResponseModel;
import com.maxis.user.domain.UserDomain;

public interface UserRepositoryInterface {
	UserResponseModel<UserDataModel> saveUser(UserDomain userDomain);

	UserResponseModel<UserDataModel> getAllUser();

	UserResponseModel<UserDataModel> getUserById(String id);
}
