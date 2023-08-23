package Pans.Api.repository;

import Pans.Api.models.Kalendarz;
import Pans.Api.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KalendarzRepository extends JpaRepository<Kalendarz, Integer> {
    List<Kalendarz> findByIdPerson(Person idPerson);

}