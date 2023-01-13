package com.iot.light.blidns.iotproject.websocket;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@ClientEndpoint
public class WebSocketClient {
  private Session session;
  private final ObjectMapper objectMapper = new ObjectMapper();

  @PostConstruct
  public void connect() {
    WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    try {
      container.connectToServer(this, new URI("ws://147.232.44.162/apps/localbus.lp"));
    } catch (DeploymentException | URISyntaxException | IOException e) {
      e.printStackTrace();
    }
  }

  @OnOpen
  public void onOpen(Session session) {
    this.session = session;
  }

  public void sendJsonMessage(Integer address, Integer value) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode node = objectMapper.createObjectNode()
            .put("action", "write")
            .put("address", address)
            .put("datatype", 5)
            .put("value", value);
    session.getAsyncRemote().sendText(objectMapper.writeValueAsString(node));
  }
}