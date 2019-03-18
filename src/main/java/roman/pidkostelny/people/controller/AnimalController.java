package roman.pidkostelny.people.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roman.pidkostelny.people.dto.request.AnimalRequest;
import roman.pidkostelny.people.dto.response.AnimalResponse;
import roman.pidkostelny.people.exception.WrongInputDataException;
import roman.pidkostelny.people.service.AnimalService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public void saveAnimal(@RequestBody AnimalRequest animalRequest) throws WrongInputDataException {
        animalService.save(animalRequest);
    }

    @GetMapping
    public List<AnimalResponse> findAll(){
      return animalService.findAll();
    }
}
