package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.model.CourseEntity;
import com.varteq.parent.dashboard.model.HomeWorkEntity;
import com.varteq.parent.dashboard.repo.HomeWorkRepository;
import com.varteq.parent.dashboard.service.HomeWorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HomeWorkServiceImpl implements HomeWorkService {

    @Autowired
    private HomeWorkRepository repository;

    @Override
    public List<HomeWorkEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public HomeWorkEntity load(Long homeWorkId) {
        log.debug("Load homeWork by id {}", homeWorkId);
        Optional<HomeWorkEntity> homeWork = repository.findById(homeWorkId);
        if (homeWork == null) {
            throw new EntityNotFoundException("HomeWork doesn't exist, id " + homeWorkId);
        }
        return homeWork.get();
    }

    @Override
    public HomeWorkEntity save(HomeWorkEntity homeWork) {
        Long homeWorkId = homeWork.getId();
        log.debug("Save add home work {}", homeWorkId);
        Optional<HomeWorkEntity> homeWorkEntity = repository.findById(homeWorkId);
        if (homeWork.getId() != null && repository.existsById(homeWorkId)) {
            throw new EntityExistsException("Failed to save, course already exists, id:" + homeWork.getId());
        }

        homeWorkEntity.get().setDescription(homeWork.getDescription());
        homeWorkEntity.get().setUsers(homeWork.getUsers());
        homeWorkEntity.get().setCourses(homeWork.getCourses());
        homeWorkEntity.get().setGradeBooks(homeWork.getGradeBooks());

        return homeWorkEntity.get();
    }

    @Override
    public HomeWorkEntity update(HomeWorkEntity homeWork) {
        Long homeWorkId = homeWork.getId();
        log.debug("Update home Work by id {}", homeWork.getId());

        Optional<HomeWorkEntity> homeWorkEntity = repository.findById(homeWorkId);

        if (homeWorkId == null || !homeWorkId.equals(homeWorkEntity.get().getId())) {
            throw new EntityNotFoundException("Failed to update, course doesn't exist id:" + homeWorkId);
        }

        homeWorkEntity.get().setDescription(homeWork.getDescription());
        homeWorkEntity.get().setUsers(homeWork.getUsers());
        homeWorkEntity.get().setCourses(homeWork.getCourses());
        homeWorkEntity.get().setGradeBooks(homeWork.getGradeBooks());

        return homeWorkEntity.get();
    }

    @Override
    public void remove(Long homeWorkId) {

        log.debug("Remove home work by id {}", homeWorkId);
        repository.deleteById(homeWorkId);
    }
}
