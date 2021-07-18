package com.maxis.user.adapter.out.mongo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.maxis.common.GeneralConstants;
import com.maxis.user.adapter.out.model.mongo.UserDataModel;
import com.maxis.user.adapter.out.model.mongo.UserResponseModel;
import com.maxis.user.application.port.out.UserRepositoryInterface;
import com.maxis.user.domain.UserDomain;

@Component
public class MongoUserDataRepository implements UserRepositoryInterface {

	private final SpringDataUserMongoRepository sDataUserMongoRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public MongoUserDataRepository(SpringDataUserMongoRepository sDataUserMongoRepository,
			PasswordEncoder passwordEncoder) {
		this.sDataUserMongoRepository = sDataUserMongoRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserResponseModel<UserDataModel> saveUser(UserDomain userDomain) {
		UserResponseModel<UserDataModel> userDataResponse = new UserResponseModel<UserDataModel>();
		try {
			UserDataModel userData = new UserDataModel();
			if (userDomain.getId() != null) {
				userData.setId(userDomain.getId());
			}
			userData.setUserId(userDomain.getUserId());
			userData.setPassword(passwordEncoder.encode(userDomain.getPassword()));
			userData.setName(userDomain.getName());

			userData.setTanentId(userDomain.getTanentId());
			userData.setPhoneNumber(userDomain.getPhoneNumber());
			userData.setEmail(userDomain.getEmail());
			userData.setPhoto(userDomain.getPhoto());

			UserDataModel userDataSaveResponse = sDataUserMongoRepository.save(userData);

			userDataResponse.status = GeneralConstants.USER_RESPONSE_MESSAGE_SUCCESS_RESULT;
			userDataResponse.response = Arrays.asList(userDataSaveResponse);

		} catch (Exception e) {
			userDataResponse.status = GeneralConstants.USER_RESPONSE_MESSAGE_FAILURE_RESULT;
			userDataResponse.response = Arrays.asList(new UserDataModel());
		}
		return userDataResponse;
	}

	@Override
	public UserResponseModel<UserDataModel> getAllUser() {
		UserResponseModel<UserDataModel> userDataResponse = new UserResponseModel<UserDataModel>();

		try {
			List<UserDataModel> userDataGetRespone = sDataUserMongoRepository.findAll();
			userDataResponse.status = GeneralConstants.USER_RESPONSE_MESSAGE_SUCCESS_RESULT;
			userDataResponse.response = userDataGetRespone;
		} catch (Exception e) {
			userDataResponse.status = GeneralConstants.USER_RESPONSE_MESSAGE_FAILURE_RESULT;
			userDataResponse.response = Arrays.asList(new UserDataModel());
		}

		return userDataResponse;
	}

	@Override
	public UserResponseModel<UserDataModel> getUserById(String id) {
		UserResponseModel<UserDataModel> userDataResponse = new UserResponseModel<UserDataModel>();

		try {
			UserDataModel roleDataGetRespone = sDataUserMongoRepository.findUserByUserId(id);
			userDataResponse.status = GeneralConstants.USER_RESPONSE_MESSAGE_SUCCESS_RESULT;
			userDataResponse.response = Arrays.asList(roleDataGetRespone);
			return userDataResponse;
		} catch (Exception e) {
			userDataResponse.status = GeneralConstants.USER_RESPONSE_MESSAGE_FAILURE_RESULT;
			userDataResponse.response = Arrays.asList(new UserDataModel());
			return userDataResponse;
		}

	}

}
