package roman.pidkostelny.people.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roman.pidkostelny.people.dto.request.PersonRequest;
import roman.pidkostelny.people.dto.response.DataResponse;
import roman.pidkostelny.people.dto.response.PersonResponse;
import roman.pidkostelny.people.entity.Person;
import roman.pidkostelny.people.repository.PersonRepository;
import roman.pidkostelny.people.specification.PersonSpecification;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Integer save(PersonRequest personRequest){
        Person person = new Person();
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        person.setAge(personRequest.getAge());
        person = personRepository.save(person);
        return person.getId();
    }

    public DataResponse<PersonResponse> findAll(String value, Integer page, Integer size, String fieldName, Sort.Direction direction){
        Sort sort = Sort.by(direction, fieldName);
        PageRequest pageRequest = PageRequest.of(page,size,sort);
        Page<Person> pagePerson;
        if(value != null && !value.equals("")) {
            PersonSpecification specification = new PersonSpecification(value);
            pagePerson = personRepository.findAll(specification, pageRequest);
        }else{
            pagePerson = personRepository.findAll(pageRequest);
        }
        return new DataResponse<PersonResponse>(pagePerson.stream().map(PersonResponse::new).collect(Collectors.toList()), pagePerson);
    }

    @Transactional
    public PersonResponse findOne(Integer id){
        Optional<Person> personOptional = personRepository.findById(id);
        if(personOptional.isPresent()){
            return new PersonResponse(personOptional.get());
        }else{
            throw new IllegalArgumentException("Person with id "+id+" not found");
        }
    }

    public void delete(Integer id) {
        personRepository.deleteById(id);
    }
}
