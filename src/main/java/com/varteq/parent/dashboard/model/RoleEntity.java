package com.varteq.parent.dashboard.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="roles")
public class RoleEntity {

    @Id
    @GeneratedValue(generator="system-uuid", strategy = GenerationType.AUTO)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String Id;


    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<UserEntity> users;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Set<PrivilegeEntity> privileges = new HashSet();
}
