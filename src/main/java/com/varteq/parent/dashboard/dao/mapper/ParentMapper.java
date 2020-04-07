package com.varteq.parent.dashboard.dao.mapper;

import com.varteq.parent.dashboard.common.EntityMapper;
import com.varteq.parent.dashboard.dao.model.UserEntity;
import com.varteq.parent.dashboard.dto.ParentDto;

public class ParentMapper extends EntityMapper<UserEntity, ParentDto> {
    @Override
    protected ParentDto asDto(UserEntity userEntity) {
        return ParentDto.builder()

                .build();
    }

    @Override
    protected UserEntity asEntity(ParentDto parentDto) {
        return null;
    }
}
