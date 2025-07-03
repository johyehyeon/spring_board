package com.mysite.sbb.user;

import lombok.Getter;

@Getter
public enum UserRole {
	// ADMIN, USER 상수, 각각'ROLE_ADMON','ROLE_USER' 라는 값을 저장
	// 상수이기때문에 @Setter불필요
	ADMIN("ROLE_ADMON"), USER("ROLE_USER");

	UserRole(String value) {
		this.value = value;
	}

	private String value;

}
