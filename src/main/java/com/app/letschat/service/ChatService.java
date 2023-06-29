package com.app.letschat.service;

import com.app.letschat.domain.Chat;
import com.app.letschat.domain.User;
import com.app.letschat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatService {

  private final ChatRepository chatRepository;
  private final UserService userService;

  @Autowired
  public ChatService(ChatRepository chatRepository,
      UserService userService) {
    this.chatRepository = chatRepository;
    this.userService = userService;
  }

  public void createChat(String username, String chatName) {
    if (chatRepository.existsByName(chatName)) {
      throw new IllegalArgumentException("Username already exists");
    }

    Chat chat = new Chat();
    chat.setName(chatName);

    User user = userService.getUser(username);

    chat.setAdministrator(user);
    chat.getUsers().add(user);

    chatRepository.save(chat);
  }
}
