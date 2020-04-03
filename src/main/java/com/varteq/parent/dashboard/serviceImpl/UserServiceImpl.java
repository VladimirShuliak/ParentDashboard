package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.model.HomeWorkEntity;
import com.varteq.parent.dashboard.model.RoleEntity;
import com.varteq.parent.dashboard.model.UserEntity;
import com.varteq.parent.dashboard.repo.HomeWorkRepository;
import com.varteq.parent.dashboard.repo.RoleRepository;
import com.varteq.parent.dashboard.repo.UserRepository;
import com.varteq.parent.dashboard.security.RoleName;
import com.varteq.parent.dashboard.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository repository;

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserEntity load(String userId) {
        log.debug("Load user by id {}", userId);
        Optional<UserEntity> user = repository.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User doesn't exist, id " + userId);
        }
        return user.get();
    }

    @Override
    public UserEntity save(UserEntity user, List<RoleName> roleNames) {
        log.debug("Save user {}", user);
        if (user.getId() != null && repository.existsById(user.getId())) {
            throw new EntityExistsException("Failed to save, user already exists, id:" + user.getId());
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setEmail(user.getEmail());
        userEntity.setVisit(java.time.LocalDateTime.now());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRoles(getAvailableRoleEntity(roleNames));
        userEntity.setGradebook(user.getGradebook());
        userEntity.setCourses(user.getCourses());
        userEntity.setHomeWork(user.getHomeWork());

        repository.save(userEntity);

        return userEntity;
    }

    @Override
    public UserEntity update(UserEntity user) {
        String userId = user.getId();
        log.debug("Update user by id {}", userId);

        Optional<UserEntity> userEntityForId = repository.findById(userId);

        if (userId == null || !userId.equals(userEntityForId.get().getId())) {
            throw new EntityNotFoundException("Failed to update, user doesn't exist id:" + userId);
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userEntityForId.get().getId());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setEmail(user.getEmail());
        userEntity.setVisit(java.time.LocalDateTime.now());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRoles(user.getRoles());
        userEntity.setGradebook(user.getGradebook());
        userEntity.setCourses(user.getCourses());
        userEntity.setHomeWork(user.getHomeWork());

        repository.save(userEntity);

        return userEntity;
    }

    @Override
    public void remove(String userId) {
        log.debug("Remove user by id {}", userId);
        repository.deleteById(userId);
    }

    private Set<RoleEntity> getAvailableRoleEntity(List<RoleName> roleNames) {
        Set<RoleEntity> roleEntity;

        if (roleNames != null && !roleNames.isEmpty()) {
            roleEntity = roleRepository.findByNameIn(roleNames);
        } else {
            roleEntity = Collections.singleton(roleRepository.findByName(RoleName.ROLE_USER));
        }

        if (roleEntity.isEmpty()) {
            throw new InvalidRequestException(String.format("Cannot create user for such roles: %s", roleNames));
        }
        return roleEntity;
    }
}
