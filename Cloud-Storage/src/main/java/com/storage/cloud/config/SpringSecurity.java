package com.storage.cloud.config;

import com.storage.cloud.service.AuthenticationService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Component
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    public SpringSecurity(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    public void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(this.authenticationService);
    }

    public void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests().antMatchers("/signup","/js/**","/css/**").
                permitAll().anyRequest().authenticated();

        http.formLogin().loginPage("/login").permitAll();

        http.formLogin().defaultSuccessUrl("/home",true);

    }


}
