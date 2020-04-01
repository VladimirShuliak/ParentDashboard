package com.varteq.parent.dashboard.rest;

import com.varteq.parent.dashboard.model.JournalEntity;
import com.varteq.parent.dashboard.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping(value = "/{journalId}")
    public JournalEntity getJournal(@PathVariable Long journalId){
        return journalService.getById(journalId);
    }
}
