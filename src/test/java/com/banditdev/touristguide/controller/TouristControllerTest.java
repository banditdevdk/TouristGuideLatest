package com.banditdev.touristguide.controller;

import com.banditdev.touristguide.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(TouristController.class)
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTouristAttractions() {
    }

    @Test
    void getTouristAttractionByName() {
    }

    @Test
    void viewTags() {
    }

    @Test
    void addTouristAttraction() {
    }

    @Test
    void updateTouristAttraction() {
    }

    @Test
    void deleteAttraction() {
    }

    @Test
    void seeAllTest() {
    }
}