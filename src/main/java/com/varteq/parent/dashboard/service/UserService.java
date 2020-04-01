package com.varteq.parent.dashboard.service;

import com.varteq.parent.dashboard.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity get(String userId);
}
