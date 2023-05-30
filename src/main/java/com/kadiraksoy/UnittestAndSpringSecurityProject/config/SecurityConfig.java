package com.kadiraksoy.UnittestAndSpringSecurityProject.config;


import com.kadiraksoy.UnittestAndSpringSecurityProject.security.JwtAuthEntryPoint;
import com.kadiraksoy.UnittestAndSpringSecurityProject.security.JwtAuthenticationFilter;
import com.kadiraksoy.UnittestAndSpringSecurityProject.service.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableAutoConfiguration
public class SecurityConfig {

    private final JwtAuthEntryPoint authEntryPoint;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JwtAuthEntryPoint authEntryPoint, CustomUserDetailsService customUserDetailsService) {
        this.authEntryPoint = authEntryPoint;
        this.customUserDetailsService = customUserDetailsService;
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(authEntryPoint)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/auth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        return httpSecurity.build();
//    }

    //authenticationManager: A @Bean method used to configure the AuthenticationManager object.
    // It accesses the configuration settings using the authenticationConfiguration parameter
    // and returns an AuthenticationManager object.
    // The AuthenticationManager is a Spring Security component that manages authentication processes.
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //passwordEncoder: A @Bean method used to properly store and verify passwords.
    // It returns an instance of BCryptPasswordEncoder class, which hashes and verifies passwords using the BCrypt algorithm.
    // This ensures secure storage and authentication of user passwords.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //jwtAuthenticationFilter: A @Bean method that returns an instance of JwtAuthenticationFilter for JWT-based authentication.
    // The JwtAuthenticationFilter is a Spring Security filter that processes incoming requests for JWT authentication,
    // and if authentication is successful, it populates the security context with the authenticated identity.
    // By adding this filter to the security configuration, it enables JWT-based authentication.
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
