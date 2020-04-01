package com.varteq.parent.dashboard.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(generator="system-uuid", strategy = GenerationType.AUTO)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "visit")
    private LocalDateTime visit;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<RoleEntity> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_id", referencedColumnName = "id")
    private JournalEntity journal;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", visit=" + visit +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", journal=" + journal +
                '}';
    }
}

