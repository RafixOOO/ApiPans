package Pans.Api.repository;

import Pans.Api.models.Kolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KoloRepository extends JpaRepository<Kolo, Integer> {
    List<Kolo> findByNazwaContains(String nazwa);

}