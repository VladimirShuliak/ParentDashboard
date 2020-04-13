package com.varteq.parent.dashboard.dao.mapper;

import com.varteq.parent.dashboard.common.EntityMapper;
import com.varteq.parent.dashboard.dao.model.UserEntity;
import com.varteq.parent.dashboard.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper extends EntityMapper<UserEntity, UserDto> {

    @Override
    protected UserDto asDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .visit(userEntity.getVisit())
                .password(userEntity.getPassword())
                .build();
    }

    @Override
    protected UserEntity asEntity(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .visit(userDto.getVisit())
                .password(userDto.getPassword())
                .build();
    }
}
