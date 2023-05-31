package com.kadiraksoy.UnittestAndSpringSecurityProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.MisketDto;
import com.kadiraksoy.UnittestAndSpringSecurityProject.dto.ReviewDto;
import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Misket;
import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Review;
import com.kadiraksoy.UnittestAndSpringSecurityProject.service.MisketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.springframework.http.RequestEntity.post;

//@WebMvcTest(controllers = MisketController.class) is used for unit tests that specifically focus on the web layer of an application
//@AutoConfigureMockMvc(addFilters = false) is used to automatically configure the MockMvc instance in the test environment.
//@ExtendWith(MockitoExtension.class) is used to integrate Mockito, a popular mocking framework, into the test environment.
@WebMvcTest(controllers = MisketController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MisketControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private MisketService misketService;
//
//    @InjectMocks
//    private MisketController misketController;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    private Misket misket;
//    private Review review;
//    private ReviewDto reviewDto;
//    private MisketDto misketDto;
//    private MisketDto expectMisketDto;
//
//    @BeforeEach
//    public void init(){
//        misket = Misket.builder().name("blue").type("hit").build();
//        misketDto = MisketDto.builder().name("blue").type("hit").build();
//        review = Review.builder().title("title").content("content").stars(5).build();
//        reviewDto = ReviewDto.builder().title("title").content("content").stars(5).build();
//        expectMisketDto = MisketDto.builder().name("blue").type("hit").build();
//
//    }
//
//    @Test
//    public void whenMisketControllerCreateMisket_thenReturnCreated() throws Exception {
//
//        Mockito.when(misketService.createMisket(misketDto)).thenReturn(expectMisketDto);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/misket/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(misketDto)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                // expectMisketDto'nun döndürdüğü alanları doğrula
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectMisketDto.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(expectMisketDto.getType()))
//                // Diğer alanları da doğrula
//                .andReturn();
//
//        // Misket servisinin çağrıldığını doğrula
//        Mockito.verify(misketService, times(1)).createMisket(misketDto);
//
//    }
//
//    private String asJsonString(Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


}