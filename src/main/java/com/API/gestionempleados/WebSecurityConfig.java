package com.API.gestionempleados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails usuario1= User
                                .withUsername("fernando")
                                .password("$2a$10$wnJdYMdh0UMXw35pjnO3ge6MenE6U2iPipbLcDvkf/UdHXf1fb7Ba")
                                .roles("USER")
                                .build();

        UserDetails usuario2= User
                                .withUsername("admin")
                                .password("$2a$10$RW0XCNBYj0qUUXcIbxw53..YwGVMkEt/GGAHQj1W0grOWNBFk0RIi")
                                .roles("ADMIN")
                                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/registrarempleado/*", "/eliminarempleado/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                        .loginPage("/login")
                        .permitAll()
                .and()
                .logout().permitAll();

        http.headers().frameOptions().sameOrigin();
        return http.build();
    }
    /**
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
    **/
}
