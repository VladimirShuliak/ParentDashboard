package com.varteq.parent.dashboard.config;

import com.varteq.parent.dashboard.dao.model.UserEntity;
import com.varteq.parent.dashboard.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class AdminAuthFilter extends GenericFilterBean {

    @Autowired
    private UserServiceImpl userServiceImpl;

    private UserEntity userEntity;

    @PostConstruct
    public void init(){
            userEntity = (UserEntity) userServiceImpl.loadUserByUsername("Vladimir Shuliak");
        }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(userEntity));

        chain.doFilter(request, response);
    }
}
