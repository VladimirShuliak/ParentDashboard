package com.varteq.parent.dashboard.service;

import com.varteq.parent.dashboard.model.UserEntity;
import com.varteq.parent.dashboard.security.RoleName;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<UserEntity> findAll();

    public UserEntity load(Long userId);

    public UserEntity save(UserEntity user, List<RoleName> roleNames);

    public UserEntity update(UserEntity user);

    public void remove(Long id);
}
