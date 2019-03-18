package roman.pidkostelny.people.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import roman.pidkostelny.people.dto.request.PersonRequest;
import roman.pidkostelny.people.dto.response.DataResponse;
import roman.pidkostelny.people.dto.response.PersonResponse;
import roman.pidkostelny.people.service.PersonService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public DataResponse<PersonResponse> getPersons(@RequestParam(required = false) String value,
                                                   @RequestParam Integer page,
                                                   @RequestParam Integer size,
                                                   @RequestParam String sortFieldName,
                                                   @RequestParam Sort.Direction direction){
        System.out.println("GET ALL PERSONS");
        return personService.findAll(value,page,size,sortFieldName,direction);
    }

    @GetMapping("/{id}")
    public PersonResponse getPersonById(@PathVariable Integer id){
        System.out.println("Get person by id : "+id);
        return personService.findOne(id);
    }

    @PostMapping
    public Integer createPerson(@RequestBody @Valid PersonRequest personRequest){
        System.out.println("SAVE PERSON IN DB with first name -> "+ personRequest.getFirstName());
        return personService.save(personRequest);
    }

    @DeleteMapping("/{id}")
    public void search(@PathVariable Integer id){
        System.out.println("Delete person by id "+id);
        personService.delete(id);
    }
}