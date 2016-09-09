package com.etnlgravtnl.common.persistence;

import java.io.Serializable;


public class AuthenticationResult implements Serializable {
	
	private String status;
	private String token;
	private Long level;
	private Boolean passwordReset;

	public AuthenticationResult() {}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Boolean getPasswordReset() {
		return passwordReset;
	}

	public void setPasswordReset(Boolean passwordReset) {
		this.passwordReset = passwordReset;
	}
}
