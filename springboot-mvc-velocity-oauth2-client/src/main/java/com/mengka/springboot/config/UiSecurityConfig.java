package com.mengka.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableOAuth2Sso
@Configuration
public class UiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${auth-server}/oauth/exit")
    private String logoutUrl;

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/**")
//            .authorizeRequests()
//            .antMatchers("/", "/login**")
//            .permitAll()
//            .anyRequest()
//            .authenticated();
            http.logout()
                .logoutSuccessUrl(logoutUrl)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

}
