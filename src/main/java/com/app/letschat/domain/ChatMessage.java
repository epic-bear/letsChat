package com.app.letschat.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "messages")
public class ChatMessage {

  @Id
  @GeneratedValue
  private long messageId;

  private MessageType type;
  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User sender;

  @ManyToOne
  @JoinColumn(name = "chat_id")
  private Chat chat;

  private LocalDateTime created;
}
