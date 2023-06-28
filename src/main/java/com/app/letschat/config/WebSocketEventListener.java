package com.app.letschat.config;

import com.app.letschat.domain.ChatMessage;
import com.app.letschat.domain.MessageType;
import com.app.letschat.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

  private final SimpMessageSendingOperations messagingTemplate;

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
    User user = (User) headerAccessor.getSessionAttributes().get("user");
    if (user != null) {
      ChatMessage chatMessage = ChatMessage.builder()
          .type(MessageType.LEAVE)
          .sender(user)
          .build();
      messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
  }

}
