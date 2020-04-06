package com.varteq.parent.dashboard.dto;


import com.varteq.parent.dashboard.dao.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserDto {

    private String id;
    private String name;
    private String surname;
    private String email;
    private LocalDateTime visit;
    private String password;
    private Set<Role> roles;
    private GradeBookDto gradebook;
    private List<CourseDto> courses;
    private HomeWorkDto homeWork;
}
