package com.varteq.parent.dashboard.dao.mapper;

import com.varteq.parent.dashboard.common.EntityMapper;
import com.varteq.parent.dashboard.dao.model.Role;
import com.varteq.parent.dashboard.dto.RoleDto;

public class RoleMapper extends EntityMapper<Role, RoleDto> {
    @Override
    protected RoleDto asDto(Role role) {
        return RoleDto.builder().id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .createdOn(role.getCreatedOn())
                .modifiedOn(role.getModifiedOn())
                .build();
    }

    @Override
    protected Role asEntity(RoleDto roleDto) {
        return Role.builder().id(roleDto.getId())
                .name(roleDto.getName())
                .description(roleDto.getDescription())
                .createdOn(roleDto.getCreatedOn())
                .modifiedOn(roleDto.getModifiedOn())
                .build();
    }
}
