package com.zeromh.chat.realtime.chatting.config.websocket;

import com.zeromh.chat.realtime.chatting.receive.application.ReceiveChatUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final ReceiveChatUseCase receiveChatUseCase;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId  = headerAccessor.getLogin();
        String sessionId = event.getUser().getName(); //  headerAccessor.getSessionId();
        log.info(event.getUser().getName());
        log.info("Received a new WebSocket connection. user: {}, session: {}", userId,  sessionId);

        receiveChatUseCase.subscribe(userId, sessionId);

    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId  = headerAccessor.getLogin();
//        String sessionId = headerAccessor.getSessionId();
        String sessionId = event.getUser().getName();
        log.info("WebSocket connection closed. user: {}, session: {}", userId,  sessionId);

        receiveChatUseCase.unsubscribe(userId);

    }
}
