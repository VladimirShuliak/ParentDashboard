package com.varteq.parent.dashboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class GradeBookDto {

    private Long id;
    private String grade;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private UserDto user;
    private List<CourseDto> courses;
    private HomeWorkDto homeWork;
}
