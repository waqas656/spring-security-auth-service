package com.springsecurityauth.springsecurityauthservice.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    //In-memory Authentication
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.builder()
                .username("waqas")
                .password(new BCryptPasswordEncoder().encode("new"))
                .roles("USER") //registering USER role
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("new"))
                .roles("ADMIN") //registering ADMIN role
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeReqs ->
                        authorizeReqs
                                .antMatchers("/admin").hasRole("ADMIN") // '/admin' can be accessed by ADMIN only
                                .antMatchers("/user").hasAnyRole("USER", "ADMIN")// '/user' can be accessed by USER & ADMIN
                                .antMatchers("/").permitAll() // '/' can be accessed by anyone
                )
                .formLogin();

        return http.build();
    }


    //for password encoding
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
