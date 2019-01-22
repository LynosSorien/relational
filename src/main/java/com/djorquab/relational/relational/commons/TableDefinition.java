package com.djorquab.relational.relational.commons;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableDefinition implements Serializable {
	private static final long serialVersionUID = -2659582962542076499L;

	private String id;
	private List<ColumnDefinition> columns;
	private List<Action> actions;
}
