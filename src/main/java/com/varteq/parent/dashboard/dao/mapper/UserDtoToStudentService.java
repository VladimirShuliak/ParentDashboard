package com.varteq.parent.dashboard.dao.mapper;

import com.varteq.parent.dashboard.common.EntityMapper;
import com.varteq.parent.dashboard.dao.model.StudentEntity;
import com.varteq.parent.dashboard.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToStudentService extends EntityMapper<StudentEntity, UserDto> {

    @Autowired
    CourseEntityMapper courseMapper;

    @Autowired
    GradeBookMapper gradeBookMapper;

    @Override
    protected UserDto asDto(StudentEntity studentEntity) {
        return UserDto.builder()
                .name(studentEntity.getName())
                .dob(studentEntity.getDob())
                .email(studentEntity.getEmail())
                .build();
    }

    @Override
    protected StudentEntity asEntity(UserDto userDto) {
        return null;
    }
}
