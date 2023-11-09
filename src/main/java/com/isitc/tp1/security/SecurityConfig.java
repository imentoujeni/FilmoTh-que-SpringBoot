package com.isitc.tp1.security;

import com.isitc.tp1.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().defaultSuccessUrl("/film/all", true);
        http.logout().logoutSuccessUrl("/");
        http.authorizeHttpRequests().antMatchers("/", "/cat/**", "/details/**", "/images/**", "/photos/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/film/new/**", "/film/delete/**", "/film/edit/**").hasAuthority("ADMIN");
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.csrf().disable();
        http.exceptionHandling().accessDeniedPage("/forbidden");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder passwordEncoder = passwordEncoder();
//        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");

        auth.userDetailsService(userDetailsServiceImpl);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
