package com.example.InfoSystem.config;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

//import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
////                                .requestMatchers("/public/**").permitAll()
////                                .requestMatchers("/employee/**").hasRole("DEVELOPER")
//                                .requestMatchers("/employee/getAll/").hasRole("BOSS")
//                                .requestMatchers("/department/getAll/**").hasRole("BOSS")
////                                .anyRequest().anonymous()
//                                .anyRequest().authenticated()
//                )
//                .formLogin().disable()
//                .logout(withDefaults());
//
//        return http.build();
//    }


//
//   @Bean
//    public UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.withDefaultPasswordEncoder()
//                        .username("boss")
//                        .password("password")
//                        .roles("BOSS")
//                        .build(),
//                User.withDefaultPasswordEncoder()
//                        .username("employee")
//                        .password("password")
//                        .roles("EMPLOYEE")
//                        .build()
//        );
//    }


}



