package com.varteq.parent.dashboard.service;

import com.varteq.parent.dashboard.dto.GradeBookDto;

import java.util.List;

public interface GradeBookService {

    public List<GradeBookDto> findAll();

    public GradeBookDto load(Long id);

    public GradeBookDto save(GradeBookDto user);

    public GradeBookDto update(GradeBookDto user);

    public void remove(Long Id);
}
