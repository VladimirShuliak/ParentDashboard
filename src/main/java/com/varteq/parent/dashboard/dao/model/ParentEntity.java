package com.varteq.parent.dashboard.dao.model;


import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parents")
public class ParentEntity {


    @Id
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private String dob;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "parents_students", joinColumns = @JoinColumn(
            name = "parent_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private List<StudentEntity> students;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gradebook_id", referencedColumnName = "id")
    private GradeBookEntity gradebook;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_courses", joinColumns = @JoinColumn(
            name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "couse_id", referencedColumnName = "id"))
    private List<CourseEntity> courses;
}
