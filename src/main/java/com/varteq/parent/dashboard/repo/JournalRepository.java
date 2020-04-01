package com.varteq.parent.dashboard.repo;

import com.varteq.parent.dashboard.model.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends JpaRepository<JournalEntity, Long> {
}
