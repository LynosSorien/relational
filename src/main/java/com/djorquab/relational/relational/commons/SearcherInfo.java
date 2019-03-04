package com.djorquab.relational.relational.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearcherInfo implements Serializable {
    private List<Filter> filters;
    private String acceptLink;
    private String paginationLink;
    private String searchLink;
    private TableDefinition tableDefinition;
}
