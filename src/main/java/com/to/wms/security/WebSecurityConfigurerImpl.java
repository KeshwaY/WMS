package com.to.wms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfigurerImpl(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .authenticationEntryPoint(getRestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                // ADDRESS
                .mvcMatchers(
                        "/api/v1/address",
                        "/api/v1/address/by-city"
                ).hasAuthority("WAREHOUSEMAN_ADDRESS")
                .mvcMatchers("/api/v1/address/**").hasAuthority("OP_ADDRESS_MANAGEMENT")
                // DEPARTMENT
                .mvcMatchers("/api/v1/departments/by-name").hasAuthority("WAREHOUSEMAN_DEPARTMENTS")
                .mvcMatchers("/api/v1/departments/**").hasAuthority("OP_DEPARTMENTS_MANAGEMENT")
                // LOCATIONS
                .mvcMatchers(
                        "/api/v1/locations/locations",
                        "/api/v1/locations/shelf",
                        "/api/v1/locations/department"
                ).hasAuthority("WAREHOUSEMAN_LOCATIONS")
                .mvcMatchers("/api/v1/locations/**").hasAuthority("OP_LOCATIONS_MANAGEMENT")
                // CATEGORY
                .mvcMatchers(
                        "/api/v1/categories/by-name",
                        "/api/v1/categories"
                ).hasAuthority("WAREHOUSE_CATEGORY")
                .mvcMatchers("/api/v1/categories/**").hasAuthority("OP_CATEGORY_MANAGEMENT")
                // PRODUCT
                .mvcMatchers(
                        "/api/v1/products/quantity/*",
                        "/api/v1/products/name",
                        "/api/v1/products/shelf",
                        "/api/v1/products/category"
                ).hasAuthority("WAREHOUSEMAN_PRODUCT")
                .mvcMatchers("/api/v1/products/**").hasAuthority("OP_PRODUCT_MANAGEMENT")
                // ROLES
                .mvcMatchers("/api/v1/roles/**").hasAuthority("OP_ROLE_MANAGEMENT")
                .mvcMatchers("/api/v1/authorities/**").hasAuthority("OP_ROLE_MANAGEMENT")
                // USERS
                .mvcMatchers(HttpMethod.POST, "/api/v1/users/add").anonymous()
                .mvcMatchers("/api/v1/users/**").hasAuthority("OP_USER_MANAGEMENT")
                // OTHERS
                .mvcMatchers("/**").permitAll()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public RestAuthenticationEntryPoint getRestAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

}
