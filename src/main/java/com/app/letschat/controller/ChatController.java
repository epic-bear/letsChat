package com.app.letschat.controller;

import com.app.letschat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChatController {

  private final ChatService chatService;

  @Autowired
  public ChatController(final ChatService chatService) {
    this.chatService = chatService;
  }

  @PostMapping("/createChat")
  public String createUser(@RequestParam("username") String username,
      @RequestParam("chatName") String chatName, RedirectAttributes redirectAttributes) {
    chatService.createChat(username, chatName);
    redirectAttributes.addAttribute("username", username);
    return "redirect:/main";
  }
}
