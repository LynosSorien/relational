package com.djorquab.relational.relational.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.djorquab.relational.relational.commons.ActionType;
import com.djorquab.relational.relational.commons.ColumnType;
import com.djorquab.relational.relational.processor.ActionDefinition;
import com.djorquab.relational.relational.processor.ViewColumn;
import com.fasterxml.jackson.annotation.JsonFormat;
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

	@ViewColumn(header = "ID", position = 0,
			actions = {
					@ActionDefinition(active = true, type = ActionType.EDIT, path = "/backoffice/people/edit", requestParam = "personId", method = "GET"),
					@ActionDefinition(active = true, type = ActionType.DELETE, path = "/backoffice/people", requestParam = "personId", method = "DELETE")
			}
	)
	private Long id;
	@ViewColumn(header = "Name", position = 1)
	private String name;
	@ViewColumn(header = "Surname", position = 2)
	private String surname;
	@ViewColumn(header = "Description", position = 3)
	private String description;
	@ViewColumn(header = "Birthday", position = 4)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthday;
	@ViewColumn(header = "Deathdate", position = 5)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date deathdate;

	@ViewColumn(header = "Image", position = 6, type = ColumnType.IMAGE)
	private String image;
	
	private Date lastModified;
	private Date createdOn;
	
	private List<PeopleRelationBO> peopleRelations;
}
