package Pans.Api.repository;

import Pans.Api.models.Event;
import Pans.Api.models.Kolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface EventRepository extends JpaRepository<Event, Integer> {



}