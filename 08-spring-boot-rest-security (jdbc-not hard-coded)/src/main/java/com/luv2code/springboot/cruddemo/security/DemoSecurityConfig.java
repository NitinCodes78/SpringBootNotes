package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration

public class DemoSecurityConfig {
    //add support for JDBC ... no more hardcoded users :-)
    //Inject data source, auto-configured by spring boot( we connenect to the database in application.properties

    //Below method uses the default table names and schema i.e. uesrs and authorities table

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        //It tell Spring Security to use JDBC authentication with our data source, so spring security will look in a table called users( we have to name the table in sql as users only) for JdbcUserDetailsManager. And it will automatically look for authorise table for the users
//        return new JdbcUserDetailsManager(dataSource);
//
//    }

    //Below is the Method to define the table names and schema customizabally

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retreive a user by username

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
                //user_id will come from login form
        );
        //define query to retreive the authorities/roles by username

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );


        return jdbcUserDetailsManager;


    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(ami -> ami
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        //use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        //disable Cross Site Request Forgery(CSRF)
        //CSRF is for web applications with html forms, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());


        return http.build();

    }
}
