package com.djorquab.relational.relational.bo;

import java.io.Serializable;
import java.util.Date;

import com.djorquab.relational.relational.commons.RelationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeopleRelationBO implements Serializable {
	private static final long serialVersionUID = -2469345196814328697L;

	private Long id;
	private PersonBO personFrom;
	private PersonBO personTo;
	private RelationType type;
	private RelationNameBO relationName;
	private Date lastModified;
	private Date createdOn;
}
