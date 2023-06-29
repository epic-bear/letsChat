package com.app.letschat.controller;

import com.app.letschat.domain.Chat;
import com.app.letschat.domain.User;
import com.app.letschat.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/createUser")
  public String createUser(@RequestParam("username") String username,
      @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
    userService.createUser(username, password);
    redirectAttributes.addAttribute("username", username);
    return "redirect:/main";
  }

  @GetMapping("/getUser")
  public String getUser(@RequestParam("username") String username,
      @RequestParam("password") String password,
      RedirectAttributes redirectAttributes) {

    if (userService.checkCredentials(username, password)) {
      redirectAttributes.addAttribute("username", username);
      return "redirect:/main";
    } else {
      return "redirect:/index";
    }
  }

  @GetMapping("/main")
  public String getMain(@ModelAttribute("username") String username, Model model) {
    User user = userService.getUser(username);
    List<String> chatNames = user.getChats().stream().map(Chat::getName).collect(Collectors.toList());

    model.addAttribute("username", username);
    model.addAttribute("chatNames", chatNames);
    return "main";
  }
}
