package com.djorquab.relational.relational.commons;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NamedDTO implements Serializable {
	private static final long serialVersionUID = 3984890328528540304L;
	
	private String name;
}
