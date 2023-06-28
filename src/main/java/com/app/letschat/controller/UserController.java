package com.app.letschat.controller;

import com.app.letschat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {


  private final UserService userService;

  @Autowired
  public UserController(final UserService userService) {
    this.userService = userService;
  }
}
