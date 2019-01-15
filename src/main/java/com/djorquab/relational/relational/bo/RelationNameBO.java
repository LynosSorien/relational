package com.djorquab.relational.relational.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@lombok.Builder
public class RelationNameBO implements Serializable {
	private static final long serialVersionUID = 8823793833431291554L;
	
	private Long id;
	private String name;
	private Date lastModified;
	private Date createdOn;
}
