package com.djorquab.relational.relational.processor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Processors {
	private static TableDefinitionProcessor tableDefinitionProcessor;
	private static final String LOCK = "PROCESSOR-LOCK";
	
	public static TableDefinitionProcessor tableDefinitionInstance() {
		if (tableDefinitionProcessor == null) {
			synchronized(LOCK) {
				if (tableDefinitionProcessor == null) {
					tableDefinitionProcessor = new TableDefinitionProcessor();
				}
			}
		}
		return tableDefinitionProcessor;
	}
}
