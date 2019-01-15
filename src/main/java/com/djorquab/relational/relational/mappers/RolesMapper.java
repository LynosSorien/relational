package com.djorquab.relational.relational.mappers;

import org.mapstruct.Mapper;

import com.djorquab.relational.relational.bo.Role;
import com.djorquab.relational.relational.model.RoleEntity;

@Mapper
public interface RolesMapper extends AbstractMapper<RoleEntity, Role>{

}
