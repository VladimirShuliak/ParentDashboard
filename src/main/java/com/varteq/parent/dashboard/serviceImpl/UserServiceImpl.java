package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.model.UserEntity;
import com.varteq.parent.dashboard.repo.UserRepository;
import com.varteq.parent.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void save(UserEntity user){
        user.setPassword(getEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserEntity get(String email) {
        return  userRepository.findByEmail(email);
    }
}
