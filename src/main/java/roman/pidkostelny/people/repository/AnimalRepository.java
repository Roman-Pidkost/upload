package roman.pidkostelny.people.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.pidkostelny.people.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
