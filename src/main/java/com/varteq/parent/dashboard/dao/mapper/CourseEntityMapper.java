package com.varteq.parent.dashboard.dao.mapper;

import com.varteq.parent.dashboard.common.EntityMapper;
import com.varteq.parent.dashboard.dao.model.CourseEntity;
import com.varteq.parent.dashboard.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseEntityMapper extends EntityMapper<CourseEntity, CourseDto> {

    @Autowired
    UserEntityMapper userEntityMapper;

    @Autowired
    GradeBookMapper gradeBookMapper;

    @Autowired
    HomeWorkMapper homeWorkMapper;

    @Override
    protected CourseDto asDto(CourseEntity courseEntity) {
        return CourseDto.builder()
                .id(courseEntity.getId())
                .name(courseEntity.getName())
                .start(courseEntity.getStart())
                .end(courseEntity.getEnd())
                .build();
    }

    @Override
    protected CourseEntity asEntity(CourseDto courseDto) {
        return CourseEntity.builder()
                .id(courseDto.getId())
                .name(courseDto.getName())
                .start(courseDto.getStart())
                .end(courseDto.getEnd())
                .users(userEntityMapper.toEntityList(courseDto.getUsers()))
                .gradeBooks(gradeBookMapper.toEntityList(courseDto.getGradeBooks()))
                .homeWork(homeWorkMapper.toEntity(courseDto.getHomeWork()))
                .build();
    }
}
