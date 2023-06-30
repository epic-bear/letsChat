package com.app.letschat.service;

import com.app.letschat.domain.Chat;
import com.app.letschat.domain.ChatMessage;
import com.app.letschat.domain.MessageCommand;
import com.app.letschat.domain.MessageResponse;
import com.app.letschat.domain.User;
import com.app.letschat.repository.ChatMessageRepository;
import com.app.letschat.repository.ChatRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatService {

  private final ChatRepository chatRepository;
  private final ChatMessageRepository messageRepository;
  private final UserService userService;

  @Autowired
  public ChatService(ChatRepository chatRepository,
      ChatMessageRepository messageRepository, UserService userService) {
    this.chatRepository = chatRepository;
    this.messageRepository = messageRepository;
    this.userService = userService;
  }

  public void createChat(String username, String chatName) {
    if (chatRepository.existsByName(chatName)) {
      throw new IllegalArgumentException("Name already exists");
    }

    Chat chat = new Chat();
    chat.setName(chatName);

    User user = userService.getUser(username);

    chat.setAdministrator(user);
    chat.getUsers().add(user);

    chatRepository.save(chat);
  }

  public MessageResponse sendMessage(Long chatId, MessageCommand messageCommand) {
    ChatMessage createdMessage = createChatMessage(chatId, messageCommand);
    MessageResponse messageResponse = new MessageResponse();
    messageResponse.setChatId(chatId);
    messageResponse.setContent(createdMessage.getContent());
    messageResponse.setCreated(createdMessage.getCreated());
    messageResponse.setSender(messageCommand.getUsername());
    messageResponse.setType(messageCommand.getType());

    return messageResponse;
  }

  private ChatMessage createChatMessage(Long chatId, MessageCommand messageCommand) {
    ChatMessage chatMessage = new ChatMessage();
    User sender = userService.getUser(messageCommand.getUsername());
    chatMessage.setContent(messageCommand.getMessage());
    chatMessage.setType(messageCommand.getType());
    chatMessage.setSender(sender);

    Optional<Chat> chat = Optional.ofNullable(chatRepository.findById(chatId).orElseThrow());
    chatMessage.setChat(chat.get());

    chatMessage.setCreated(LocalDateTime.now());

    return messageRepository.save(chatMessage);
  }
}
