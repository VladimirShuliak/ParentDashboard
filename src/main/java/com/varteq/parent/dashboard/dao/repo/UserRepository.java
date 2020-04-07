package com.varteq.parent.dashboard.dao.repo;

import com.varteq.parent.dashboard.dao.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

    UserEntity findByName(String name);

}
