package com.banditdev.touristguide.service;

import com.banditdev.touristguide.model.TouristAttraction;
import com.banditdev.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public TouristAttraction findTouristAttractionById(int input) {
        return repository.findTouristAttractionById(input);
    }

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction) {
        return repository.addTouristAttraction(touristAttraction);
    }

    public void deleteTouristAttractionById(int id) {
        repository.deleteTouristAttractionById(id);
    }

    public List<String> getCities() {
        return repository.getCities();
    }

    public List<String> getTags() {
        return repository.getTags();
    }

    public void updateTouristAttraction(TouristAttraction touristAttraction) {
        repository.updateTouristAttraction(touristAttraction);
    }

    public List<TouristAttraction> findAll() {
        return repository.findAll();
    }

}
