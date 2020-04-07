package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.dao.mapper.StudentMapper;
import com.varteq.parent.dashboard.dao.model.StudentEntity;
import com.varteq.parent.dashboard.dao.model.UserEntity;
import com.varteq.parent.dashboard.dao.repo.StudentRepository;
import com.varteq.parent.dashboard.dto.StudentDto;
import com.varteq.parent.dashboard.service.StudentService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentRepository repository;

    @Override
    public List<StudentDto> findAll() {
        return studentMapper.toDtoList(repository.findAll());
    }

    @Override
    public StudentDto load(String studentId) {
        log.debug("Load student by id {}", studentId);
        Optional<StudentEntity> student = repository.findById(studentId);
        if (student == null) {
            throw new EntityNotFoundException("Student doesn't exist, id " + studentId);
        }
        return studentMapper.toDto(student.get());
    }


    // TODO this method only for ADMIN
    @Override
    public StudentDto save(StudentDto student) {
        log.debug("Save student {}", student);
        if (student.getId() != null && repository.existsById(student.getId())) {
            throw new EntityExistsException("Failed to save, student already exists, id:" + student.getId());
        }

        StudentEntity studentEntity = repository.save(studentMapper.toEntity(student));
        return studentMapper.toDto(studentEntity);
    }

    @Override
    public StudentDto update(StudentDto student) {
        String studentId = student.getId();
        log.debug("Update student by id {}", studentId);
        Optional<StudentEntity> studentEntity = repository.findById(studentId);

        if (studentId == null || studentId == null) {
            throw new EntityNotFoundException("Failed to update, student doesn't exist id:" + studentId);
        }
        repository.save(studentMapper.toEntity(student));
        return student;

    }

    @Override
    public void remove(String studentId) {
        log.debug("Remove user by id {}", studentId);
        repository.deleteById(studentId);
    }
}
