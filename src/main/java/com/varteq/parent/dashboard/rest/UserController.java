package com.varteq.parent.dashboard.rest;

import com.varteq.parent.dashboard.model.UserEntity;
import com.varteq.parent.dashboard.security.RoleName;
import com.varteq.parent.dashboard.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserEntity> getAll() {

        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{userId}")
    public UserEntity getUser(@PathVariable String userId) {

        return userService.load(userId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserEntity saveUser(@Valid @RequestBody UserEntity user,
                               @RequestParam(name = "roleNames", required = false) List<RoleName> roleNames) {

        return userService.save(user, roleNames);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public UserEntity updateUser(@Valid @RequestBody UserEntity userToUpdate) {

        return userService.update(userToUpdate);
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.remove(userId);
    }
}
