package com.app.letschat.domain;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue
  private long userId;

  private String name;

  private String password;

  @OneToMany(mappedBy = "sender")
  private Set<ChatMessage> messages = new HashSet<>();

  @ManyToMany(mappedBy = "users")
  private Set<Chat> chats = new HashSet<>();
}
