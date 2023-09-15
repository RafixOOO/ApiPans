package Pans.Api.repository;

import Pans.Api.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByImieContainsAndNazwiskoContainsAndEmailconfirmOrNazwiskoContainsAndEmailconfirmOrImieContainsAndEmailconfirm(String imie,String nazwisko,Integer emailconfirm ,String nazwisko1,Integer emailconfirm1, String imie1 , Integer emailconfirm2);

    



}