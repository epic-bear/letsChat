package com.app.letschat.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chats")
public class Chat {

  @Id
  @GeneratedValue
  private long chatId;

  private String name;

  private ChatAccess access = ChatAccess.PUBLIC;

  @ManyToOne
  @JoinColumn(name = "administrator_id")
  private User administrator;

  @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
  private Set<ChatMessage> messages = new HashSet<>();

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_chat",
      joinColumns = @JoinColumn(name = "chat_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> users = new HashSet<>();
}
