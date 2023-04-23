package com.zlg.zlgpm.commom.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;

@Component
public class SocketHandler extends TextWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("和客户端建立连接");
        Map<String, Object> attributes = session.getAttributes();
        logger.info(attributes.toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session,status);
        logger.info("和客户端断开连接");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException {
        session.close(CloseStatus.SERVER_ERROR);
        logger.error("连接异常",exception);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // 获取到客户端发送过来的消息
        String receiveMessage = message.getPayload();
        logger.info(receiveMessage);
        // 发送消息给客户端
        session.sendMessage(new TextMessage("服务端收到了，并做出了响应"));
    }

    public void sendTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        session.sendMessage(new TextMessage("服务端推送的消息"));
    }

}
