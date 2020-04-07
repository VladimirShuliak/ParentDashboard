package com.varteq.parent.dashboard.service;

import com.varteq.parent.dashboard.dto.StudentDto;

import java.util.List;

public interface StudentService {

    public List<StudentDto> findAll();

    public StudentDto load(String studentId);

    public StudentDto save(StudentDto student);

    public StudentDto update(StudentDto student);

    public void remove(String studentId);
}
