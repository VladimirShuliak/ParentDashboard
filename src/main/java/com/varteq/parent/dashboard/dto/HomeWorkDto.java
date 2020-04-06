package com.varteq.parent.dashboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class HomeWorkDto {

    private Long id;
    private String description;
    private int grade;
    private List<UserDto> users;
    private List<CourseDto> courses;
    private List<GradeBookDto> gradeBooks;
}
