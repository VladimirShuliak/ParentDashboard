package com.varteq.parent.dashboard.dao.mapper;

import com.varteq.parent.dashboard.common.EntityMapper;
import com.varteq.parent.dashboard.dao.model.ParentEntity;
import com.varteq.parent.dashboard.dao.model.StudentEntity;
import com.varteq.parent.dashboard.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToParentService extends EntityMapper<ParentEntity, UserDto> {

    @Autowired
    CourseEntityMapper courseMapper;

    @Autowired
    GradeBookMapper gradeBookMapper;


    @Override
    protected UserDto asDto(ParentEntity parentEntity) {
        return UserDto.builder()
                .name(parentEntity.getName())
                .dob(parentEntity.getDob())
                .email(parentEntity.getEmail())
                .build();
    }

    @Override
    protected ParentEntity asEntity(UserDto userDto) {
        return null;
    }
}
