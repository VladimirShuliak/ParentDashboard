package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.dao.mapper.HomeWorkMapper;
import com.varteq.parent.dashboard.dao.model.HomeWorkEntity;
import com.varteq.parent.dashboard.dao.repo.HomeWorkRepository;
import com.varteq.parent.dashboard.dto.HomeWorkDto;
import com.varteq.parent.dashboard.service.HomeWorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HomeWorkServiceImpl implements HomeWorkService {

    @Autowired
    private HomeWorkRepository repository;

    @Autowired
    private HomeWorkMapper homeWorkMapper;

    @Override
//    @Secured({"ROLE_USER"})
    public List<HomeWorkDto> findAll() {
        return homeWorkMapper.toDtoList(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
//    @Secured({"ROLE_ADMIN"})
    public HomeWorkDto load(Long homeWorkId) {
        log.debug("Load homeWork by id {}", homeWorkId);
        Optional<HomeWorkEntity> homeWork = repository.findById(homeWorkId);
        if (homeWork == null) {
            throw new EntityNotFoundException("HomeWork doesn't exist, id " + homeWorkId);
        }
        return homeWorkMapper.toDto(homeWork.get());
    }

    @Override
    public HomeWorkDto save(HomeWorkDto homeWork) {
        Long homeWorkId = homeWork.getId();
        log.debug("Save add home work {}", homeWorkId);
        if (homeWork.getId() != null && repository.existsById(homeWorkId)) {
            throw new EntityExistsException("Failed to save, course already exists, id:" + homeWork.getId());
        }

        HomeWorkEntity homeWorkEntity = repository.save(homeWorkMapper.toEntity(homeWork));
        return homeWorkMapper.toDto(homeWorkEntity);
    }

    @Override
    public HomeWorkDto update(HomeWorkDto homeWork) {
        Long homeWorkId = homeWork.getId();
        log.debug("Update home Work by id {}", homeWork.getId());

        Optional<HomeWorkEntity> homeWorkForId = repository.findById(homeWorkId);

        if (homeWorkId == null || !homeWorkId.equals(homeWorkForId.get().getId())) {
            throw new EntityNotFoundException("Failed to update, home work doesn't exist id:" + homeWorkId);
        }

        HomeWorkEntity homeWorkEntity = repository.save(homeWorkMapper.toEntity(homeWork));
        return homeWorkMapper.toDto(homeWorkEntity);
    }

    @Override
    public void remove(Long homeWorkId) {
        log.debug("Remove home work by id {}", homeWorkId);
        repository.deleteById(homeWorkId);
    }
}
