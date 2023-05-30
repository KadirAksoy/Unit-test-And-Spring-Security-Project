package com.kadiraksoy.UnittestAndSpringSecurityProject.service;

import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Role;
import com.kadiraksoy.UnittestAndSpringSecurityProject.model.User;

import com.kadiraksoy.UnittestAndSpringSecurityProject.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //The loadUserByUsername method retrieves a user from the UserRepository based on the provided username.
    // If the user is not found, it throws a UsernameNotFoundException.
    // If the user is found, a UserDetails object is created and returned.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Username not found"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(mapRolesToAuthorities(user.getRoles()))
                .build();
    }


    //The method mapRolesToAuthorities is used to convert the user's roles into a list of GrantedAuthority.
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
