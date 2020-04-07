package com.varteq.parent.dashboard.dao.repo;

import com.varteq.parent.dashboard.dao.model.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository <ParentEntity, String> {
}
