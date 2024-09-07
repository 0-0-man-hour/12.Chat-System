package com.zeromh.chat.core.infra.messaging;

import com.zeromh.chat.core.infra.ChatConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer, ChatConfig {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트로 메시지를 전달할 때 사용할 경로 설정 (예: /topic, /queue)
        // config.enableSimpleBroker("/topic");
        // 클라이언트에서 메시지를 보낼 때 사용할 경로 설정 (예: /app)
        registry.setApplicationDestinationPrefixes("/pub");
        registry.setUserDestinationPrefix("/user"); // 사용자 목적지 프리픽스 설정
        registry.enableSimpleBroker("/queue","/topic");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 연결 엔드포인트 설정
//        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
//        registry.addEndpoint("/ws").setAllowedOrigins("*");
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        return UUID.randomUUID()::toString;
                    }
                });

        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        return UUID.randomUUID()::toString;
                    }
                })
                .withSockJS();

    }

}
