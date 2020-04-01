package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.model.UserEntity;
import com.varteq.parent.dashboard.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService  {

    private UserRepository userRepository;

    private UserEntity userEntity;

    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userEntity = userRepository.findByEmail(username);
        if (userEntity != null) {
            return new CustomUserPrincipal(userEntity);
        } else {
            throw new EntityNotFoundException("User doesn't exist, email: " + username);
        }
    }
}
