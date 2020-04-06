package com.varteq.parent.dashboard.dto;

import com.varteq.parent.dashboard.dao.model.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleDto {

    private long id;
    private RoleType name;
    private String description;
    private Long createdOn;
    private Long modifiedOn;
}
