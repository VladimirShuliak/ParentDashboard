package com.varteq.parent.dashboard.repo;

import com.varteq.parent.dashboard.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
