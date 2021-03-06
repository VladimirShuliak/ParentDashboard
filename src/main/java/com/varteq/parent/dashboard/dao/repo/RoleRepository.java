package com.varteq.parent.dashboard.dao.repo;

import com.varteq.parent.dashboard.dao.model.Role;
import com.varteq.parent.dashboard.security.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName name);

    Set<Role> findByNameIn(Set<Role> roleNames);
}
