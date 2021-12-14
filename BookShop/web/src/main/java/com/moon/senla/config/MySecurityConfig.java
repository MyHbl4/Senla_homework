package com.moon.senla.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
            .withUser(userBuilder.username("user")
                .password("user")
                .roles("USER"))
            .withUser(userBuilder.username("admin")
                .password("admin")
                .roles("ADMIN"));
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//            .dataSource(dataSource)
//            .passwordEncoder(NoOpPasswordEncoder.getInstance())
//            .usersByUsernameQuery("select username, password, active from usr where username=?")
//            .authoritiesByUsernameQuery(
//                "select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").hasAnyRole("USER", "ADMIN")
            .antMatchers("/orders/").hasAnyRole("USER", "ADMIN")
            .antMatchers("/books/").hasAnyRole("USER", "ADMIN")
            .antMatchers("/orders/new").hasAnyRole("USER", "ADMIN")
            .antMatchers("/books/sort/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/orders/**").hasRole("ADMIN")
            .antMatchers("/requests/**").hasRole("ADMIN")
            .antMatchers("/books/**").hasRole("ADMIN")
            .and()
            .formLogin()
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
