package com.varteq.parent.dashboard.dto;


import com.varteq.parent.dashboard.dao.model.GradeBookEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ParentDto {

    private String id;
    private String name;
    private String dob;
    private String email;
    private List<StudentDto> students;
    private GradeBookEntity gradebook;
    private List<CourseDto> courses;
}
