package com.djorquab.relational.relational.components.forms;

import com.djorquab.relational.relational.commons.FieldType;
import com.djorquab.relational.relational.components.filters.PeopleSearcher;
import com.djorquab.relational.relational.components.tables.PeopleTable;
import com.djorquab.relational.relational.processor.FormField;
import lombok.Data;

@Data
public class PersonRelationForm {
    @FormField(label = "People relations", type = FieldType.RELATION,
            searcher = @FormField.Searcher(searcher = PeopleSearcher.class, searchLink = "/backoffice/people/find/by", tableDefinition = PeopleTable.class),
            position = 0)
    private PeopleSearcher person;
}
