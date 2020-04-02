package com.varteq.parent.dashboard.service;

import com.varteq.parent.dashboard.model.GradeBookEntity;

import java.util.List;

public interface GradeBookService {

    public List<GradeBookEntity> findAll();

    public GradeBookEntity load(Long journalId);

    public GradeBookEntity save(GradeBookEntity user);

    public GradeBookEntity update(GradeBookEntity user);

    public void remove(Long journalId);
}
