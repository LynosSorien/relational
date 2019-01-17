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
public class PagedResult<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = -1154728610334127016L;
	
	private long page;
	private long totalElements;
	private long numberOfPages;
	private long pageSize;
	
	private List<T> elements;
}
