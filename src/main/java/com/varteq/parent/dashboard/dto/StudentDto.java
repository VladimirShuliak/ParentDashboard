package com.varteq.parent.dashboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class StudentDto {

    private String id;
    private String name;
    private String dob;
    private String email;
    private boolean studentInd;
    private boolean parentInd;
    private GradeBookDto gradebook;
    private List<CourseDto> courses;
}
