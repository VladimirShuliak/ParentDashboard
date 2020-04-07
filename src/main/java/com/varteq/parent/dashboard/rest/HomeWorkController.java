package com.varteq.parent.dashboard.rest;

import com.varteq.parent.dashboard.dto.HomeWorkDto;
import com.varteq.parent.dashboard.serviceImpl.HomeWorkServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/homeWorks")
public class HomeWorkController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public static final String SUCCESS = "success";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private HomeWorkServiceImpl homeWorkService;

//    @Secured({ROLE_USER})
    @GetMapping
    public List<HomeWorkDto> getAll() {
        return homeWorkService.findAll();
    }

    //    @Secured({ROLE_ADMIN})
    @GetMapping(value = "/{homeWorkId}")
    public HomeWorkDto getHomeWork(@PathVariable Long homeWorkId) {

        return homeWorkService.load(homeWorkId);
    }

//    @Secured({ROLE_ADMIN})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public HomeWorkDto save(@Valid @RequestBody HomeWorkDto homeWork) {

        return homeWorkService.save(homeWork);
    }

//    @Secured({ROLE_ADMIN})
    @PutMapping
    public HomeWorkDto update(@Valid @RequestBody HomeWorkDto homeWorkToUpdate) {

        return homeWorkService.update(homeWorkToUpdate);
    }

//    @Secured({ROLE_ADMIN})
    @DeleteMapping(value = "/{homeWorkId}")
    public void deleteHomeWork(@PathVariable Long homeWorkId) {

        homeWorkService.remove(homeWorkId);
    }
}
