package com.varteq.parent.dashboard.dao.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType name;
    @Column(name = "description")
    private String description;
    @Column(name = "created_on")
    private Long createdOn;
    @Column(name = "modified_on")
    private Long modifiedOn;
}
