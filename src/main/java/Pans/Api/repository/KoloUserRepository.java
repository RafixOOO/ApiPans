package Pans.Api.repository;

import Pans.Api.models.Kolo;
import Pans.Api.models.KoloUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KoloUserRepository extends JpaRepository<KoloUser, Integer> {
    List<KoloUser> findByUserIdUser_Email(String email);

    List<KoloUser> findByKoloIdKola(Kolo koloIdKola);

}