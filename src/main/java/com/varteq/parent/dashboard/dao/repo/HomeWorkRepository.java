package com.varteq.parent.dashboard.dao.repo;

import com.varteq.parent.dashboard.dao.model.HomeWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeWorkRepository extends JpaRepository<HomeWorkEntity, Long> {
}
