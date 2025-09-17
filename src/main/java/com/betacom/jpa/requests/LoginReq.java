package com.betacom.jpa.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginReq {

	private String user;
	private String password;
	
}
