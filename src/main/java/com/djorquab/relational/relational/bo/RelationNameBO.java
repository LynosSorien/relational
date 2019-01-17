package com.djorquab.relational.relational.bo;

import java.io.Serializable;
import java.util.Date;

import com.djorquab.relational.relational.processor.ViewColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@lombok.Builder
public class RelationNameBO implements Serializable {
	private static final long serialVersionUID = 8823793833431291554L;
	
	@ViewColumn(header = "ID")
	private Long id;
	@ViewColumn(header = "Name")
	private String name;
	private Date lastModified;
	private Date createdOn;
}
