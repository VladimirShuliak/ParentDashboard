package com.varteq.parent.dashboard.rest;

import com.varteq.parent.dashboard.dto.CourseDto;
import com.varteq.parent.dashboard.serviceImpl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;


    @GetMapping
    public List<CourseDto> getAll() {

        return courseService.findAll();
    }

    @GetMapping(value = "/{courseId}")
    public CourseDto getUser(@PathVariable Long userId) {

        return courseService.load(userId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CourseDto save(@Valid @RequestBody CourseDto course) {

        return courseService.save(course);
    }

    @PutMapping
    public CourseDto updateUser(@Valid @RequestBody CourseDto courseToUpdate) {

        return courseService.update(courseToUpdate);
    }

    @DeleteMapping(value = "/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        courseService.remove(courseId);
    }
}
