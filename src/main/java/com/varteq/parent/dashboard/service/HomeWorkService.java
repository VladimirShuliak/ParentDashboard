package com.varteq.parent.dashboard.service;


import com.varteq.parent.dashboard.model.HomeWorkEntity;

import java.util.List;

public interface HomeWorkService {

    public List<HomeWorkEntity> findAll();

    public HomeWorkEntity load(Long homeWorkId);

    public HomeWorkEntity save(HomeWorkEntity homeWork);

    public HomeWorkEntity update(HomeWorkEntity homeWork);

    public void remove(Long homeWorkId);
}
