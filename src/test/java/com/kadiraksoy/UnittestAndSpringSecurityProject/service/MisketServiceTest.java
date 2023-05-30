package com.kadiraksoy.UnittestAndSpringSecurityProject.service;

import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.MisketDto;
import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Misket;
import com.kadiraksoy.UnittestAndSpringSecurityProject.repository.MisketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


// The annotation @ExtendWith(MockitoExtension.class)
// enables the necessary JUnit extension to use the Mockito library in JUnit 5 tests.
@ExtendWith(MockitoExtension.class)
class MisketServiceTest {

    @Mock
    private MisketRepository misketRepository;
    @InjectMocks
    private MisketService misketService;

    // We can also do it this way.
//    private MisketService misketService;
//    private MisketRepository misketRepository;
//
//    @BeforeEach
//    public void setUp() throws Exception{
//
//        misketRepository = Mockito.mock(MisketRepository.class);
//
//        misketService = new MisketService(misketRepository);
//    }

    @Test   // whenMisketServiceCreateMisketWithMisketDto_thenReturnMisketDto()
    public void whenMisketServiceCreateMisketWithMisketDto_thenReturnMisketDto() {
        Misket misket = Misket.builder()    // Data
                .name("blue")
                .type("hit")
                .build();

        MisketDto misketDto = MisketDto.builder().name("blue").type("hit").build();   // Data

        Mockito.when(misketRepository.save(Mockito.any(Misket.class))).thenReturn(misket);

        MisketDto savedMisket = misketService.createMisket(misketDto);

        Assertions.assertEquals(savedMisket,misketDto);


    }

}