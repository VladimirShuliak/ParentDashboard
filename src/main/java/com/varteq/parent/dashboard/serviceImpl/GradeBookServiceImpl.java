package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.model.GradeBookEntity;
import com.varteq.parent.dashboard.repo.GradeBookRepository;
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

    @Override
    public List<GradeBookEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public GradeBookEntity load(Long gradeBookId) {
        log.debug("Load gradeBook by id {}", gradeBookId);
        Optional<GradeBookEntity> gradeBook = repository.findById(gradeBookId);
        if (gradeBook == null) {
            throw new EntityNotFoundException("GradeBook doesn't exist, id " + gradeBookId);
        }
        return gradeBook.get();
    }

    @Override
    public GradeBookEntity save(GradeBookEntity gradeBook) {
        log.debug("Save gradebook {}", gradeBook);
        if (gradeBook.getId() != null && repository.existsById(gradeBook.getId())) {
            throw new EntityExistsException("Failed to save, gradebook already exists, id:" + gradeBook.getId());
        }

        GradeBookEntity gradeBookEntity = gradeBook;

        gradeBookEntity.setId(gradeBook.getId());
        gradeBookEntity.setGrade(gradeBook.getGrade());
        gradeBookEntity.setUser(gradeBook.getUser());

        repository.save(gradeBookEntity);

        return gradeBookEntity;

    }

    @Override
    public GradeBookEntity update(GradeBookEntity gradeBook) {
        Long gradeBookId = gradeBook.getId();
        log.debug("Update gradeBook by id {}", gradeBookId);

        Optional<GradeBookEntity> gradeBookEntityForId = repository.findById(gradeBookId);

        if (gradeBookId == null || !gradeBookId.equals(gradeBookEntityForId.get().getId())) {
            throw new EntityNotFoundException("Failed to update, gradeBook doesn't exist id:" + gradeBookId);
        }

        GradeBookEntity gradeBookEntity = new GradeBookEntity();

        gradeBookEntity.setId(gradeBookEntityForId.get().getId());
        gradeBookEntity.setGrade(gradeBook.getGrade());
        gradeBookEntity.setDateFrom(gradeBook.getDateFrom());
        gradeBookEntity.setDateFrom(gradeBook.getDateTo());
        gradeBookEntity.setCourses(gradeBook.getCourses());
        gradeBookEntity.setHomeWork(gradeBook.getHomeWork());

        repository.save(gradeBookEntity);

        return gradeBookEntity;
    }

    @Override
    public void remove(Long gradeBooklId) {

        log.debug("Remove user by name {}", gradeBooklId);
        repository.deleteById(gradeBooklId);
    }
}
