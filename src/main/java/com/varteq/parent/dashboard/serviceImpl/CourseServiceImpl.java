package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.model.CourseEntity;
import com.varteq.parent.dashboard.repo.CourseRepository;
import com.varteq.parent.dashboard.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    public List<CourseEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public CourseEntity load(Long courseId) {
        log.debug("Load course by id {}", courseId);
        Optional<CourseEntity> couse = repository.findById(courseId);
        if (couse == null) {
            throw new EntityNotFoundException("Course doesn't exist, id " + courseId);
        }
        return couse.get();
    }

    @Override
    public CourseEntity save(CourseEntity course) {
        Long courseId = course.getId();
        log.debug("Save user {}", courseId);
        Optional<CourseEntity> courseEntity = repository.findById(courseId);
        if (course.getId() != null && repository.existsById(courseId)) {
            throw new EntityExistsException("Failed to save, course already exists, id:" + course.getId());
        }

            courseEntity.get().setName(course.getName());
            courseEntity.get().setStart(course.getStart());
            courseEntity.get().setEnd(course.getEnd());
            courseEntity.get().setHomeWork(course.getHomeWork());
            courseEntity.get().setUsers(course.getUsers());
            courseEntity.get().setGradeBooks(course.getGradeBooks());

            return courseEntity.get();
        }

    @Override
    public CourseEntity update(CourseEntity course) {

            Long courseId = course.getId();
            log.debug("Update course by id {}", course.getId());

            Optional<CourseEntity> courseEntity = repository.findById(courseId);

            if (courseId == null || !courseId.equals(courseEntity.get().getId())) {
                throw new EntityNotFoundException("Failed to update, course doesn't exist id:" + courseId);
            }

        courseEntity.get().setName(course.getName());
        courseEntity.get().setStart(course.getStart());
        courseEntity.get().setEnd(course.getEnd());
        courseEntity.get().setHomeWork(course.getHomeWork());
        courseEntity.get().setUsers(course.getUsers());
        courseEntity.get().setGradeBooks(course.getGradeBooks());

            return courseEntity.get();
    }

    @Override
    public void remove(Long courseId) {
        log.debug("Remove course by id {}", courseId);
        repository.deleteById(courseId);
    }
}
