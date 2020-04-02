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
@Table(name = "gradebook")
public class GradeBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "grade")
    private String grade;

    @Column(name = "dateFrom")
    private LocalDateTime dateFrom;

    @Column(name = "dateTo")
    private LocalDateTime dateTo;

    @OneToOne(mappedBy = "gradebook")
    private UserEntity user;

    @ManyToMany
    @JoinTable(name = "gradebooks_cousrses", joinColumns = {@JoinColumn(name="gradebook_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name="course_id", referencedColumnName = "id")})
    private List<CourseEntity> courses;

    @ManyToOne
    @JoinColumn(name="homework_id", nullable=false)
    private HomeWorkEntity homeWork;

}
