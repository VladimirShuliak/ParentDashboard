package com.varteq.parent.dashboard.service;

import com.varteq.parent.dashboard.model.CourseEntity;

import java.util.List;

public interface CourseService {

    public List<CourseEntity> findAll();

    public CourseEntity load(Long courseId);

    public CourseEntity save(CourseEntity course);

    public CourseEntity update(CourseEntity course);

    public void remove(Long journalId);
}
