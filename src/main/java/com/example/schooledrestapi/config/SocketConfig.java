package com.example.schooledrestapi.config;

import com.example.schooledrestapi.ws.StudentStateSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {

  @Autowired
  private StudentStateSocketHandler studentStateSocketHandler;

  private final static String SOCKET_PREFIX = "/sub";

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(studentStateSocketHandler, SOCKET_PREFIX + "/student/state");
  }
}
