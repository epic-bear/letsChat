package com.app.letschat.service;

import com.app.letschat.domain.User;
import com.app.letschat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public void createUser(String username, String password) {
    if (userRepository.existsByName(username)) {
      throw new IllegalArgumentException("Username already exists");
    }

    User user = new User();
    user.setName(username);
    user.setPassword(password);
    userRepository.save(user);
  }

  public User getUser(String username) {
    return userRepository.getUserByName(username);
  }

  public boolean checkCredentials(String username, String password) {
    User user = userRepository.getUserByName(username);
    return user != null && user.getPassword().equals(password);
  }
}
