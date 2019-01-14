package com.djorquab.relational.relational.managers;

import java.io.Serializable;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyManager implements Serializable {
	private static final long serialVersionUID = -6503747481485760996L;
	
	@Value("${bootstrapping:false}")
	@Getter
	private boolean bootstrapping;
}
