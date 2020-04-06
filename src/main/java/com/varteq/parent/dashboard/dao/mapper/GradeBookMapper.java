package com.varteq.parent.dashboard.dao.mapper;

import com.varteq.parent.dashboard.common.EntityMapper;
import com.varteq.parent.dashboard.dao.model.GradeBookEntity;
import com.varteq.parent.dashboard.dto.GradeBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GradeBookMapper extends EntityMapper<GradeBookEntity, GradeBookDto> {

    @Autowired
    UserEntityMapper userEntityMapper;

    @Autowired
    CourseEntityMapper courseEntityMapper;

    @Autowired
    HomeWorkMapper homeWorkMapper;

    @Override
    protected GradeBookDto asDto(GradeBookEntity gradeBookEntity) {
        return GradeBookDto.builder()
                .id(gradeBookEntity.getId())
                .grade(gradeBookEntity.getGrade())
                .dateFrom(gradeBookEntity.getDateFrom())
                .dateTo(gradeBookEntity.getDateTo())
                .build();
    }

    @Override
    protected GradeBookEntity asEntity(GradeBookDto gradeBookDto) {
        return GradeBookEntity.builder()
                .id(gradeBookDto.getId())
                .grade(gradeBookDto.getGrade())
                .dateFrom(gradeBookDto.getDateFrom())
                .dateTo(gradeBookDto.getDateTo())
                .user(userEntityMapper.toEntity(gradeBookDto.getUser()))
                .courses(courseEntityMapper.toEntityList(gradeBookDto.getCourses()))
                .homeWork(homeWorkMapper.toEntity(gradeBookDto.getHomeWork()))
                .build();
    }
}
