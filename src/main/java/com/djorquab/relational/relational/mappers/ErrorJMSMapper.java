package com.djorquab.relational.relational.mappers;

import com.djorquab.relational.relational.bo.ErrorJMSBO;
import com.djorquab.relational.relational.model.ErrorJMS;
import org.mapstruct.Mapper;

@Mapper
public interface ErrorJMSMapper extends AbstractMapper<ErrorJMS, ErrorJMSBO> {
}
