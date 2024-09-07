package com.zeromh.chat.realtime.connect.config;

import com.zeromh.chat.core.domain.member.ConnectStatus;
import com.zeromh.chat.core.domain.member.UserStatus;
import com.zeromh.chat.realtime.connect.preserve.application.PreserveUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
//@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final PreserveUseCase preserveUseCase;

//    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId  = headerAccessor.getLogin();
        String sessionId = event.getUser().getName(); //  headerAccessor.getSessionId();
        headerAccessor.getSessionAttributes().put("userId", userId);

        log.info(event.getUser().getName());
        log.info("[Connect] Received a new WebSocket connection. user: {}, session: {}", userId,  sessionId);

        preserveUseCase.changeUserStatus(UserStatus.builder()
                .id(userId)
                .status(ConnectStatus.ONLINE)
                .build());

    }

//    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String userId  = headerAccessor.getLogin();
//        String sessionId = headerAccessor.getSessionId();
        String userId = (String) headerAccessor.getSessionAttributes().get("userId");
        String sessionId = event.getUser().getName();
        log.info("[Connect] WebSocket connection closed. user: {}, session: {}", userId,  sessionId);
        preserveUseCase.changeUserStatus(UserStatus.builder()
                        .id(userId)
                        .status(ConnectStatus.OFFLINE)
                .build());
    }
}
