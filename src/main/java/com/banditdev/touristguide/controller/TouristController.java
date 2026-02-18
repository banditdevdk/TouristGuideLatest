package com.banditdev.touristguide.controller;

import com.banditdev.touristguide.model.Cities;
import com.banditdev.touristguide.model.TouristAttraction;
import com.banditdev.touristguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("attractions")
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping()
    public String getTouristAttractions(Model model) {
        ArrayList<TouristAttraction> touristAttractions = service.getTouristAttractions();
        model.addAttribute("attractions", touristAttractions);
        return "attractionList";
    }

    @GetMapping("{name}")
    public ResponseEntity<TouristAttraction> getTouristAttractionByName(@PathVariable String name) {
        TouristAttraction t = service.findTouristAttractionByName(name);

        if (t != null) {
            return new ResponseEntity<>(t, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{name}/tags")
    public String viewTags(@PathVariable String name, Model model) {
        TouristAttraction t = service.findTouristAttractionByName(name);
        model.addAttribute("attraction", t);
        model.addAttribute("tags", t.getAttractionTags());

        return "tags";
    }

    @GetMapping("/add")
    public String addNewTouristAttraction(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());

        model.addAttribute("cities", Cities.values());

        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveNewTouristAttraction(@ModelAttribute TouristAttraction touristAttraction, Model model) {
        model.addAttribute("touristAttraction", touristAttraction);

        service.addTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    //TODO @GetMapping("/{name}/edit")
    
    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> updateTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
    //logik skal muligvis ligge i repository? og skal return en string for at komme til en thymeleaf template
        for (int i = 0; i < service.getTouristAttractions().size(); i++) {
            TouristAttraction ta = service.getTouristAttractions().get(i);

            if (touristAttraction.getName().equalsIgnoreCase(ta.getName())) {
                service.getTouristAttractions().set(i, touristAttraction);
                return new ResponseEntity<>(touristAttraction, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        service.deleteTouristAttraction(name);

        return "redirect:/attractions";
    }

}
