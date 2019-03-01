package com.djorquab.relational.relational.processor;

import com.djorquab.relational.relational.commons.Filter;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class FilterProcessor extends Processor<List<Filter>, FieldFilter> {

    @Override
    protected List<Filter> postProcess(List<Filter> instance) {
        return instance;
    }

    @Override
    protected List<Filter> processField(FieldFilter annotation, List<Filter> instance, Field field) {
        Filter filter = Filter.builder()
                .variable(annotation.variable())
                .label(annotation.label())
                .type(annotation.type())
                .options(new LinkedList<>())
                .build();
        if ("".equalsIgnoreCase(filter.getVariable())) {
            filter.setVariable(field.getName());
        }
        instance.add(filter);
        return instance;
    }

    @Override
    protected Class<FieldFilter> annotationClass() {
        return FieldFilter.class;
    }

    @Override
    protected Class<List<Filter>> instanceClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected List<Filter> getObjectInstance() {
        return new LinkedList<>();
    }
}
