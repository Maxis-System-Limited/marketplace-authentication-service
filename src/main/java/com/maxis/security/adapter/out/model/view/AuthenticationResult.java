package com.maxis.security.adapter.out.model.view;

public class AuthenticationResult {
	private String code;
	private String result;
	private String message;
	private String userId;
	private String tanentId;

	public String getTanentId() {
		return tanentId;
	}

	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}

	private String userName;
	private String userPhone;
	private String userImage;

	private AuthenticationResponse authResponse;

	public AuthenticationResult() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public AuthenticationResponse getAuthResponse() {
		return authResponse;
	}

	public void setAuthResponse(AuthenticationResponse authResponse) {
		this.authResponse = authResponse;
	}

}
