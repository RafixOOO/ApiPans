package Pans.Api.repository;

import Pans.Api.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}