package com.varteq.parent.dashboard.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "journal")
public class JournalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "grade")
    private String grade;

    @OneToOne(mappedBy = "journal")
    private UserEntity user;
}
