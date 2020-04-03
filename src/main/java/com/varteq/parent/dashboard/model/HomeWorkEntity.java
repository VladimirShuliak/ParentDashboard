package com.varteq.parent.dashboard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name="description")
    private String description;

    @Column(name="grade")
    private int grade;

    @OneToMany(mappedBy = "homeWork", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @OneToMany(mappedBy = "homeWork", fetch = FetchType.LAZY)
    private List<CourseEntity> courses;

    @OneToMany(mappedBy = "homeWork", fetch = FetchType.LAZY)
    private List<GradeBookEntity> gradeBooks;


}
