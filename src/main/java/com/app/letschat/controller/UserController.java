package com.app.letschat.controller;

import com.app.letschat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/createUser")
  public String createUser(@RequestParam("username") String username,
      @RequestParam("password") String password, Model model) {
    userService.createUser(username, password);
    model.addAttribute("username", username);
    return "main";
  }
}
