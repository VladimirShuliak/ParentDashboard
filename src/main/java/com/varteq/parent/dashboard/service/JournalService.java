package com.varteq.parent.dashboard.service;

import com.varteq.parent.dashboard.model.JournalEntity;
import org.springframework.stereotype.Service;

@Service
public interface JournalService {

    JournalEntity getById(Long journalId);
}
