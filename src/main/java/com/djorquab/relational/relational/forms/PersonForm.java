package com.djorquab.relational.relational.forms;

import java.io.Serializable;
import java.util.Date;

import com.djorquab.relational.relational.commons.FieldType;
import com.djorquab.relational.relational.processor.FormDef;
import com.djorquab.relational.relational.processor.FormField;

@FormDef(method = "POST", submitButtonLabel = "Create", submitLink = "/backoffice/people")
public class PersonForm implements Serializable {
	private static final long serialVersionUID = -5241309306612469463L;
	
	@FormField(label = "Name", type = FieldType.TEXT, position = 0, colspan = 6)
	private String name;
	
	@FormField(label = "Surname", type = FieldType.TEXT, position = 1, colspan = 6)
	private String surname;
	
	@FormField(label = "Description", type = FieldType.DESCRIPTION, colspan = 12, position = 2)
	private String description;
	
	@FormField(label = "Birthday", type = FieldType.DATE, colspan = 4, position = 3)
	private Date birthday;
	
	@FormField(label = "Deathdate", type = FieldType.DATE, colspan = 4, position = 4)
	private Date deathdate;
}
