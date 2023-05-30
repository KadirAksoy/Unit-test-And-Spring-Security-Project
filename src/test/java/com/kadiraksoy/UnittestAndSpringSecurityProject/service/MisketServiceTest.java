package com.kadiraksoy.UnittestAndSpringSecurityProject.service;

import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.MisketDto;
import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.MisketResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.mockito.Mockito.times;


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

    @Test
    public void whenMisketServiceGetAllMisketWithPage_thenReturnMisketDto(){
        Page<Misket> miskets = Mockito.mock(Page.class);

        Mockito.when(misketRepository.findAll(Mockito.any(Pageable.class))).thenReturn(miskets);

        MisketResponse misketResponse = misketService.getAllMisket(1,10);

        org.assertj.core.api.Assertions.assertThat(misketResponse).isNotNull();
    }

    @Test
    public void whenMisketServiceFindMisketById_thenReturnMisketDto() {
        int misketId = 1;
        Misket misket = Misket.builder().id(1).name("yellow").type("defense").type("this is a type").build();

        Mockito.when(misketRepository.findById(misketId)).thenReturn(Optional.ofNullable(misket));

        MisketDto misketReturn = misketService.getMisketById(misketId);

        org.assertj.core.api.Assertions.assertThat(misketReturn).isNotNull();
    }

    @Test
    public void whenMisketServiceUpdateMisketWithIdAndMisketDto_thenReturnMisketDto(){
        int misketId = 1;
        Misket misket = Misket.builder().id(1).name("blue").type("hit").type("this is a type").build();
        MisketDto misketDto = MisketDto.builder().id(1).name("blue").type("hit").type("this is a type").build();

        Mockito.when(misketRepository.findById(misketId)).thenReturn(Optional.ofNullable(misket));
        Mockito.when(misketRepository.save(misket)).thenReturn(misket);

        MisketDto updateMisket = misketService.updateMisket(misketDto,misketId);

        Assertions.assertEquals(misketDto,updateMisket);
    }

    @Test
    public void whenMisketServiceDeleteMisketWithId_thenReturnVoid(){
        int misketId = 0;
        Misket misket = Misket.builder().id(1).name("blue").type("hit").type("this is a type").build();

        Mockito.when(misketRepository.findById(misketId)).thenReturn(Optional.ofNullable(misket));

        misketService.deleteMisketId(misketId);

        Mockito.verify(misketRepository).findById(misketId);
        Mockito.verify(misketRepository).delete(misket);

    }



}