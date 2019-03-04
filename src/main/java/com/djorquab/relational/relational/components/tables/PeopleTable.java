package com.djorquab.relational.relational.components.tables;

import com.djorquab.relational.relational.commons.ColumnType;
import com.djorquab.relational.relational.processor.ViewColumn;
import lombok.Data;

import java.io.Serializable;

@Data
public class PeopleTable implements Serializable {
    @ViewColumn(header = "ID", position = 0, link = "/backoffice/people/view", pathVariable = true, linkable = true)
    private Long id;
    @ViewColumn(header = "Name", position = 1)
    private String name;
    @ViewColumn(header = "Surname", position = 2)
    private String surname;
    @ViewColumn(header = "Image", position = 3, type = ColumnType.IMAGE)
    private String image;
}
