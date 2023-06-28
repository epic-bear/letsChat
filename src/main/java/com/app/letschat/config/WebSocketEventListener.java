package com.app.letschat.config;

import com.app.letschat.domain.ChatMessage;
import com.app.letschat.domain.MessageType;
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
    String username = (String) headerAccessor.getSessionAttributes().get("username");
    if (username != null) {
      ChatMessage chatMessage = ChatMessage.builder()
          .type(MessageType.LEAVE)
          .sender(username)
          .build();
      messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
  }

}
