package com.varteq.parent.dashboard.repo;

import com.varteq.parent.dashboard.model.HomeWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeWorkRepository extends JpaRepository<HomeWorkEntity, Long> {
}
