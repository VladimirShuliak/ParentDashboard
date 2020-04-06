package com.varteq.parent.dashboard.rest;

import com.varteq.parent.dashboard.dto.UserDto;
import com.varteq.parent.dashboard.security.RoleName;
import com.varteq.parent.dashboard.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.varteq.parent.dashboard.rest.HomeWorkController.ROLE_USER;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Secured(ROLE_USER)
    public List<UserDto> getAll() {

        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{userId}")
    public UserDto getUser(@PathVariable String userId) {

        return userService.load(userId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto saveUser(@Valid @RequestBody UserDto user,
                            @RequestParam(name = "roleNames", required = false) List<RoleName> roleNames) {

        return userService.save(user);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public UserDto updateUser(@Valid @RequestBody UserDto userToUpdate) {

        return userService.update(userToUpdate);
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.remove(userId);
    }
}
