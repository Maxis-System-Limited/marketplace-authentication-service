package com.maxis.security.adapter.out.model.view;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 345L;

	private final String id_token;
	private long expires_in;
	private final String refresh_token;

	public AuthenticationResponse(String id_token, String refresh_token) {
		this.id_token = id_token;
		this.refresh_token = refresh_token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId_token() {
		return id_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

}
