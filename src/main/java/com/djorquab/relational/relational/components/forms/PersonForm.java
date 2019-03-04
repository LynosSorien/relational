package com.djorquab.relational.relational.components.forms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.FieldType;
import com.djorquab.relational.relational.components.filters.PeopleSearcher;
import com.djorquab.relational.relational.components.tables.PeopleTable;
import com.djorquab.relational.relational.processor.FormDef;
import com.djorquab.relational.relational.processor.FormField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@FormDef(submitButtonLabel = "Create", submitLink = "/backoffice/people", navigateTo = "/backoffice/people")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonForm implements Serializable {
	private static final long serialVersionUID = -5241309306612469463L;

	@FormField(label = "ID", hidden = true)
	private Long id;

	@FormField(label = "Name", type = FieldType.TEXT, position = 0, colspan = 6)
	private String name;
	
	@FormField(label = "Surname", type = FieldType.TEXT, position = 1, colspan = 6)
	private String surname;

	@FormField(label = "Image URL", type = FieldType.TEXT, position = 2)
	private String image;
	
	@FormField(label = "Description", type = FieldType.DESCRIPTION, position = 3)
	private String description;
	
	@FormField(label = "Birthday", type = FieldType.DATE, colspan = 4, position = 4)
	private Date birthday;
	
	@FormField(label = "Deathdate", type = FieldType.DATE, colspan = 4, position = 5)
	private Date deathdate;

	@FormField(label = "People relations", type = FieldType.RELATION,
			searcher = @FormField.Searcher(searcher = PeopleSearcher.class, acceptLink = "", paginationLink = "", searchLink = "/backoffice/people/find/by", tableDefinition = PeopleTable.class),
			position = 6)
	private List<PersonBO> peopleRelations;
}
