package com.djorquab.relational.relational.commons;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NamedDTO implements Serializable {
	private static final long serialVersionUID = 3984890328528540304L;
	
	private String name;
}
