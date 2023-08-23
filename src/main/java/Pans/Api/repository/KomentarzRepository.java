package Pans.Api.repository;

import Pans.Api.models.Komentarz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentarzRepository extends JpaRepository<Komentarz, Integer> {
}