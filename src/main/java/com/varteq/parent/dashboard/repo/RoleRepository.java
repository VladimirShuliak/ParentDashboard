package com.varteq.parent.dashboard.repo;

import com.varteq.parent.dashboard.model.RoleEntity;
import com.varteq.parent.dashboard.security.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(RoleName name);
    Set<RoleEntity> findByNameIn(List<RoleName> roleNames);
    Set<RoleEntity> findByNameNotIn(List<RoleName> excludeNames);
}
