package com.app.letschat.repository;

import com.app.letschat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User getUserByName(String username);

  boolean existsByName(String username);
}
