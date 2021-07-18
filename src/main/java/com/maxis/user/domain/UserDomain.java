package com.maxis.user.domain;

public class UserDomain {
	private String id;
	private String userId;
	private String password;
	private String name;
	private String tanentId;
	private String phoneNumber;
	private String email;
	private String photo;

	public UserDomain(String id, String userId, String password, String name, String tanentId, String phoneNumber,
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

	public UserDomain() {

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

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoto() {
		return photo;
	}

	public String getTanentId() {
		return tanentId;
	}

}
