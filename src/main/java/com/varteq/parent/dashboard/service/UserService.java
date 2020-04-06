package com.varteq.parent.dashboard.service;

//import com.varteq.parent.dashboard.dao.model.UserEntity;

import com.varteq.parent.dashboard.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<UserDto> findAll();

    public UserDto findByEmail(String email);

    public UserDto load(String userId);

    public UserDto save(UserDto user);

    public UserDto update(UserDto user);

    public void remove(String id);
}
