package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.dao.mapper.GradeBookMapper;
import com.varteq.parent.dashboard.dao.model.GradeBookEntity;
import com.varteq.parent.dashboard.dto.GradeBookDto;
import com.varteq.parent.dashboard.dao.repo.GradeBookRepository;
import com.varteq.parent.dashboard.service.GradeBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GradeBookServiceImpl implements GradeBookService {

    @Autowired
    private GradeBookRepository repository;

    @Autowired
    private GradeBookMapper gradeBookMapper;

    @Override
    public List<GradeBookDto> findAll() {
        return gradeBookMapper.toDtoList(repository.findAll());
    }

    @Override
    public GradeBookDto load(Long gradeBookId) {
        log.debug("Load gradeBook by id {}", gradeBookId);
        Optional<GradeBookEntity> gradeBook = repository.findById(gradeBookId);
        if (gradeBook == null) {
            throw new EntityNotFoundException("GradeBook doesn't exist, id " + gradeBookId);
        }
        return gradeBookMapper.toDto(gradeBook.get());
    }

    @Override
    public GradeBookDto save(GradeBookDto gradeBook) {
        log.debug("Save gradebook {}", gradeBook);
        if (gradeBook.getId() != null && repository.existsById(gradeBook.getId())) {
            throw new EntityExistsException("Failed to save, gradebook already exists, id:" + gradeBook.getId());
        }

        GradeBookEntity gradeBookEntity = repository.save(gradeBookMapper.toEntity(gradeBook));
        return gradeBookMapper.toDto(gradeBookEntity);
    }

    @Override
    public GradeBookDto update(GradeBookDto gradeBook) {
        Long gradeBookId = gradeBook.getId();
        log.debug("Update gradeBook by id {}", gradeBookId);

        Optional<GradeBookEntity> gradeBookEntityForId = repository.findById(gradeBookId);

        if (gradeBookId == null || !gradeBookId.equals(gradeBookEntityForId.get().getId())) {
            throw new EntityNotFoundException("Failed to update, gradeBook doesn't exist id:" + gradeBookId);
        }

        GradeBookEntity gradeBookEntity = repository.save(gradeBookMapper.toEntity(gradeBook));
        return gradeBookMapper.toDto(gradeBookEntity);
    }

    @Override
    public void remove(Long gradeBooklId) {
        log.debug("Remove user by name {}", gradeBooklId);
        repository.deleteById(gradeBooklId);
    }
}
