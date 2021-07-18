package com.maxis.user.adapter.out.model.mongo;

import java.util.List;

public class UserResponseModel<T> {
	public String status;
	public List<T> response;

	@Override
	public String toString() {
		return "UserResponseModel [status=" + status + ", response=" + response + "]";
	}
}
