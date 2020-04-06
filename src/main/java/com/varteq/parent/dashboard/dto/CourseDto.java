package com.varteq.parent.dashboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class CourseDto {

    private Long id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<UserDto> users;
    private List<GradeBookDto> gradeBooks;
    private HomeWorkDto homeWork;

}
