package com.varteq.parent.dashboard.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="courseStart")
    private LocalDateTime start;

    @Column(name = "courseEnd")
    private LocalDateTime end;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<GradeBookEntity> gradeBooks;

    @ManyToOne
    @JoinColumn(name="homework_id", nullable=false)
    private HomeWorkEntity homeWork;

}
