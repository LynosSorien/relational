package com.djorquab.relational.relational.components.filters;

import com.djorquab.relational.relational.processor.FieldFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeopleSearcher implements Serializable {
    @FieldFilter(label = "Name", shown = true)
    private String name;

    @FieldFilter(label = "Surname", shown = true)
    private String surname;
}
