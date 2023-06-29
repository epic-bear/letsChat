package com.app.letschat.repository;

import com.app.letschat.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

  boolean existsByName(String chatName);
}
