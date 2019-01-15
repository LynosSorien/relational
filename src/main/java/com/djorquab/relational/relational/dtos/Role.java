package com.djorquab.relational.relational.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {
	private static final long serialVersionUID = -6950016437116860643L;
	
	private String role;

	@Override
	public String getAuthority() {
		return role;
	}

}
