package Pans.Api.repository;

import Pans.Api.models.KoloUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KoloUserRepository extends JpaRepository<KoloUser, Integer> {
}