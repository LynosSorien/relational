package com.djorquab.relational.relational.commons;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormFieldDefinition implements Comparable<FormFieldDefinition>, Serializable {
	private static final long serialVersionUID = -1273250837751498006L;
	
	private String variable;
	private String label;
	private FieldType type;
	private int position;
	private int colspan;
	
	
	@Override
	public int compareTo(FormFieldDefinition o) {
		if (o != null) {
			return Integer.compare(position, o.position);
		}
		return 1;
	}
}
