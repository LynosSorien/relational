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
public class ColumnDefinition implements Serializable, Comparable<ColumnDefinition> {
	private static final long serialVersionUID = 6418164662492215949L;
	
	private String variable;
	private String header;
	@Builder.Default private ColumnType type = ColumnType.TEXT;
	private String link;
	@Builder.Default private boolean withLink = false;
	@Builder.Default private boolean pathVariable = false;
	@Builder.Default private LinkType linkType = LinkType.LINK;
	@Builder.Default private int position = -1;
	
	
	@Override
	public int compareTo(ColumnDefinition o) {
		if (o != null) {
			return Integer.compare(position, o.position);
		}
		return 1;
	}
}
