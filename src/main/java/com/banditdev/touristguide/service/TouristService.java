package com.banditdev.touristguide.service;

import com.banditdev.touristguide.model.TouristAttraction;
import com.banditdev.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


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

}
