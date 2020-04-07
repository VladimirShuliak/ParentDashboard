package com.varteq.parent.dashboard.service;

import com.varteq.parent.dashboard.dto.ParentDto;

import java.util.List;

public interface ParentService {

    public List<ParentDto> findAll();

    public ParentDto findByEmail(String parent);

    public ParentDto load(String parentId);

    public ParentDto save(ParentDto parent);

    public ParentDto update(ParentDto parent);

    public void remove(String parentId);
}
