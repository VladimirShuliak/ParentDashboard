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
            throw new EntityNotFoundException("Journal doesn't exist, id " + gradeBookId);
        }
        return gradeBook.get();
    }

    @Override
    public GradeBookEntity save(GradeBookEntity gradebook) {
        log.debug("Save gradebook {}", gradebook);
        if (gradebook.getId() != null && repository.existsById(gradebook.getId())) {
            throw new EntityExistsException("Failed to save, gradebook already exists, id:" + gradebook.getId());
        }

        GradeBookEntity gradeBookEntity = gradebook;

        gradeBookEntity.setId(gradebook.getId());
        gradeBookEntity.setGrade(gradebook.getGrade());
        gradeBookEntity.setUser(gradebook.getUser());

        return gradeBookEntity;

    }

    @Override
    public GradeBookEntity update(GradeBookEntity gradeBook) {
        Long journalId = gradeBook.getId();
        log.debug("Update gradeBook by id {}", journalId);

        Optional<GradeBookEntity> gradeBookEntity = repository.findById(journalId);

        if (journalId == null || !journalId.equals(gradeBookEntity.get().getId())) {
            throw new EntityNotFoundException("Failed to update, gradeBook doesn't exist id:" + journalId);
        }

        gradeBookEntity.get().setUser(gradeBook.getUser());
        gradeBookEntity.get().setGrade(gradeBook.getGrade());

        return gradeBookEntity.get();
    }

    @Override
    public void remove(Long gradeBooklId) {

        log.debug("Remove user by name {}", gradeBooklId);
        repository.deleteById(gradeBooklId);
    }
}
