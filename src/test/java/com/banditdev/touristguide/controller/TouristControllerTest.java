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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

    @Test
    void saveNewTouristAttraction() throws Exception {
        TouristAttraction saved = new TouristAttraction(1, "Rundetårn", "En random description", "København");
        when(touristService.addTouristAttraction(any(TouristAttraction.class))).thenReturn((saved));

        mockMvc.perform(post("/attractions/save")
                        .param("name", "Rundetårn")
                        .param("description", "En random description")
                        .param("cityName", "København"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

        ArgumentCaptor<TouristAttraction> captor = ArgumentCaptor.forClass(TouristAttraction.class);
        verify(touristService).addTouristAttraction(captor.capture());

        TouristAttraction captured = captor.getValue();
        assertEquals("Rundetårn", captured.getName());
        assertEquals("En random description", captured.getDescription());
        assertEquals("København", captured.getCityName());
    }

    @Test
    void viewTags() throws Exception {
        TouristAttraction mockAttraction = new TouristAttraction();
        mockAttraction.setId(7);
        mockAttraction.setName("Eiffel Tower");
        mockAttraction.setDescription("Test Description");
        mockAttraction.setCityName("TestCity");
        mockAttraction.setAttractionTags(List.of("TestTag1", "TestTag2", "TestTag3"));

        when(touristService.findTouristAttractionById(7)).thenReturn(mockAttraction);

        mockMvc.perform(get("/attractions/{id}/tags", 7))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attribute("attraction", mockAttraction));
    }

    @Test
    void editTouristAttraction() {
    }

    @Test
    void delete() {
    }
}