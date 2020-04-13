package com.varteq.parent.dashboard.config;

import com.varteq.parent.dashboard.dao.model.UserEntity;
import com.varteq.parent.dashboard.dao.repo.GradeBookRepository;
import com.varteq.parent.dashboard.dao.repo.HomeWorkRepository;
import com.varteq.parent.dashboard.dao.repo.StudentRepository;
import com.varteq.parent.dashboard.dao.repo.UserRepository;
import com.varteq.parent.dashboard.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HomeWorkRepository homeWorkRepository;

    @Autowired
    GradeBookRepository gradeBookRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserServiceImpl userService;

    UserEntity userEntity;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    AdminAuthFilter adminAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/login**")
                .permitAll()
                .mvcMatchers("/users/**", "/courses/**")
                .authenticated()
//                 logoutSuccessful
                .and().logout().permitAll()
                .and()
                .addFilterBefore(adminAuthFilter, BasicAuthenticationFilter.class).authorizeRequests()
                .and().csrf().disable();

    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String id = (String) map.get("sub");
            String email = (String) map.get("email");
            UserEntity userEntity = userRepository.findByEmail(email);

            if (userEntity == null) {

                UserEntity user = new UserEntity();
                user.setId(id);

                user.setName((String) map.get("name"));
                user.setEmail((String) map.get("email"));
                user.setEmail((String) map.get("email"));

                return userRepository.save(user);
            }
            UserEntity userEntityHol = (UserEntity) userService.loadUserByUsername(userEntity.getName());
            SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(userEntityHol));

            userEntity.setVisit(LocalDateTime.now());
            return userRepository.save(userEntity);
        };
    }
}
