package com.varteq.parent.dashboard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonBackReference
    @Column(name = "courseEnd")
    private LocalDateTime end;

    @JsonBackReference
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @JsonBackReference
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<GradeBookEntity> gradeBooks;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="homework_id", nullable=false)
    private HomeWorkEntity homeWork;

}
