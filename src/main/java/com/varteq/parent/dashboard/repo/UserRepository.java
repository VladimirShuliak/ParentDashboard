package com.varteq.parent.dashboard.repo;

import com.varteq.parent.dashboard.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String email);
}
