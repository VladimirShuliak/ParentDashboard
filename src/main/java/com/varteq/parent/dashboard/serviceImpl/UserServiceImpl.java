package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.model.RoleEntity;
import com.varteq.parent.dashboard.model.UserEntity;
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

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public UserEntity load(Long userId) {
        log.debug("Load user by id {}", userId);
        Optional<UserEntity> user = repository.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User doesn't exist, id " + userId);
        }
        return user.get();
    }

    @Override
    public UserEntity save(UserEntity user, List<RoleName> roleNames) {
        Long userId = user.getId();
        log.debug("Save user {}", user);
        if (user.getId() != null && repository.existsById(userId)) {
            throw new EntityExistsException("Failed to save, user already exists, id:" + user.getId());
        }

        Optional<UserEntity> userEntity = repository.findById(user.getId());

        Set<RoleEntity> roleEntity = getAvailableRoleEntity(roleNames);
        userEntity.get().setRoles(roleEntity);
        userEntity.get().setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.get().setEmail(user.getEmail());
        userEntity.get().setName(user.getName());
//        user.isStudent(user.getIsStudent);
//        user.isParent(user.getIsParent);
//        userEntity.setJournal(user.getJournal());

        return userEntity.get();
    }

    @Override
    public UserEntity update(UserEntity user) {
        Long userId = user.getId();
        log.debug("Update user by id {}", userId);

        Optional<UserEntity> userEntity = repository.findById(userId);

        if (userId == null || !userId.equals(userEntity.get().getId())) {
            throw new EntityNotFoundException("Failed to update, user doesn't exist id:" + userId);
        }

        userEntity.get().setName(user.getName());
        userEntity.get().setSurname(user.getSurname());
        userEntity.get().setPassword(user.getPassword());
        userEntity.get().setEmail(user.getEmail());

        return userEntity.get();
    }

    @Override
    public void remove(Long userId) {
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
