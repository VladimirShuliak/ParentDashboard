package com.varteq.parent.dashboard.dao.mapper;

import com.varteq.parent.dashboard.common.EntityMapper;
import com.varteq.parent.dashboard.dao.model.StudentEntity;
import com.varteq.parent.dashboard.dao.model.UserEntity;
import com.varteq.parent.dashboard.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper extends EntityMapper<StudentEntity, StudentDto> {

    @Autowired
    private GradeBookMapper gradeBookMapper;

    @Autowired
    private CourseEntityMapper courseEntityMapper;

    @Override
    protected StudentDto asDto(StudentEntity studentEntity) {
        return StudentDto.builder()
                .id(studentEntity.getId())
                .name(studentEntity.getName())
                .dob(studentEntity.getDob())
                .email(studentEntity.getEmail())
                .build();
    }

    @Override
    protected StudentEntity asEntity(StudentDto studentDto) {
        return StudentEntity.builder()
                .id(studentDto.getId())
                .name(studentDto.getName())
                .dob(studentDto.getDob())
                .email(studentDto.getEmail())
                .courses(courseEntityMapper.toEntityList(studentDto.getCourses()))
                .gradebook(gradeBookMapper.toEntity(studentDto.getGradebook()))
                .build();
    }


    // from user to student
    protected StudentDto asDto(UserEntity userEntity) {
        return StudentDto.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }
}
