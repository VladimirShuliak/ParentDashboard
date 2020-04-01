package com.varteq.parent.dashboard.serviceImpl;

import com.varteq.parent.dashboard.model.JournalEntity;
import com.varteq.parent.dashboard.repo.JournalRepository;
import com.varteq.parent.dashboard.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    public JournalEntity getById(Long journalId) {

        Optional<JournalEntity> userEntity = journalRepository.findById(journalId);
        if (userEntity.isPresent()) {
            return userEntity.get();
        } else {
            throw new EntityNotFoundException("Journal doesn't exist, id: " + journalId);
        }
    }
}
