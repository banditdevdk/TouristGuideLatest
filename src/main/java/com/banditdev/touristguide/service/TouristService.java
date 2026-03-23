package com.banditdev.touristguide.service;

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
/*old method
    public void deleteTouristAttraction(String name) {
        repository.deleteTouristAttraction(name);
    }

 */
    //delete atractions by id
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
