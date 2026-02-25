package com.banditdev.touristguide.controller;

import com.banditdev.touristguide.model.AttractionTags;
import com.banditdev.touristguide.model.Cities;
import com.banditdev.touristguide.model.TouristAttraction;
import com.banditdev.touristguide.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;


    @Test
    void getTouristAttractions() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"))
                .andExpect(model().attributeExists("attractions"));
    }

    @Test
    void getTouristAttractionByName() throws Exception {

        TouristAttraction touristAttraction = new TouristAttraction("Test","Test description", Cities.HERNING);

        when(touristService.findTouristAttractionByName(touristAttraction.getName())).thenReturn(touristAttraction);

        mockMvc.perform(get("/attractions/{name}", touristAttraction.getName()))
                .andExpect(status().isOk());

        verify(touristService).findTouristAttractionByName(touristAttraction.getName());
    }

    @Test
    void viewTags() throws Exception {
        TouristAttraction mockAttraction = new TouristAttraction();
        mockAttraction.setName("Eiffel Tower");
        mockAttraction.setAttractionTags(List.of(AttractionTags.HISTORISK, AttractionTags.SEVÆRDIGHED, AttractionTags.KULTUR));

        when(touristService.findTouristAttractionByName("Eiffel Tower"))
                .thenReturn(mockAttraction);
        //tjekker GET til endpoint
        mockMvc.perform(get("/attractions/{name}/tags", "Eiffel Tower"))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attribute("attraction", mockAttraction))
                .andExpect(model().attribute("tags", mockAttraction.getAttractionTags()));
    }

    @Test
    void addNewTouristAttraction() throws Exception {
        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addAttraction"))
                .andExpect(model().attributeExists("touristAttraction"))
                .andExpect(model().attributeExists("cities"))
                .andExpect(model().attributeExists("tags"));

    }

    @Test
    void saveNewTouristAttraction() {
    }

    @Test
    void editTouristAttraction() {
    }

    @Test
    void updateTouristAttraction() {
    }

    @Test
    void deleteAttraction() {
    }
}