package com.djorquab.relational.relational.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonBO implements Serializable {
	private static final long serialVersionUID = 2303305096393347270L;
	
	private Long id;
	private String name;
	private String surname;
	private CountryBO country;
	private String description;
	private Date birthday;
	private Date deathDate;
	
	private Date lastModified;
	private Date createdOn;
	
	private List<PeopleRelationBO> peopleRelations;
}
