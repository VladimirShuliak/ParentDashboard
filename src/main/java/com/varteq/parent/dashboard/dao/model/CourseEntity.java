package com.varteq.parent.dashboard.dao.model;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;

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
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "courseStart")
    private LocalDateTime start;

    @Column(name = "courseEnd")
    private LocalDateTime end;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<GradeBookEntity> gradeBooks;

    @JsonIgnore
    @ManyToOne(optional = true)
    @JoinColumn(name = "homework_id", nullable = true)
    private HomeWorkEntity homeWork;

}
