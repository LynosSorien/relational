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
public class Action implements Serializable {
	private static final long serialVersionUID = -1143869554889968354L;

	private String name;
	private String variable;
	private String requestParam;
	private String path;
	@Builder.Default
	private boolean pathVariable = false;
	private ActionType type;
	private String method;
}
