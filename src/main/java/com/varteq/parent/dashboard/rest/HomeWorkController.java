package com.varteq.parent.dashboard.rest;

import com.varteq.parent.dashboard.model.HomeWorkEntity;
import com.varteq.parent.dashboard.serviceImpl.HomeWorkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/homeWorks")
public class HomeWorkController {

    @Autowired
    private HomeWorkServiceImpl homeWorkService;

    @GetMapping
    public List<HomeWorkEntity> getAll() {
        return homeWorkService.findAll();
    }

    @GetMapping(value = "/{homeWorkId}")
    public HomeWorkEntity getHomeWork(@PathVariable Long homeWorkId) {

        return homeWorkService.load(homeWorkId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public HomeWorkEntity save(@Valid @RequestBody HomeWorkEntity homeWork) {

        return homeWorkService.save(homeWork);
    }

    @PutMapping
    public HomeWorkEntity update(@Valid @RequestBody HomeWorkEntity homeWorkToUpdate) {

        return homeWorkService.update(homeWorkToUpdate);
    }

    @DeleteMapping(value = "/{homeWorkId}")
    public void deleteJournal(@PathVariable Long gradeBookId) {

        homeWorkService.remove(gradeBookId);
    }
}
