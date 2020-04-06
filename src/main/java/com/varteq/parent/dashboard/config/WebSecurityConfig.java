package com.varteq.parent.dashboard.config;

import com.varteq.parent.dashboard.dao.model.GradeBookEntity;
import com.varteq.parent.dashboard.dao.model.HomeWorkEntity;
import com.varteq.parent.dashboard.dao.model.UserEntity;
import com.varteq.parent.dashboard.repo.GradeBookRepository;
import com.varteq.parent.dashboard.repo.HomeWorkRepository;
import com.varteq.parent.dashboard.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HomeWorkRepository homeWorkRepository;

    @Autowired
    GradeBookRepository gradeBookRepository;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/login**")
                .permitAll()
                .mvcMatchers("/users/**", "/courses/**")
                .authenticated()
                // logoutSuccessful
                .and().logout().permitAll()
                .and().csrf().disable();

    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String id = (String) map.get("sub");
            String email = (String) map.get("email");
            UserEntity userEntity = userRepository.findByEmail(email);

            if (userEntity == null) {
                HomeWorkEntity homeWorkEntity = new HomeWorkEntity();
                homeWorkEntity.setId(10L);
                homeWorkEntity.setGrade(1);
                homeWorkEntity.setDescription("forTestAuth");
                homeWorkRepository.save(homeWorkEntity);

                GradeBookEntity gradeBookEntity = new GradeBookEntity();
                gradeBookEntity.setId(10L);
                gradeBookEntity.setHomeWork(homeWorkEntity);
                gradeBookRepository.save(gradeBookEntity);

                UserEntity user = new UserEntity();
                user.setId(id);

                Optional<HomeWorkEntity> homeWorkEntityForOp = homeWorkRepository.findById(homeWorkEntity.getId());
                Optional<GradeBookEntity> gradeBookEntityForOp = gradeBookRepository.findById(gradeBookEntity.getId());

                homeWorkEntity.setId(homeWorkEntityForOp.get().getId());
                gradeBookEntity.setId(gradeBookEntityForOp.get().getId());

                user.setHomeWork(homeWorkEntity);
                user.setGradebook(gradeBookEntity);
                user.setName((String) map.get("name"));
                user.setEmail((String) map.get("email"));

                return userRepository.save(user);
            }

            userEntity.setVisit(LocalDateTime.now());
            return userRepository.save(userEntity);
        };
    }
}
