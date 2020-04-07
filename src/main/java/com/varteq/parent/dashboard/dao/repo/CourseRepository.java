package com.varteq.parent.dashboard.dao.repo;

import com.varteq.parent.dashboard.dao.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
