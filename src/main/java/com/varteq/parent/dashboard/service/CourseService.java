package com.varteq.parent.dashboard.service;

import com.varteq.parent.dashboard.dto.CourseDto;

import java.util.List;

public interface CourseService {

    public List<CourseDto> findAll();

    public CourseDto load(Long courseId);

    public CourseDto save(CourseDto course);

    public CourseDto update(CourseDto course);

    public void remove(Long journalId);
}
