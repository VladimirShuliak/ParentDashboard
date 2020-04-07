package com.varteq.parent.dashboard.dao.mapper;

import com.varteq.parent.dashboard.common.EntityMapper;
import com.varteq.parent.dashboard.dao.model.UserEntity;
import com.varteq.parent.dashboard.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper extends EntityMapper<UserEntity, UserDto> {

    @Autowired
    private GradeBookMapper gradeBookMapper;

    @Autowired
    private CourseEntityMapper courseEntityMapper;

    @Override
    protected UserDto asDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .dob(userEntity.getDob())
                .email(userEntity.getEmail())
                .visit(userEntity.getVisit())
                .password(userEntity.getPassword())
                .studentInd(userEntity.isStudentInd())
//                .parentInd(userEntity.isParentInd())
                .roles(userEntity.getRoles())
                .build();
    }

    @Override
    protected UserEntity asEntity(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .dob(userDto.getDob())
                .email(userDto.getEmail())
                .visit(userDto.getVisit())
                .password(userDto.getPassword())
                .studentInd(userDto.isStudentInd())
                .parentInd(userDto.isParentInd())
                .roles(userDto.getRoles())
                .courses(courseEntityMapper.toEntityList(userDto.getCourses()))
                .gradebook(gradeBookMapper.toEntity(userDto.getGradebook()))
                .build();
    }
}
