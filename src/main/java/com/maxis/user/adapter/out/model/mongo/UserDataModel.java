package com.maxis.user.adapter.out.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public class UserDataModel {
	@Id
	private String id;
	private String userId;
	private String password;
	private String name;
	private String tanentId;
	private String phoneNumber;
	private String email;
	private String photo;

	public UserDataModel(String id, String userId, String password, String name, String tanentId, String phoneNumber,
			String email, String photo) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.tanentId = tanentId;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.photo = photo;
	}

	public UserDataModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTanentId() {
		return tanentId;
	}

	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}

}
