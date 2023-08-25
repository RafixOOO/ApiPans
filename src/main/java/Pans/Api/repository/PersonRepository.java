package Pans.Api.repository;

import Pans.Api.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByImieContainsOrNazwiskoContainsOrImieContainsAndNazwiskoContains(String imie, String nazwisko, String imie1, String nazwisko1);
    



}