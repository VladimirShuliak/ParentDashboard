package com.varteq.parent.dashboard.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
