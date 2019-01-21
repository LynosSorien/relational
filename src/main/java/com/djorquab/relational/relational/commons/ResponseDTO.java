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
public class ResponseDTO<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 8597599404613304397L;
	
	private String message;
	private T payload;
}
