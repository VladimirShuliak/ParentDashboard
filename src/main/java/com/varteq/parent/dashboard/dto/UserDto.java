package com.varteq.parent.dashboard.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserDto {

    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "visit")
    private LocalDateTime visit;

    @Column(name = "password")
    private String password;
}
