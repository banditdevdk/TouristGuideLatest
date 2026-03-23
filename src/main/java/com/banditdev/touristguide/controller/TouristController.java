package com.banditdev.touristguide.controller;

import com.banditdev.touristguide.model.TouristAttraction;
import com.banditdev.touristguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping()
    public String getTouristAttractions(Model model) {
        List<TouristAttraction> touristAttractions = service.findAll();
        model.addAttribute("attractions", touristAttractions);
        return "attractionList";
    }

    @GetMapping("{id}")
    public String getTouristAttractionById(@PathVariable int id, Model model) {
        TouristAttraction t = service.findTouristAttractionById(id);

        if (t == null) {
            return "attractionList"; //vi kan lave error page eller andet her!
        }

        model.addAttribute("attraction", t);
        return "attractionSingle";
    }



    @GetMapping("/add")
    public String addNewTouristAttraction(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());

        model.addAttribute("cities", service.getCities());
        model.addAttribute("tags", service.getTags());

        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveNewTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {

        service.addTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }

//    @GetMapping("/{name}/edit")
//    public String editTouristAttraction(@PathVariable String name, Model model) {
//        TouristAttraction touristAttraction = service.findTouristAttractionByName(name);
//        model.addAttribute("touristAttraction", touristAttraction);
//
//
//        model.addAttribute("cities", service.getCities());
//        model.addAttribute("tags", service.getTags());
//
//        return "edit-template";
//    }

    @PostMapping("/update")
    public String updateTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        service.updateTouristAttraction(touristAttraction);

        return "redirect:/attractions";
    }

    @PostMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        service.deleteTouristAttraction(name);

        return "redirect:/attractions";
    }

}
