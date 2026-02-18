package com.banditdev.touristguide.service;

import com.banditdev.touristguide.model.Cities;
import com.banditdev.touristguide.model.TouristAttraction;
import com.banditdev.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class TouristService {
    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public ArrayList<TouristAttraction> getTouristAttractions(){
        return repository.getTouristAttractions();
    }

    public  TouristAttraction findTouristAttractionByName(String input) {
        return repository.findTouristAttractionByName(input);
    }

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction) {
        return repository.addTouristAttraction(touristAttraction);
    }

    public void deleteTouristAttraction(String name) {
        repository.deleteTouristAttraction(name);
    }

    public List<String> getCities() {
        return repository.getCities();
    }

}
