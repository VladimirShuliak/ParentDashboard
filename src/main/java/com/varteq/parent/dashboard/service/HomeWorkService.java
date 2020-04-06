package com.varteq.parent.dashboard.service;


import com.varteq.parent.dashboard.dto.HomeWorkDto;

import java.util.List;

public interface HomeWorkService {

    public List<HomeWorkDto> findAll();

    public HomeWorkDto load(Long homeWorkId);

    public HomeWorkDto save(HomeWorkDto homeWork);

    public HomeWorkDto update(HomeWorkDto homeWork);

    public void remove(Long homeWorkId);
}
