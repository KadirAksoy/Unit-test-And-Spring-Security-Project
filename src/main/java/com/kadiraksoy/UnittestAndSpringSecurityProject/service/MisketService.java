package com.kadiraksoy.UnittestAndSpringSecurityProject.service;


import com.kadiraksoy.UnittestAndSpringSecurityProject.repository.MisketRepository;
import org.springframework.stereotype.Service;

@Service
public class MisketService {

    private final MisketRepository misketRepository;

    public MisketService(MisketRepository misketRepository) {
        this.misketRepository = misketRepository;
    }



}
