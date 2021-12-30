package com.example.code_block_server.controller;

import com.example.code_block_server.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.SerializationUtils;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class MessageSocketChannel extends AbstractWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        System.out.println("Message is: " + msg);

        MessageDTO messageDTO = objectMapper.readValue(msg, MessageDTO.class);
        System.out.println(messageDTO.getId());
        System.out.println(messageDTO.getBody());

        session.sendMessage(new TextMessage(msg));
    }
}
