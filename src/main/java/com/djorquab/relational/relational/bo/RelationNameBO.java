package com.djorquab.relational.relational.bo;

import java.io.Serializable;
import java.util.Date;

import com.djorquab.relational.relational.commons.ActionType;
import com.djorquab.relational.relational.processor.ActionDefinition;
import com.djorquab.relational.relational.processor.FormDef;
import com.djorquab.relational.relational.processor.FormField;
import com.djorquab.relational.relational.processor.ViewColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FormDef(method = "POST", submitLink = "/api/relation/names")
public class RelationNameBO implements Serializable {
	private static final long serialVersionUID = 8823793833431291554L;
	
	@ViewColumn(header = "ID",
			actions = {
					@ActionDefinition(active = true, type = ActionType.EDIT, pathVariable = true, path = "/backoffice/relational/names/edit", method = "GET"),
					@ActionDefinition(active = true, type = ActionType.DELETE, path = "/backoffice/relational/names", requestParam = "relationId")
			}
	)
	private Long id;
	@ViewColumn(header = "Name")
	@FormField(label="Name")
	private String name;
	private Date lastModified;
	private Date createdOn;
}
