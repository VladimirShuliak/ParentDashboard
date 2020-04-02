package com.varteq.parent.dashboard.repo;

import com.varteq.parent.dashboard.model.GradeBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeBookRepository  extends JpaRepository<GradeBookEntity, Long> {
}
