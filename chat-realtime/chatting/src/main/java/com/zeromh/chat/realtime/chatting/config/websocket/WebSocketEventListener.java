package com.zeromh.chat.realtime.chatting.config.websocket;

import com.zeromh.chat.core.domain.member.ConnectStatus;
import com.zeromh.chat.core.domain.member.UserStatus;
import com.zeromh.chat.realtime.chatting.config.ServerProperties;
import com.zeromh.chat.realtime.chatting.receive.application.ReceiveChatUseCase;
import com.zeromh.chat.realtime.chatting.send.application.CheckConnectPort;
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

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final ReceiveChatUseCase receiveChatUseCase;
    private final CheckConnectPort checkConnectPort;
    private final ServerProperties serverProperties;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId  = headerAccessor.getLogin();
        String sessionId = event.getUser().getName(); //  headerAccessor.getSessionId();
        headerAccessor.getSessionAttributes().put("userId", userId);

        log.info(event.getUser().getName());
        log.info("[Chat] Received a new WebSocket connection. user: {}, session: {}", userId,  sessionId);

        receiveChatUseCase.subscribe(userId, sessionId);
        checkConnectPort.updateUserInfo(UserStatus.builder()
                .id(userId)
                .status(ConnectStatus.ONLINE)
                .lastUpdateAt(LocalDateTime.now())
                .server(serverProperties.getServerInfo())
                .build());

    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId = (String) headerAccessor.getSessionAttributes().get("userId");
//        String sessionId = headerAccessor.getSessionId();
        String sessionId = event.getUser().getName();
        log.info("[Chat] WebSocket connection closed. user: {}, session: {}", userId,  sessionId);

        receiveChatUseCase.unsubscribe(userId);
        checkConnectPort.updateUserInfo(UserStatus.builder()
                .id(userId)
                .status(ConnectStatus.OFFLINE)
                .lastUpdateAt(LocalDateTime.now())
                .build());

    }
}
