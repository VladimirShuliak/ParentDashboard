package com.varteq.parent.dashboard.dao.mapper;

import com.varteq.parent.dashboard.common.EntityMapper;
import com.varteq.parent.dashboard.dao.model.HomeWorkEntity;
import com.varteq.parent.dashboard.dto.HomeWorkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeWorkMapper extends EntityMapper<HomeWorkEntity, HomeWorkDto> {

    @Autowired
    UserEntityMapper userEntityMapper;

    @Autowired
    CourseEntityMapper courseEntityMapper;

    @Autowired
    GradeBookMapper gradeBookMapper;

    @Override
    protected HomeWorkDto asDto(HomeWorkEntity homeWorkEntity) {
        return HomeWorkDto.builder()
                .id(homeWorkEntity.getId())
                .description(homeWorkEntity.getDescription())
                .grade(homeWorkEntity.getGrade())
                .build();
    }

    @Override
    protected HomeWorkEntity asEntity(HomeWorkDto homeWorkDto) {
        return HomeWorkEntity.builder()
                .id(homeWorkDto.getId())
                .description(homeWorkDto.getDescription())
                .grade(homeWorkDto.getGrade())
//                .users(userEntityMapper.toEntityList(homeWorkDto.getUsers()))
                .courses(courseEntityMapper.toEntityList(homeWorkDto.getCourses()))
                .gradeBooks(gradeBookMapper.toEntityList(homeWorkDto.getGradeBooks()))
                .build();
    }
}
