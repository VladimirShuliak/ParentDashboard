package com.varteq.parent.dashboard.dao.model;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "homework")
public class HomeWorkEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "grade")
    private int grade;

    @JsonIgnore
    @OneToMany(mappedBy = "homeWork", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @JsonIgnore
    @OneToMany(mappedBy = "homeWork", fetch = FetchType.LAZY)
    private List<CourseEntity> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "homeWork", fetch = FetchType.LAZY)
    private List<GradeBookEntity> gradeBooks;
}
