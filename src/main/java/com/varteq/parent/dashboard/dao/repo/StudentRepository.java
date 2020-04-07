package com.varteq.parent.dashboard.dao.repo;

import com.varteq.parent.dashboard.dao.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, String> {
}
