package com.app.letschat.controller;

import com.app.letschat.domain.Chat;
import com.app.letschat.domain.MessageCommand;
import com.app.letschat.domain.MessageResponse;
import com.app.letschat.service.ChatService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChatController {

  private final ChatService chatService;

  @Autowired
  public ChatController(final ChatService chatService) {
    this.chatService = chatService;
  }

  @PostMapping("/createChat")
  public String createChat(@RequestParam("username") String username,
      @RequestParam("chatName") String chatName, RedirectAttributes redirectAttributes) {
    chatService.createChat(username, chatName);
    redirectAttributes.addAttribute("username", username);
    return "redirect:/main";
  }

  @MessageMapping("/send/{chatId}")
  @SendTo("/topic/chat/{chatId}")
  public MessageResponse sendMessage(@DestinationVariable("chatId") Long chatId,
      @Payload MessageCommand messageCommand) {

    return chatService.sendMessage(chatId, messageCommand);
  }

  @PostMapping("/searchChat")
  @ResponseBody
  public List<String> searchChat(@RequestParam("searchTerm") String searchTerm) {

    return chatService.searchChat(searchTerm);
  }
}
