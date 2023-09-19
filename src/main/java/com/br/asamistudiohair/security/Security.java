package com.br.asamistudiohair.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                                .requestMatchers("/public/**").permitAll()
                                .requestMatchers("/user/**").hasAnyRole()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(withDefaults());

        return http.build();
    }
}
