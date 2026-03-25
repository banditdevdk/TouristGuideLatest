package com.banditdev.touristguide.controller;

import com.banditdev.touristguide.model.TouristAttraction;
import com.banditdev.touristguide.repository.TouristRepository;
import com.banditdev.touristguide.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


@WebMvcTest(TouristController.class)
@ActiveProfiles("test")
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @MockitoBean
    private TouristRepository touristRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getTouristAttractions() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }


    @Test
    void addNewTouristAttraction() {
    }

//    @Test
//    void saveNewTouristAttraction() throws Exception {
//            mockMvc.perform(post("/attractions/save")
//                            .param("id", "1")
//                            .param("name", "Rundetårn")
//                            .param("description", "En random description")
//                            .param("cityName", String.valueOf(Cities.KØBENHAVN)))
//
//
//                    .andExpect(status().is3xxRedirection())
//                    .andExpect(view().name("redirect:/attractions"));
//
//            ArgumentCaptor<TouristAttraction> captor = ArgumentCaptor.forClass(TouristAttraction.class);
//            verify(touristService).addTouristAttraction(captor.capture());
//
//            TouristAttraction captured = captor.getValue();
//            assertEquals("Rundetårn", captured.getName());
//            assertEquals("En random description", captured.getDescription());
//            assertEquals(Cities.KØBENHAVN, captured.getCityName());
//        }

    @Test
    void editTouristAttraction() {
    }

    @Test
    void updateTouristAttraction() {
    }

    @Test
    void delete() {
    }
}