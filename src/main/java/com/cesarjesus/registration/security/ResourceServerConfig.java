package com.cesarjesus.registration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http){
        try {
            http.authorizeRequests()
                    .antMatchers("/oauth/**", "/registration/**").permitAll()
                    .anyRequest().authenticated();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}