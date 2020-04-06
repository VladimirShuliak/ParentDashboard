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

    @Autowired
    private HomeWorkMapper homeWorkMapper;

    @Override
    protected UserDto asDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .email(userEntity.getEmail())
                .visit(userEntity.getVisit())
                .password(userEntity.getPassword())
                .roles(userEntity.getRoles())
                .build();
    }

    @Override
    protected UserEntity asEntity(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .visit(userDto.getVisit())
                .password(userDto.getPassword())
                .roles(userDto.getRoles())
                .courses(courseEntityMapper.toEntityList(userDto.getCourses()))
                .gradebook(gradeBookMapper.toEntity(userDto.getGradebook()))
                .homeWork(homeWorkMapper.toEntity(userDto.getHomeWork()))
                .build();
    }
}
