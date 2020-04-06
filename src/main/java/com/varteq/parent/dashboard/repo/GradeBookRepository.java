package com.varteq.parent.dashboard.repo;

import com.varteq.parent.dashboard.dao.model.GradeBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeBookRepository extends JpaRepository<GradeBookEntity, Long> {
}
