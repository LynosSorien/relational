package com.djorquab.relational.relational.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordBO implements Serializable {
	private static final long serialVersionUID = -7688333394842810849L;
	
	private Long id;
	private String title;
	private String subtitle;
	private String description;
	
	private Date lastModified;
	private Date createdOn;
}
