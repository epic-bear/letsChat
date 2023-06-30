package com.app.letschat.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageResponse {

  private MessageType type;
  private String content;
  private String sender;
  private long chatId;
  private LocalDateTime created;

}
