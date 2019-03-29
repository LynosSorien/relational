package com.djorquab.relational.relational.commons;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormDefinition<T> implements Serializable {
	private static final long serialVersionUID = -1363693446820281697L;

	private String title;
	private String method;
	private String submitLink;
	private String submitButtonLabel;
	private List<FormFieldDefinition> fields;
	private String navigateTo;
	private T object;
}
