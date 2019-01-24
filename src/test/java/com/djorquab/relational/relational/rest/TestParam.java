package com.djorquab.relational.relational.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestParam<O> {
	private String param;
	private O value;
}
