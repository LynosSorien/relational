package com.djorquab.relational.relational.mappers;

import org.mapstruct.Mapper;

import com.djorquab.relational.relational.bo.RecordBO;
import com.djorquab.relational.relational.model.RecordEntity;

@Mapper
public interface RecordMapper extends AbstractMapper<RecordEntity, RecordBO> {

}
