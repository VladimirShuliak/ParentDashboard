package com.varteq.parent.dashboard.rest;


import com.varteq.parent.dashboard.model.GradeBookEntity;
import com.varteq.parent.dashboard.serviceImpl.GradeBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gradeBooks")
public class GradeBookController {

    @Autowired
    private GradeBookServiceImpl gradeBookService;

    @GetMapping
    public List<GradeBookEntity> getAll() {
        return gradeBookService.findAll();
    }

    @GetMapping(value = "/{gradeBookId}")
    public GradeBookEntity getGradeBooks(@PathVariable Long gradeBookId) {

        return gradeBookService.load(gradeBookId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public GradeBookEntity save(@Valid @RequestBody GradeBookEntity gradeBook) {

        return gradeBookService.save(gradeBook);
    }

    @PutMapping
    public GradeBookEntity update(@Valid @RequestBody GradeBookEntity gradeBookToUpdate) {

        return gradeBookService.update(gradeBookToUpdate);
    }

    @DeleteMapping(value = "/{gradeBookId}")
    public void deleteGradeBook(@PathVariable Long gradeBookId) {

        gradeBookService.remove(gradeBookId);
    }
}
