package com.djorquab.relational.relational.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Filter implements Serializable {
    private String label;
    private String type;
    private String variable;
    private List<?> options;
}
