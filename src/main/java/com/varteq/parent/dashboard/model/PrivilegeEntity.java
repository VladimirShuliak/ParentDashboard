package com.varteq.parent.dashboard.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="privileges")
public class PrivilegeEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "privileges", cascade = {CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<RoleEntity> roles = new HashSet<>();
}
