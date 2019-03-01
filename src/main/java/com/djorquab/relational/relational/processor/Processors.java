package com.djorquab.relational.relational.processor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Processors {
	private static TableDefinitionProcessor tableDefinitionProcessor;
	private static FormProcessor formProcessor;
	private static FilterProcessor filterProcessor;
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

	public static FormProcessor formProcessorInstance() {
		if (formProcessor == null) {
			synchronized(LOCK) {
				if (formProcessor == null) {
					formProcessor = new FormProcessor();
				}
			}
		}
		return formProcessor;
	}

	public static FilterProcessor filterProcessorInstance() {
		if (filterProcessor == null) {
			synchronized(LOCK) {
				if (filterProcessor == null) {
					filterProcessor = new FilterProcessor();
				}
			}
		}
		return filterProcessor;
	}
}
