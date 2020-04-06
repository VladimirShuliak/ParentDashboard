package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.dao.mapper.UserEntityMapper;
import com.varteq.parent.dashboard.dao.model.Role;
import com.varteq.parent.dashboard.dao.model.UserEntity;
import com.varteq.parent.dashboard.dto.UserDto;
import com.varteq.parent.dashboard.repo.RoleRepository;
import com.varteq.parent.dashboard.repo.UserRepository;
import com.varteq.parent.dashboard.security.RoleName;
import com.varteq.parent.dashboard.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private UserRepository repository;


    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        UserDto user = userEntityMapper.toDto(repository.findByName(name));
        if (user == null) {
            log.error("Invalid username or password.");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set grantedAuthorities = getAuthorities(user);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);
    }

    private Set<GrantedAuthority> getAuthorities(UserDto user) {
        Set<Role> roleByUserId = user.getRoles();
        final Set<GrantedAuthority> authorities = roleByUserId.stream().map(role -> new SimpleGrantedAuthority(role.getName().toString().toUpperCase())).collect(Collectors.toSet());
        return authorities;
    }

    @Override
    public List<UserDto> findAll() {
        return userEntityMapper.toDtoList(repository.findAll());
    }

    @Override
    public UserDto findByEmail(String email) {
        log.debug("Load user by email {}", email);
        UserEntity user = repository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User doesn't exist, email " + email);
        }
        return userEntityMapper.toDto(user);
    }

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto load(String userId) {
        log.debug("Load user by id {}", userId);
        Optional<UserEntity> user = repository.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User doesn't exist, id " + userId);
        }
        return userEntityMapper.toDto(user.get());
    }

    @Override
    public UserDto save(UserDto user) {
        log.debug("Save user {}", user);
        if (user.getId() != null && repository.existsById(user.getId())) {
            throw new EntityExistsException("Failed to save, user already exists, id:" + user.getId());
        }

        UserEntity userEntity = repository.save(userEntityMapper.toEntity(user));
        return userEntityMapper.toDto(userEntity);
    }

    @Override
    public UserDto update(UserDto user) {
        String userId = user.getId();
        log.debug("Update user by id {}", userId);

        Optional<UserEntity> userEntityForId = repository.findById(userId);

        if (userId == null || !userId.equals(userEntityForId.get().getId())) {
            throw new EntityNotFoundException("Failed to update, user doesn't exist id:" + userId);
        }

        UserEntity userEntity = new UserEntity();
        repository.save(userEntityMapper.toEntity(user));
        return userEntityMapper.toDto(userEntity);
    }

    @Override
    public void remove(String userId) {
        log.debug("Remove user by id {}", userId);
        repository.deleteById(userId);
    }

    private Set<Role> getAvailableRoleEntity(Set<Role> roleNames) {
        Set<Role> roleTypeEntity;

        if (roleNames != null && !roleNames.isEmpty()) {
            roleTypeEntity = roleRepository.findByNameIn(roleNames);
        } else {
            roleTypeEntity = Collections.singleton(roleRepository.findByName(RoleName.ROLE_USER));
        }

        if (roleTypeEntity.isEmpty()) {
            throw new InvalidRequestException(String.format("Cannot create user for such roles: %s", roleNames));
        }
        return roleTypeEntity;
    }
}
