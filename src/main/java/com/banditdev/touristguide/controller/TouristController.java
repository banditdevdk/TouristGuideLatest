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

    @GetMapping("{name}")
    public ResponseEntity<TouristAttraction> getTouristAttractionByName(@PathVariable String name) {
        TouristAttraction t = service.findTouristAttractionByName(name);

        if (t != null) {
            return new ResponseEntity<>(t, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping("/{name}/edit")
    public String editTouristAttraction(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = service.findTouristAttractionByName(name);
        model.addAttribute("touristAttraction", touristAttraction);


        model.addAttribute("cities", service.getCities());
        model.addAttribute("tags", service.getTags());

        return "edit-template";
    }

    @PostMapping("/update")
    public String updateTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        service.updateTouristAttraction(touristAttraction);

        return "redirect:/attractions";
    }
/*old method
    @PostMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        service.deleteTouristAttraction(name);

        return "redirect:/attractions";
    }
 */
    //delete attraction by id
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        service.deleteTouristAttractionById(id);
        return "redirect:/attractions";
    }

}
