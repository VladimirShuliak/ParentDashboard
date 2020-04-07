package com.varteq.parent.dashboard.dao.repo;

import com.varteq.parent.dashboard.dao.model.GradeBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeBookRepository extends JpaRepository<GradeBookEntity, Long> {
}
