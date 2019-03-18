package roman.pidkostelny.people.dto.response;

import lombok.Getter;
import lombok.Setter;
import roman.pidkostelny.people.entity.Animal;
import roman.pidkostelny.people.entity.PetType;


@Getter @Setter
public class AnimalResponse {

    private Integer id;

    private String nickName;

    private PetType petType;

    private PersonResponse personResponse;

    public AnimalResponse(Animal animal) {
        this.id = animal.getId();
        this.nickName = animal.getNickName();
        this.petType = animal.getPetType();
        this.personResponse =new PersonResponse(animal.getPerson());
    }
}
