//package com.maxis.user.adapter.in.kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import com.google.gson.Gson;
//import com.maxis.user.adapter.out.model.mongo.UserDataModel;
//import com.maxis.user.adapter.out.model.mongo.UserResponseModel;
//import com.maxis.user.application.port.in.MessageBrokerInterface;
//import com.maxis.user.application.port.in.UserInputInterface;
//import com.maxis.user.domain.UserDomain;
//
//@Component
//public class KafkaMessageConsumer implements MessageBrokerInterface {
//
//	private final UserInputInterface userService;
//
//	@Autowired
//	public KafkaMessageConsumer(UserInputInterface userService) {
//		this.userService = userService;
//	}
//
//	@KafkaListener(topics = "user_data", groupId = "auth_group_user", containerFactory = "kafkaListenerContainerFactory")
//	@Override
//	public UserDomain listenUserDomain(String message) {
//		Gson g = new Gson();
//		try {
//			UserDomain userDomain = g.fromJson(message, UserDomain.class);
//			System.out.println("received:" + userDomain.toString());
//
//			UserResponseModel<UserDataModel> userData = userService.getUserById(userDomain.getUserId());
//			if (userData.response.get(0) == null) {
//				userService.saveUser(userDomain);
//			} else {
//				userDomain.setId(userData.response.get(0).getId());
//				userService.saveUser(userDomain);
//			}
//
//			return userDomain;
//		} catch (Exception e) {
//			return new UserDomain();
//		}
//
//	}
//
//}
